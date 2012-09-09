/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.dao;

import br.com.javamagazine.onzevencedor.entity.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ALEX
 */
@Repository("funcionarioDao")
public class FuncionarioDao {
    
    @PersistenceContext
    protected EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Funcionario> findAll() {
        String jpql = " SELECT f from Funcionario f order by f.nome";
        Query query = entityManager.createQuery(jpql);
        List<Funcionario> list = (List<Funcionario>) query.getResultList();
        if(list==null){
            list = new ArrayList<Funcionario>() ; 
        }
        return list;
    }
    
    @Transactional
    public Funcionario create(Funcionario entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    public void update(Funcionario entity) {
        entityManager.merge(entity);
    }

    @Transactional
    public void delete(Funcionario entity) {
        entity = entityManager.find(Funcionario.class, entity.getId());
        entityManager.remove(entity);
    }
}
