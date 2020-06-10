package com.terminal.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.terminal.app.model.HuelleroDigital;
import com.terminal.app.util.BackCentral;

@SpringBootApplication
public class HuelleroTerminalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HuelleroTerminalApplication.class, args);
		crearFolder();
		try {
			registrarHuellero();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static int leerTxt() throws IOException {
		File txt = new File("src/main/resources/HuelleroDigital/huellero.txt");
		FileReader reader = new FileReader(txt);
		BufferedReader bReader = new BufferedReader(reader);
		return Integer.parseInt(bReader.readLine());
	}

	private static void crearFolder() {
		File huellero = new File("src/main/resources/HuelleroDigital");
		if (!huellero.exists()) {
			huellero.mkdir();
		}
	}

	private static void registrarHuellero() throws IOException {
		File txt = new File("src/main/resources/HuelleroDigital/huellero.txt");

		BackCentral backCentral = new BackCentral();
		HuelleroDigital huelleroDigital = new HuelleroDigital(ipPrivada(), ipPublica());

		if (txt.exists()) {
			int id = leerTxt();
			huelleroDigital.setIidHuelleroDigital(id);
			backCentral.modificarHuellero(huelleroDigital);
			System.out.println("Modifico " + id);
		} else {
			FileWriter writer = new FileWriter(txt);
			HuelleroDigital resp = backCentral.registrarHuellero(huelleroDigital);
			writer.write(resp.getIidHuelleroDigital().toString());
			writer.close();
			System.out.println("Registro");
		}
	}

	private static String ipPrivada() {
		String privateIp = "";
		try {
			privateIp = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return privateIp;
	}

	private static String ipPublica() {
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
