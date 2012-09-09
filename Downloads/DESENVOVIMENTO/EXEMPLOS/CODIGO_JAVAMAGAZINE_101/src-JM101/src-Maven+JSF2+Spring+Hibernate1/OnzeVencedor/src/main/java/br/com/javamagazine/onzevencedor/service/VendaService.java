/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.service;

import br.com.javamagazine.onzevencedor.dao.VendaDao;
import br.com.javamagazine.onzevencedor.entity.Venda;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author ALEX
 */
@Service("vendaService")
public class VendaService {
    
    @Autowired
    private VendaDao dao;

    public List<Venda> getAllVendas() {
        return dao.findAll();
    }

    public Venda createVenda(Venda entity) {
        return dao.create(entity);
    }

    public void updateVenda(Venda entity) {
        dao.update(entity);
    }

    public void deleteVenda(Venda entity) {
        dao.delete(entity);
    }

}
