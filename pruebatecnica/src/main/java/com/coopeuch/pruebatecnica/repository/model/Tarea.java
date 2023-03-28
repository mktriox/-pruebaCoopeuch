package com.coopeuch.pruebatecnica.repository.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name ="tarea", schema = "public")
public class Tarea implements Serializable {
  private static final long serialVersionUID= -5250344242497043207L;

  @Id
  @Column(name = "identificador")
  @TableGenerator(name = "id_tarea", allocationSize = 1)
  @GeneratedValue(generator = "tarea", strategy = GenerationType.IDENTITY)
  private Long identificador;
  @Column(name = "descripcion")
  private String descripcion;
  @Column(name = "fecha_creacion")
  private Date fechaCreacion;
  @Column(name = "vigente")
  private Boolean vigente;

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
