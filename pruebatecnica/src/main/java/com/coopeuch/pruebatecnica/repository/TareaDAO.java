package com.coopeuch.pruebatecnica.repository;

import com.coopeuch.pruebatecnica.repository.model.Tarea;
import java.util.List;

public interface TareaDAO {

  List<Tarea> listar();
  Tarea eliminar(Long id);
  Tarea findById( Long id);
  Tarea guardar(Tarea t);
  Tarea actualizar(Tarea t);

}
