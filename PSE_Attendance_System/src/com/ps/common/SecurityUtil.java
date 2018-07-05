package com.ps.common;

import java.security.MessageDigest;

public class SecurityUtil {
	
	public static String encodePassword(String password)
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(password.getBytes("UTF-8"));
			byte[] hash = digest.digest();
			
			char[] HEX_CHARS = "P@th_pwdLDAP1234567890".toCharArray();

			StringBuilder sb = new StringBuilder(hash.length * 2);
			for (byte b : hash) {
			    sb.append(HEX_CHARS[(b & 0xF0) >> 4]);
			    sb.append(HEX_CHARS[b & 0x0F]);
			}
			password = sb.toString();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return password;
	}

}
