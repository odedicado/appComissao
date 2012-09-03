/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.javamagazine.onzevencedor.service;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrador
 */
public abstract class BasicSessionBean implements java.io.Serializable {

    public final static boolean debug = false;
    private static final long serialVersionUID = 2L;

    @PersistenceContext
    private EntityManager em;

    public BasicSessionBean() {
    }

    public EntityManager getEm() {
        return em;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public <T> List<T> getList(Class<T> classToCast,String query,Object... values) {
        Query qr = createQuery(query, values);
        return qr.getResultList();
    }

    public <T> List<T> getLimitedList(Class<T> classToCast,String query,int limit,Object... values) {
        Query qr = createQuery(query, values);
        qr.setMaxResults(limit);
        return qr.getResultList();
    }

    public <T> List<T> getNamedList(Class<T> classToCast,String namedQuery,Object... values) {
        Query qr = em.createNamedQuery(namedQuery);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                Object object = values[i];
                qr.setParameter(i + 1, object);
            }
        }
        return qr.getResultList();
    }

    public <T> T getPojo(Class<T> classToCast,String query,Object... values) {
        Query qr = createQuery(query, values);
        return (T) qr.getSingleResult();
    }

    public <T> T getPojo(Class<T> classToCast,Serializable primaryKey) {
        return em.find(classToCast, primaryKey);
    }


    public int execute(String query,Object... values) {
        Query qr = createQuery(query, values);
        return qr.executeUpdate();
    }


    private Query createQuery(String query, Object[] values) {
        Query qr = em.createQuery(query);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                Object object = values[i];
                qr.setParameter(i + 1, object);
            }
        }
        return qr;
    }


}
