package com.coopeuch.pruebatecnica.service.vo;

import com.coopeuch.pruebatecnica.repository.model.Tarea;
import java.io.Serializable;
import java.util.Date;

public class TareaVO implements Serializable {
  private static final long serialVersionUID = -1529511618839113753L;

  private Long identificador;
  private String descripcion;
  private Date fechaCreacion;
  private Boolean vigente;

  public TareaVO(){}

  public TareaVO(Tarea t){
    this.identificador = t.getIdentificador();
    this.descripcion = t.getDescripcion();
    this.fechaCreacion = t.getFechaCreacion();
    this.vigente = t.getVigente();
  }

  public static TareaVO build(Tarea t){
    if(t == null){
      return null;
    }else {
      return new TareaVO(t);
    }
  }

  public Long getIdentificador() {
    return identificador;
  }

  public void setIdentificador(Long identificador) {
    this.identificador = identificador;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Date getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public Boolean getVigente() {
    return vigente;
  }

  public void setVigente(Boolean vigente) {
    this.vigente = vigente;
  }

}
