package com.coopeuch.pruebatecnica.service.impl;

import com.coopeuch.pruebatecnica.repository.TareaDAO;
import com.coopeuch.pruebatecnica.repository.model.Tarea;
import com.coopeuch.pruebatecnica.service.TareaService;
import com.coopeuch.pruebatecnica.service.vo.TareaVO;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TareaServiceImpl implements TareaService {
  @Autowired
  private TareaDAO tareaDAO;

  public TareaServiceImpl(TareaDAO tareaDAO) {
  }

  private Tarea buildTarea(TareaVO tareaVO){
    Tarea tarea = new Tarea();
    tarea.setIdentificador(tareaVO.getIdentificador());
    tarea.setDescripcion(tareaVO.getDescripcion());
    tarea.setFechaCreacion(tareaVO.getFechaCreacion());
    tarea.setVigente(tareaVO.getVigente());
    return tarea;
  }

  @Override
  public List<TareaVO> listar() {
    return this.tareaDAO
        .listar()
        .stream()
        .map(TareaVO::build)
        .collect(Collectors.toList());
  }


  @Override
  @Transactional
  public TareaVO eliminar(Long id) {
    Tarea tarea = new Tarea();
    if(tarea != null && tarea.getIdentificador() > 0){
      return TareaVO.build(tareaDAO.eliminar(id));
    }else{
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentra el identificador a eliminar");
    }
  }

  @Override
  public TareaVO findByID(Long id) {
    Tarea tarea = tareaDAO.findById(id);
    if(tarea != null && tarea.getIdentificador() != null )
      return TareaVO.build(tarea);
    else
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encuentra el identificador");
  }

  @Override
  public TareaVO agregar(TareaVO tareaVO) {
      Tarea tarea = new Tarea();
      tarea.setIdentificador(tareaVO.getIdentificador());
      tarea.setDescripcion(tareaVO.getDescripcion());
      tarea.setFechaCreacion(tareaVO.getFechaCreacion());
      tarea.setVigente(tareaVO.getVigente());
      return tareaVO.build(tareaDAO.guardar(tarea));
  }

  @Override
  @Transactional
  public TareaVO actualiza(TareaVO tareaVO) {
    if(tareaVO.getIdentificador() != null){
      Tarea tarea= tareaDAO.findById(tareaVO.getIdentificador());
      if(tarea != null && tarea.getIdentificador() != null)
        return tareaVO.build(tareaDAO.actualizar(buildTarea(tareaVO)));
      else
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se puede actualizar");
    }else{
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la tarea para actualizar");
    }
  }
}
