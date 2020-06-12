package com.terminal.app.util;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Constantes {
	
	public static final Integer valTransaccionNoEncontro = 0; // No se encontro registro buscado
	public static final Integer valTransaccionOk = 1; // Toda la transaccion se realizo correctamente
	public static final Integer valTransaccionError = 2; // Fallo una parte de la transaccion
	
	public static final String URL_BACK = "http://localhost:2000/api/";

}
