package cn.obanks.usp.utils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("GBK"));
			byte[] b = md.digest();
			StringBuffer buf = new StringBuffer();
			for (int offset = 0; offset < b.length; offset++) {
				int i = b[offset];
				if (i < 0) i += 256;
				if (i < 16) buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		}
		catch (NoSuchAlgorithmException e) {
			return null;
		}
		catch (UnsupportedEncodingException e) {
		}
		return null;
	}
}
