package com.terminal.app.model;

public class HuelleroDigital {
	
	private Integer iidHuelleroDigital;
	private String sipPrivada;
	private String sipPublica;
	private Empresa empresa;
	
	public HuelleroDigital() {
		super();
	}
	
	public HuelleroDigital(String sipPrivada, String sipPublica) {
		super();
		this.sipPrivada = sipPrivada;
		this.sipPublica = sipPublica;
	}
	
	public Integer getIidHuelleroDigital() {
		return iidHuelleroDigital;
	}
	public void setIidHuelleroDigital(Integer iidHuelleroDigital) {
		this.iidHuelleroDigital = iidHuelleroDigital;
	}
	public String getSipPrivada() {
		return sipPrivada;
	}
	public void setSipPrivada(String sipPrivada) {
		this.sipPrivada = sipPrivada;
	}
	public String getSipPublica() {
		return sipPublica;
	}
	public void setSipPublica(String sipPublica) {
		this.sipPublica = sipPublica;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
