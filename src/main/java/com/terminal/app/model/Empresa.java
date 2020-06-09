package com.terminal.app.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Empresa {
	
	private Integer idEmpresa;
	private String razonSocial;
	private String ruc;
	private String ubicacion;
	private Integer estado;
	private Timestamp fechaRegistro;
	private Integer sector;
	private String urlImagen;
	private String logo;
	private Double companyLimit;
	private Integer limiteAutorizacion;
	
	/*private TipoEmpresa TipoEmp;
	private RegimenTributario regTrib;*/ 
	
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Integer getSector() {
		return sector;
	}
	public void setSector(Integer sector) {
		this.sector = sector;
	}
	public String getUrlImagen() {
		return urlImagen;
	}
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Double getCompanyLimit() {
		return companyLimit;
	}
	public void setCompanyLimit(Double companyLimit) {
		this.companyLimit = companyLimit;
	}
	public Integer getLimiteAutorizacion() {
		return limiteAutorizacion;
	}
	public void setLimiteAutorizacion(Integer limiteAutorizacion) {
		this.limiteAutorizacion = limiteAutorizacion;
	}

}
