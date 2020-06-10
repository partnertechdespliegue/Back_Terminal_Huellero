package com.terminal.app.util;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.terminal.app.model.Asistencia;
import com.terminal.app.model.HuelleroDigital;
import com.terminal.app.model.ResponseHuellaDigital;

public class BackCentral {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	
	public Integer marcarAsistencia(Asistencia asistencia) {
		Integer respuesta_conexion = 0;
		
		ResponseHuellaDigital rpta_conexion = new ResponseHuellaDigital();

		String url = Constantes.URL_BACK + "asistencia/registrar";

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		// request entity is created with request body and headers
		HttpEntity<Asistencia> requestEntity = new HttpEntity<Asistencia>(asistencia, requestHeaders);
		ResponseEntity<ResponseHuellaDigital> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResponseHuellaDigital.class);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			rpta_conexion = responseEntity.getBody();
			if (rpta_conexion.getEstado() == 1) {
				respuesta_conexion = 1;
			}
		} else {
			respuesta_conexion = 0;
		}
		return respuesta_conexion;
	}
	
	public HuelleroDigital registrarHuellero(HuelleroDigital huelleroDigital) {
		ResponseHuellaDigital rpta_conexion = new ResponseHuellaDigital();

		String url = Constantes.URL_BACK + "huellaDigital/registrar";

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<HuelleroDigital> requestEntity = new HttpEntity<HuelleroDigital>(huelleroDigital, requestHeaders);
		ResponseEntity<ResponseHuellaDigital> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResponseHuellaDigital.class);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			rpta_conexion = responseEntity.getBody();
			return (HuelleroDigital) rpta_conexion.getDefaultObj();
		} else {
			return null;
		}
	}
	
	public HuelleroDigital modificarHuellero(HuelleroDigital huelleroDigital) {
		ResponseHuellaDigital rpta_conexion = new ResponseHuellaDigital();

		String url = Constantes.URL_BACK + "huellaDigital/modificar";

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<HuelleroDigital> requestEntity = new HttpEntity<HuelleroDigital>(huelleroDigital, requestHeaders);
		ResponseEntity<ResponseHuellaDigital> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResponseHuellaDigital.class);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			rpta_conexion = responseEntity.getBody();
			return (HuelleroDigital) rpta_conexion.getDefaultObj();
		} else {
			return null;
		}
	}

}
