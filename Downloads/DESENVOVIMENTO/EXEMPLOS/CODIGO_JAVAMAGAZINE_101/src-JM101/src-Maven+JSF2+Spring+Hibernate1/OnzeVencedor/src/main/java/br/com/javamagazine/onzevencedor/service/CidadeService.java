package br.com.javamagazine.onzevencedor.service;

import br.com.javamagazine.onzevencedor.dao.CidadeDAO;
import br.com.javamagazine.onzevencedor.entity.Cidade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cidadeService")
public class CidadeService {
	
	@Autowired
	private CidadeDAO dao;
	
	public List<Cidade> getAllCidades(){
		
		return dao.findAll();
		
	}
	
        public List<Cidade> getCidadesByName(String nomeCidade){
		
		return dao.findByName(nomeCidade);
		
	}

	
	public Cidade createCidade(Cidade Cidade){
		
		return dao.create(Cidade);
		
	}
        
        public void updateCidade(Cidade Cidade){
		
		dao.update(Cidade);
		
	}

	public void deleteCidade(Cidade Cidade){
		
		dao.delete(Cidade);
		
	}

	public void setDao(CidadeDAO dao) {
		this.dao = dao;
	}
        
          
        public Cidade getCidade(Cidade cidade){
		return dao.find(cidade.getId());
		
	}

}
