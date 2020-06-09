package com.terminal.app.model;

public class PdoMes {

	private Integer idPdoMes;
	private String descripcion;
	private String abrev;
	private Integer diasFeriadoCalend;
	private Integer cantidadDias;
	private String txtDiasFeriados;
	
	public Integer getIdPdoMes() {
		return idPdoMes;
	}
	public void setIdPdoMes(Integer idPdoMes) {
		this.idPdoMes = idPdoMes;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAbrev() {
		return abrev;
	}
	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}
	public Integer getDiasFeriadoCalend() {
		return diasFeriadoCalend;
	}
	public void setDiasFeriadoCalend(Integer diasFeriadoCalend) {
		this.diasFeriadoCalend = diasFeriadoCalend;
	}
	public Integer getCantidadDias() {
		return cantidadDias;
	}
	public void setCantidadDias(Integer cantidadDias) {
		this.cantidadDias = cantidadDias;
	}
	public String getTxtDiasFeriados() {
		return txtDiasFeriados;
	}
	public void setTxtDiasFeriados(String txtDiasFeriados) {
		this.txtDiasFeriados = txtDiasFeriados;
	}

}
