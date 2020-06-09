package com.terminal.app.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Trabajador {

	private Integer idTrabajador;
	private String nombres;
	private String apePater;
	private String apeMater;
	private String nroDoc;
	private Timestamp fecNac;
	private String sexo;
	private String correo;
	private String nroCel;
	private String direccion;
	private String nomZona;
	private String referencia;
	private Integer nroHij;
	private Timestamp fecRegPens;
	private String nroCuspp;
	private Timestamp fecIngrSalud;
	private String nroEssalud;
	private Integer essaludVida;
	private Integer afilAseguPens;
	private Integer epsServProp;
	private Integer comiMixta;
	private Boolean estado;
	private Empresa empresa;
	
	/*private TipoDoc tipoDoc;
	private Pais pais;
	private EstadoCivil estadoCivil;
	private Departamento departamento;
	private Provincia provincia;
	private Distrito distrito;
	private TipoZona tipoZona;
	private NivelEdu nivelEdu;
	private Ocupacion ocupacion;
	private Afp afp;
	private Eps eps;
	private RegSalud regSalud;
	private Situacion situacion;
	private Horario horario;*/
	
	public Integer getIdTrabajador() {
		return idTrabajador;
	}
	public void setIdTrabajador(Integer idTrabajador) {
		this.idTrabajador = idTrabajador;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApePater() {
		return apePater;
	}
	public void setApePater(String apePater) {
		this.apePater = apePater;
	}
	public String getApeMater() {
		return apeMater;
	}
	public void setApeMater(String apeMater) {
		this.apeMater = apeMater;
	}
	public String getNroDoc() {
		return nroDoc;
	}
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}
	public Timestamp getFecNac() {
		return fecNac;
	}
	public void setFecNac(Timestamp fecNac) {
		this.fecNac = fecNac;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNroCel() {
		return nroCel;
	}
	public void setNroCel(String nroCel) {
		this.nroCel = nroCel;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNomZona() {
		return nomZona;
	}
	public void setNomZona(String nomZona) {
		this.nomZona = nomZona;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public Integer getNroHij() {
		return nroHij;
	}
	public void setNroHij(Integer nroHij) {
		this.nroHij = nroHij;
	}
	public Timestamp getFecRegPens() {
		return fecRegPens;
	}
	public void setFecRegPens(Timestamp fecRegPens) {
		this.fecRegPens = fecRegPens;
	}
	public String getNroCuspp() {
		return nroCuspp;
	}
	public void setNroCuspp(String nroCuspp) {
		this.nroCuspp = nroCuspp;
	}
	public Timestamp getFecIngrSalud() {
		return fecIngrSalud;
	}
	public void setFecIngrSalud(Timestamp fecIngrSalud) {
		this.fecIngrSalud = fecIngrSalud;
	}
	public String getNroEssalud() {
		return nroEssalud;
	}
	public void setNroEssalud(String nroEssalud) {
		this.nroEssalud = nroEssalud;
	}
	public Integer getEssaludVida() {
		return essaludVida;
	}
	public void setEssaludVida(Integer essaludVida) {
		this.essaludVida = essaludVida;
	}
	public Integer getAfilAseguPens() {
		return afilAseguPens;
	}
	public void setAfilAseguPens(Integer afilAseguPens) {
		this.afilAseguPens = afilAseguPens;
	}
	public Integer getEpsServProp() {
		return epsServProp;
	}
	public void setEpsServProp(Integer epsServProp) {
		this.epsServProp = epsServProp;
	}
	public Integer getComiMixta() {
		return comiMixta;
	}
	public void setComiMixta(Integer comiMixta) {
		this.comiMixta = comiMixta;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}	
	
}
