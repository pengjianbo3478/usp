/**  
 * @Title:  SimpleHttpsSocketFactory.java   
 * @Package cn.obanks.usp.utils   
 * @Description:TODO(用一句话描述该文件做什么)   
 * @author: pengjianbo3478    
 * @date:   2015年12月3日 下午4:57:27   
 * @version V1.0     
 */
package cn.obanks.usp.utils;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClientError;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;

public class SimpleHttpsSocketFactory implements ProtocolSocketFactory {
	private SSLContext sslcontext = null;

	public SimpleHttpsSocketFactory() {
	}

	private SSLContext createEasySSLContext() {
		try {
			X509TrustManager trustMgr = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate ax509certificate[], String s) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate ax509certificate[], String s) throws CertificateException {
				}

				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			TrustManager trustMgrs[] = { trustMgr };
			SSLContext context = SSLContext.getInstance("SSL");
			//SSLContext context = SSLContext.getInstance("TLSv1"); 
			context.init(null, trustMgrs, null);
			return context;
		}
		catch (Exception e) {
			throw new HttpClientError(e.toString());
		}
	}

	/**
	 * Retrieves SSL context.
	 * 
	 * @return SSLContext.
	 */
	private SSLContext getSSLContext() {
		if (this.sslcontext == null) {
			this.sslcontext = createEasySSLContext();
		}
		return this.sslcontext;
	}

	/**
	 * @see SecureProtocolSocketFactory#createSocket(java.lang.String, int,
	 *      java.net.InetAddress, int)
	 */
	public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort) throws IOException, UnknownHostException {
		return getSSLContext().getSocketFactory().createSocket(host, port, clientHost, clientPort);
	}

	public Socket createSocket(final String host, final int port, final InetAddress localAddress, final int localPort, final HttpConnectionParams params) throws IOException, UnknownHostException, ConnectTimeoutException {
		if (params == null) {
			throw new IllegalArgumentException("Parameters may not be null");
		}
		int timeout = params.getConnectionTimeout();
		SocketFactory socketfactory = getSSLContext().getSocketFactory();
		if (timeout == 0) {
			return socketfactory.createSocket(host, port, localAddress, localPort);
		}
		else {
			Socket socket = socketfactory.createSocket();
			SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
			SocketAddress remoteaddr = new InetSocketAddress(host, port);
			socket.bind(localaddr);
			socket.connect(remoteaddr, timeout);
			return socket;
		}
	}

	public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
		return getSSLContext().getSocketFactory().createSocket(host, port);
	}

	public boolean equals(Object obj) {
		return ((obj != null) && obj.getClass().equals(SSLSocketFactory.class));
	}

	public int hashCode() {
		return SimpleHttpsSocketFactory.class.hashCode();
	}
}
