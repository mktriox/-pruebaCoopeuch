package com.coopeuch.pruebatecnica.service.impl;

import com.coopeuch.pruebatecnica.repository.TareaDAO;
import com.coopeuch.pruebatecnica.repository.model.Tarea;
import com.coopeuch.pruebatecnica.service.vo.TareaVO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class TareaServiceImplTest {

  @Mock
  private TareaDAO tareaDAO;

  @InjectMocks
  private TareaServiceImpl tareaService;

  @BeforeEach
  public void setUp() {
   MockitoAnnotations.openMocks(this);
   tareaService = new TareaServiceImpl(tareaDAO);
  }


 @Test
 public void testList() {
   Tarea tarea1= new Tarea();
   tarea1.setIdentificador(1L);
   tarea1.setDescripcion("Tarea 1");
   tarea1.setFechaCreacion(new Date());
   tarea1.setVigente(false);
   Tarea tarea2= new Tarea();
   tarea2.setIdentificador(2L);
   tarea2.setDescripcion("Tarea 2");
   tarea2.setFechaCreacion(new Date());
   tarea2.setVigente(true);
   List<Tarea> tareas = new ArrayList<>();
   // Definir el comportamiento del mock
   when(tareaDAO.listar()).thenReturn(tareas);

   // Ejecutar el método que se está probando
   List<TareaVO> result = tareaService.listar();

   // Verificar el resultado esperado
   assertEquals(tareas.size(), result.size());
 }
/* public void listar(){
  // Given
   Tarea tarea1= new Tarea();
   tarea1.setIdentificador(1L);
   tarea1.setDescripcion("Tarea 1");
   tarea1.setFechaCreacion(new Date());
   tarea1.setVigente(false);
   Tarea tarea2= new Tarea();
   tarea2.setIdentificador(2L);
   tarea2.setDescripcion("Tarea 2");
   tarea2.setFechaCreacion(new Date());
   tarea2.setVigente(true);
  List<Tarea> tareas = new ArrayList<>();

  tareas.add(tarea1);
  tareas.add(tarea2);
  when(tareaDAO.listar()).thenReturn(tareas);

  // When
  List<TareaVO> tareaVOs = tareaService.listar();

  // Then
  assertEquals(tareas.size(), tareaVOs.size());

  List<TareaVO> expectedTareaVOs = tareas.stream()
      .map(TareaVO::build)
      .collect(Collectors.toList());
  assertEquals(expectedTareaVOs, tareaVOs);
 }*/

 @Test
 public void eliminarTarea_ValidId_ReturnsTareaVO() {
  // Arrange
  Long id = 1L;
  Tarea tarea = new Tarea();
  tarea.setIdentificador(id);
  when(tareaDAO.eliminar(id)).thenReturn(tarea);
  TareaServiceImpl tareaServiceImpl = new TareaServiceImpl(tareaDAO);

  // Act
  TareaVO tareaEliminada = tareaService.eliminar(id);

  // Assert
  assertNotNull(tareaEliminada);
  assertEquals(id, tareaEliminada.getIdentificador());
  verify(tareaDAO).eliminar(id);
 }

 @Test
 public void eliminarTarea_InvalidId_ThrowsException() {
  // Arrange
  Long id = -1L;
  TareaServiceImpl tareaServiceImpl = new TareaServiceImpl(tareaDAO);

  // Act & Assert
  ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
   tareaService.eliminar(id);
  });
  assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
  assertEquals("No se encuentra el identificador a eliminar", exception.getReason());
 }

  @Test
  void testFindByIdSuccess() {
    Tarea tarea = new Tarea();
    tarea.setIdentificador(1L);
    when(tareaDAO.findById(ArgumentMatchers.anyLong())).thenReturn(tarea);
    TareaVO tareaVO = tareaService.findByID(1L);
    Assertions.assertEquals("1", tareaVO.getIdentificador());
  }

  @Test
  void testFindByIdNotFound() {
    when(tareaDAO.findById(ArgumentMatchers.anyLong())).thenReturn(null);
    Assertions.assertThrows(ResponseStatusException.class, () -> {
      tareaService.findByID(1L);
    });
  }

  @Test
  void testFindByIdIdentificadorNull() {
    Tarea tarea = new Tarea();
    when(tareaDAO.findById(ArgumentMatchers.anyLong())).thenReturn(tarea);
    Assertions.assertThrows(ResponseStatusException.class, () -> {
      tareaService.findByID(1L);
    });
  }

  @Test
  void testAgregar() {
    TareaVO tareaVO = new TareaVO();
    tareaVO.setIdentificador(1L);
    tareaVO.setDescripcion("Tarea de prueba");
    tareaVO.setFechaCreacion(new Date());
    tareaVO.setVigente(true);
    Tarea tareaMock = new Tarea();
    tareaMock.setIdentificador(1L);
    tareaMock.setDescripcion("Tarea de prueba");
    tareaMock.setFechaCreacion(new Date());
    tareaMock.setVigente(true);
    when(tareaDAO.guardar(ArgumentMatchers.any(Tarea.class))).thenReturn(tareaMock);
    TareaVO resultado = tareaService.agregar(tareaVO);
    Assertions.assertNotNull(resultado);
    Assertions.assertEquals(tareaVO.getIdentificador(), resultado.getIdentificador());
    Assertions.assertEquals(tareaVO.getDescripcion(), resultado.getDescripcion());
    Assertions.assertEquals(tareaVO.getFechaCreacion(), resultado.getFechaCreacion());
    Assertions.assertEquals(tareaVO.getVigente(), resultado.getVigente());
  }

  @Test
  void testActualizaTareaExistente() {
    TareaVO tareaVO = new TareaVO();
    tareaVO.setIdentificador(1L);
    tareaVO.setDescripcion("Tarea de prueba");
    tareaVO.setFechaCreacion(new Date());
    tareaVO.setVigente(true);
    Tarea tareaMock = new Tarea();
    tareaMock.setIdentificador(1L);
    tareaMock.setDescripcion("Tarea de prueba");
    tareaMock.setFechaCreacion(new Date());
    tareaMock.setVigente(false);
    when(tareaDAO.findById(ArgumentMatchers.anyLong())).thenReturn(tareaMock);
    when(tareaDAO.actualizar(ArgumentMatchers.any(Tarea.class))).thenReturn(tareaMock);
    TareaVO resultado = tareaService.actualiza(tareaVO);
    Assertions.assertNotNull(resultado);
    Assertions.assertEquals(tareaVO.getIdentificador(), resultado.getIdentificador());
    Assertions.assertEquals(tareaVO.getDescripcion(), resultado.getDescripcion());
    Assertions.assertEquals(tareaVO.getFechaCreacion(), resultado.getFechaCreacion());
    Assertions.assertEquals(tareaVO.getVigente(), resultado.getVigente());
  }

  @Test
  void testActualizaTareaNoExistente() {
    TareaVO tareaVO = new TareaVO();
    tareaVO.setIdentificador(null);
    ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
      tareaService.actualiza(tareaVO);
    });
    Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
  }

  @Test
  void testActualizaTareaIdentificadorNoExistente() {
    TareaVO tareaVO = new TareaVO();
    tareaVO.setIdentificador(2L);
    when(tareaDAO.findById(ArgumentMatchers.anyLong())).thenReturn(null);
    ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
      tareaService.actualiza(tareaVO);
    });
    Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
  }

}
