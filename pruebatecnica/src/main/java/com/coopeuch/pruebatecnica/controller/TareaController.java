package com.coopeuch.pruebatecnica.controller;

import com.coopeuch.pruebatecnica.service.TareaService;
import com.coopeuch.pruebatecnica.service.vo.TareaVO;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping(value="/Tarea/")
public class TareaController {

  @Autowired
  private TareaService tareaService;

  @GetMapping(value = "buscarTarea/{id}")
  public @ResponseBody ResponseEntity<TareaVO> getTarea(@PathVariable Long id){
    HttpStatus httpStatus = HttpStatus.OK;
    TareaVO tareaVO = tareaService.findByID(id);
    return new ResponseEntity<>(tareaVO, httpStatus);
  }

  @GetMapping(value = "ListadoTareas")
  public @ResponseBody
  ResponseEntity<List<TareaVO>> listado(){
    HttpStatus httpStatus = HttpStatus.OK;
    List<TareaVO> tareaVOList = tareaService.listar();
    return new ResponseEntity<>(tareaVOList, httpStatus);
  }

  @PostMapping(value= "AgregarTarea")
  public @ResponseBody
  ResponseEntity <TareaVO> agregarOActualizar(@RequestBody TareaVO tareaVO){
    HttpStatus httpStatus = HttpStatus.OK;
    return new ResponseEntity<>(tareaService.agregar(tareaVO),httpStatus);
  }

  @PostMapping(value = "EliminarTarea/{id}")
  public @ResponseBody
  ResponseEntity<TareaVO> eliminarTarea(@PathVariable Long id){
    HttpStatus httpStatus = HttpStatus.OK;
    TareaVO tareaVO = tareaService.eliminar(id);
    return new ResponseEntity<>(tareaVO, httpStatus);
  }

  @PostMapping(value="updateHerramienta/{id}")
  public @ResponseBody
  ResponseEntity<TareaVO> update(@RequestBody TareaVO tareaVO){
    HttpStatus httpStatus = HttpStatus.OK;
    return new ResponseEntity<>(tareaService.actualiza(tareaVO),httpStatus);
  }
}
