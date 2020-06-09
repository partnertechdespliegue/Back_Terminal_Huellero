package com.terminal.app.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Asistencia {
	
	private Integer idAsistencia;
	private Date fecha;
	private Timestamp horIniDia;
	private Timestamp horFinDia;
	private Timestamp horIniAlmu;
	private Timestamp horFinAlmu;	
	private Integer tipoAsistencia;
	private Trabajador trabajador;
	private PdoAno pdoAno;
	private PdoMes pdoMes;
	
	public Integer getIdAsistencia() {
		return idAsistencia;
	}
	public void setIdAsistencia(Integer idAsistencia) {
		this.idAsistencia = idAsistencia;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Timestamp getHorIniDia() {
		return horIniDia;
	}
	public void setHorIniDia(Timestamp horIniDia) {
		this.horIniDia = horIniDia;
	}
	public Timestamp getHorFinDia() {
		return horFinDia;
	}
	public void setHorFinDia(Timestamp horFinDia) {
		this.horFinDia = horFinDia;
	}
	public Timestamp getHorIniAlmu() {
		return horIniAlmu;
	}
	public void setHorIniAlmu(Timestamp horIniAlmu) {
		this.horIniAlmu = horIniAlmu;
	}
	public Timestamp getHorFinAlmu() {
		return horFinAlmu;
	}
	public void setHorFinAlmu(Timestamp horFinAlmu) {
		this.horFinAlmu = horFinAlmu;
	}
	public Integer getTipoAsistencia() {
		return tipoAsistencia;
	}
	public void setTipoAsistencia(Integer tipoAsistencia) {
		this.tipoAsistencia = tipoAsistencia;
	}
	public Trabajador getTrabajador() {
		return trabajador;
	}
	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}
	public PdoAno getPdoAno() {
		return pdoAno;
	}
	public void setPdoAno(PdoAno pdoAno) {
		this.pdoAno = pdoAno;
	}
	public PdoMes getPdoMes() {
		return pdoMes;
	}
	public void setPdoMes(PdoMes pdoMes) {
		this.pdoMes = pdoMes;
	}

}
