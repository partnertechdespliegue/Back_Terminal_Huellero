package com.terminal.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.terminal.app.model.HuelleroDigital;
import com.terminal.app.util.BackCentral;

@Configuration
public class HuelleroTerminal {
	
	public void crearFolder() {
		File huellero = new File("src/main/resources/HuelleroDigital");
		if (!huellero.exists()) {
			huellero.mkdir();
		}
	}

	public void gestionarHuellero() throws IOException {
		File txt = new File("src/main/resources/HuelleroDigital/huellero.txt");

		BackCentral backCentral = new BackCentral();
		HuelleroDigital huelleroDigital = new HuelleroDigital(ipPrivada(), ipPublica());

		if (txt.exists()) {
			int id = leerTxt();
			huelleroDigital.setIidHuelleroDigital(id);
			backCentral.modificarHuellero(huelleroDigital);
			System.out.println("Actualizado -> id: " + id);
		} else {
			FileWriter writer = new FileWriter(txt);
			HuelleroDigital resp = backCentral.registrarHuellero(huelleroDigital);
			writer.write(resp.getIidHuelleroDigital().toString());
			writer.close();
			System.out.println("Registrado -> id " + resp.getIidHuelleroDigital());
		}
	}
	
	private int leerTxt() throws IOException {
		File txt = new File("src/main/resources/HuelleroDigital/huellero.txt");
		FileReader reader = new FileReader(txt);
		BufferedReader bReader = new BufferedReader(reader);
		return Integer.parseInt(bReader.readLine());
	}

	private  String ipPrivada() {
		String privateIp = "";
		try {
			privateIp = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return privateIp;
	}

	private  String ipPublica() {
		String publicIp = "";
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			publicIp = in.readLine();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return publicIp;
	}
}
