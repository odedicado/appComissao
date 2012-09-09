/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.service;

import br.com.javamagazine.onzevencedor.dao.ComissaoDao;
import br.com.javamagazine.onzevencedor.entity.Comissao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author ALEX
 */
@Service("comissaoService")
public class ComissaoService {
    
    @Autowired
    private ComissaoDao dao;

    public List<Comissao> getAllComissaos() {
        return dao.findAll();
    }

    public Comissao createComissao(Comissao entity) {
        return dao.create(entity);
    }

    public void updateComissao(Comissao entity) {
        dao.update(entity);
    }

    public void deleteComissao(Comissao entity) {
        dao.delete(entity);
    }

}
