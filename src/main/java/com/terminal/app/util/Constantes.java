package com.terminal.app.util;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Constantes {
	
	public static String obtenerRuta() {
		String privateIp ="";
		try {
			privateIp = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String url ="http://"+ privateIp +":5000/api/";
		return url;
	}

}
