/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.dao;

import br.com.javamagazine.onzevencedor.entity.Comissao;
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
@Repository("comissaoDao")
public class ComissaoDao {

    @PersistenceContext
    protected EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Comissao> findAll() {
        String jpql = " SELECT c from Comissao c order by c.dtaComissao";
        Query query = entityManager.createQuery(jpql);
        List<Comissao> atletas = (List<Comissao>) query.getResultList();
        return atletas;
    }

    @Transactional
    public Comissao create(Comissao entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    public void update(Comissao entity) {
        entityManager.merge(entity);
    }

    @Transactional
    public void delete(Comissao entity) {
        entity = entityManager.find(Comissao.class, entity.getId());
        entityManager.remove(entity);
    }
}
