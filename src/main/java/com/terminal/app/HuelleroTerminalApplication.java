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
import org.springframework.scheduling.annotation.EnableScheduling;

import com.terminal.app.model.HuelleroDigital;
import com.terminal.app.util.BackCentral;

@SpringBootApplication
@EnableScheduling
public class HuelleroTerminalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HuelleroTerminalApplication.class, args);
		HuelleroTerminal terminal = new HuelleroTerminal();
		terminal.crearFolder();
		try {
			terminal.gestionarHuellero();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
