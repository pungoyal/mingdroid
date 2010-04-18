package com.thoughtworks.mingle;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Constants {
	public static String APPLICATION_KEY = "minmurs";
	public static String USERNAME_KEY = "user";
	public static String PASSWORD_KEY = "password";
	public static String SERVER_KEY = "server";
	public static String PROJECT_KEY = "project";

	public static int lookupHost(String hostname) {
		InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getByName(hostname);
		} catch (UnknownHostException e) {
			return -1;
		}
		byte[] addrBytes;
		int ipAddress;
		addrBytes = inetAddress.getAddress();
		ipAddress = ((addrBytes[3] & 0xff) << 24) | ((addrBytes[2] & 0xff) << 16) | ((addrBytes[1] & 0xff) << 8)
				| (addrBytes[0] & 0xff);
		return ipAddress;
	}

}
