package com.ht.baselib.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {


	public static String encode(String password) {

		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] encrypts = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();

			for (byte b : encrypts) {
				int num = b & 0xff;
				String str = Integer.toHexString(num);
				if (str.length() == 1) {
					str = "0" + str;
				}
				sb.append(str);
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
