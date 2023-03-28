package com.coopeuch.pruebatecnica.repository.impl;

import com.coopeuch.pruebatecnica.repository.TareaDAO;
import com.coopeuch.pruebatecnica.repository.model.Tarea;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TareaDAOImpl implements TareaDAO {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Tarea> listar() {
      return em.createQuery("Select t from Tarea t", Tarea.class)
        .getResultList();
  }

  @Override
  public Tarea eliminar(Long id) {
    Tarea tarea = em.find(Tarea.class, id);
    if(tarea != null){
      em.remove(id);
    }
    return tarea;
  }

  @Override
  public Tarea findById( Long id){
    return em.find(Tarea.class, id);
  }

  @Override
  @Transactional
  public Tarea guardar(Tarea t) {
    em.persist(t);
    return t;
  }

  @Override
  @Transactional
  public Tarea actualizar(Tarea t) {
    return em.merge(t);
  }
}
