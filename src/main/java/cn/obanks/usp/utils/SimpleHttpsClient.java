package cn.obanks.usp.utils;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.io.IOUtils;

public class SimpleHttpsClient {
	private Map<Integer, Integer> registerPortList = new HashMap<Integer, Integer>();

	public SimpleHttpsClient() {
		Protocol.registerProtocol("https", new Protocol("https", new SimpleHttpsSocketFactory(), 8443));
		registerPort(8443);
	}

	public HttpSendResult postRequest(String url, Map<String, String> params, int timeout, String characterSet) throws SecurityException {
		if (characterSet == null || "".equals(characterSet)) {
			characterSet = "UTF-8";
		}
		HttpSendResult result = new HttpSendResult();
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Connection", "close");
		postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + characterSet);
		NameValuePair[] data = this.createNameValuePair(params);
		postMethod.setRequestBody(data);
		Integer port = getPort(url);
		if (isRegisterPort(port)) {
			Protocol myhttps = new Protocol("https", new SimpleHttpsSocketFactory(), 443);
			Protocol.registerProtocol("https ", myhttps);
			registerPort(port);
		}
		HttpClient client = new HttpClient();
		// client.getHostConfiguration().setProxy("10.128.179.18", 8118);
		// client.getParams().setAuthenticationPreemptive(true);
		client.getParams().setSoTimeout(timeout);
		// client.getParams().setConnectionManagerTimeout(timeout);
		try {
			int status = client.executeMethod(postMethod);
			InputStream is = postMethod.getResponseBodyAsStream();
			String responseBody = IOUtils.toString(is, characterSet);
			result.setStatus(status);
			result.setResponseBody(responseBody);
		}
		catch (Exception ex) {
			throw new SecurityException(ex.getMessage());
		}
		finally {
			postMethod.releaseConnection();
		}
		return result;
	}

	public HttpSendResult postRequest(String url, Map<String, String> params, int timeout) throws SecurityException {
		return postRequest(url, params, timeout, "UTF-8");
	}

	/**
	 * doGet
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws SecurityException
	 */
	public HttpSendResult getRequest(String url, Map<String, String> params, int timeout, String characterSet) throws SecurityException {
		if (characterSet == null || "".equals(characterSet)) {
			characterSet = "UTF-8";
		}
		HttpSendResult result = new HttpSendResult();
		Integer port = this.getPort(url);
		if (isRegisterPort(port)) {
			Protocol myhttps = new Protocol("https", new SimpleHttpsSocketFactory(), port);
			Protocol.registerProtocol("https ", myhttps);
			registerPort(port);
		}
		url = this.appendUrlParam(url, params);
		HttpClient httpclient = new HttpClient();
		GetMethod httpget = new GetMethod(url);
		try {
			int status = httpclient.executeMethod(httpget);
			InputStream is = httpget.getResponseBodyAsStream();
			String responseBody = IOUtils.toString(is, characterSet);
			result.setStatus(status);
			result.setResponseBody(responseBody);
		}
		catch (Exception ex) {
			throw new SecurityException(ex.getMessage());
		}
		finally {
			httpget.releaseConnection();
		}
		return result;
	}

	public HttpSendResult getRequest(String url, Map<String, String> params, int timeout) throws SecurityException {
		return getRequest(url, params, timeout, "UTF-8");
	}

	private boolean isRegisterPort(Integer port) {
		return registerPortList.get(port) != null;
	}

	private void registerPort(Integer port) {
		registerPortList.put(port, port);
	}

	private Integer getPort(String uri) {
		try {
			URL url = new URL(uri);
			int port = url.getPort();
			if (port == -1) {
				if (uri.indexOf("https://") == 0) {
					port = 443;
				}
				else {
					port = 80;
				}
			}
			return port;
		}
		catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	private NameValuePair[] createNameValuePair(Map<String, String> params) {
		NameValuePair[] pairs = new NameValuePair[params.size()];
		int index = 0;
		for (String key : params.keySet()) {
			pairs[index++] = new NameValuePair(key, params.get(key));
		}
		return pairs;
	}

	private String appendUrlParam(String url, Map<String, String> params) {
		String result = "";
		if (url.contains("?") && url.contains("=")) {
			result = url + "&";
		}
		else {
			result = url + "?";
		}
		for (String key : params.keySet()) {
			result = result + key + "=" + params.get(key) + "&";
		}
		if (result.charAt(result.length() - 1) == '&') {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
}
