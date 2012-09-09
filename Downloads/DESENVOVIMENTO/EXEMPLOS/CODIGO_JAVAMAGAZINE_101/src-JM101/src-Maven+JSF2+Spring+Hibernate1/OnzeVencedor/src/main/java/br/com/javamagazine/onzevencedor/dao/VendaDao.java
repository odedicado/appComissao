/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.dao;

import br.com.javamagazine.onzevencedor.entity.Venda;
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
@Repository("vendaDao")
public class VendaDao {
    @PersistenceContext
    protected EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Venda> findAll() {
        String jpql = " SELECT at from Venda at order by at.dtaVenda";
        Query query = entityManager.createQuery(jpql);
        List<Venda> atletas = (List<Venda>) query.getResultList();
        return atletas;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Venda> findByFuncionario(Integer idFuncionario) {
        String jpql = " SELECT at from Venda at"
                + " JOIN at.funcionario f"
                + " WHERE f.id = :idFuncionario"
                + " ORDER BY at.dtaVenda";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("idfClube", idFuncionario);
        List<Venda> vendas = (List<Venda>) query.getResultList();
        return vendas;
    }

    @Transactional
    public Venda create(Venda entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    public void update(Venda entity) {
        entityManager.merge(entity);
    }

    @Transactional
    public void delete(Venda entity) {
        entity = entityManager.find(Venda.class, entity.getId());
        entityManager.remove(entity);
    }
}
