package br.com.javamagazine.onzevencedor.dao;

import br.com.javamagazine.onzevencedor.entity.Cidade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("cidadeDao")
public class CidadeDAO {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Cidade> findAll(){
		
		String jpql = " SELECT c from Cidade c order by c.nome";
		Query query = entityManager.createQuery(jpql);
		List<Cidade> cidades = (List<Cidade>) query.getResultList();
		return cidades;
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Cidade> findByName(String nomeCidade){
		
		String jpql = " SELECT c from Cidade c where nome like :nome order by c.nome";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("nome", nomeCidade + "%");
		
		List<Cidade> Cidades = (List<Cidade>) query.getResultList();
		return Cidades;
		
	}

	@Transactional
	public Cidade create(Cidade Cidade){
		entityManager.persist(Cidade);
		return Cidade;
		
	}

	@Transactional
	public void update(Cidade Cidade){
		
		entityManager.merge(Cidade);
		
	}

	@Transactional
	public void delete(Cidade Cidade){
		
		Cidade = entityManager.find(Cidade.class, Cidade.getId());
		entityManager.remove(Cidade);
		
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
        
        @SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public Cidade find(Integer id){
            return entityManager.find(Cidade.class, id);
      
	}

}
