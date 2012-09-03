package br.com.javamagazine.onzevencedor.service;

import br.com.javamagazine.onzevencedor.dao.AtletaDAO;
import br.com.javamagazine.onzevencedor.entity.Atleta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("atletaService")
public class AtletaService {

	/*
         * Injeta uma dao, do tipo atleta que sera usado para o crud.
        */
        @Autowired
	private AtletaDAO dao;

	public List<Atleta> getAllAtletas(){
		return dao.findAll();
	}
	public List<Atleta> getAtletasByClube(Integer idfClube){
		return dao.findByClube(idfClube);
	}
	public Atleta createAtleta(Atleta atleta){
		return dao.create(atleta);
	}
	public void updateAtleta(Atleta atleta){
		dao.update(atleta);
	}
        
        public void deleteAtleta(Atleta atleta){
		dao.delete(atleta);
	}
}
