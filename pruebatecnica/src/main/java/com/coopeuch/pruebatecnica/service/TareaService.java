package com.coopeuch.pruebatecnica.service;

import com.coopeuch.pruebatecnica.repository.model.Tarea;
import com.coopeuch.pruebatecnica.service.vo.TareaVO;
import java.util.List;

public interface TareaService {

  List<TareaVO> listar();
  TareaVO eliminar(Long id);
  TareaVO findByID(Long id);
  TareaVO agregar(TareaVO tareaVO);
  TareaVO actualiza(TareaVO t);

}
