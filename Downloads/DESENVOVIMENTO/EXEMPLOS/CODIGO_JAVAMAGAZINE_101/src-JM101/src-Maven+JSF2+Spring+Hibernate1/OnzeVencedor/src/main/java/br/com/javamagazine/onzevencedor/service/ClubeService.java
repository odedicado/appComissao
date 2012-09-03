package br.com.javamagazine.onzevencedor.service;

import br.com.javamagazine.onzevencedor.dao.ClubeDAO;
import br.com.javamagazine.onzevencedor.entity.Clube;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clubeService")
public class ClubeService  {

    @Autowired
    private ClubeDAO dao;

    public List<Clube> getAllClubes() {

        return dao.findAll();

    }

    public List<Clube> getClubesByName(String nomeClube) {

        return dao.findByName(nomeClube);

    }

    public Clube createClube(Clube clube) {

        return dao.create(clube);

    }

    public Clube getClube(Clube clube) {
        return dao.find(clube.getIdf());

    }

    public void updateClube(Clube clube) {

        dao.update(clube);

    }

    public void deleteClube(Clube clube) {

        dao.delete(clube);

    }

    public void setDao(ClubeDAO dao) {
        this.dao = dao;
    }
}
