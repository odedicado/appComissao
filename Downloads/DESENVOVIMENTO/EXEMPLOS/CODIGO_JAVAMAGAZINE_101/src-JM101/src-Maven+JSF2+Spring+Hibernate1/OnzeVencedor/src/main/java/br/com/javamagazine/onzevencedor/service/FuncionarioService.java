/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.service;

import br.com.javamagazine.onzevencedor.dao.FuncionarioDao;
import br.com.javamagazine.onzevencedor.entity.Funcionario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author ALEX
 */
@Service("funcionarioService")
public class FuncionarioService {
    
    @Autowired
    private FuncionarioDao dao;

    public List<Funcionario> getAllFuncionarios() {
        return dao.findAll();
    }

    public Funcionario createFuncionario(Funcionario entity) {
        return dao.create(entity);
    }

    public void updateFuncionario(Funcionario entity) {
        dao.update(entity);
    }

    public void deleteFuncionario(Funcionario entity) {
        dao.delete(entity);
    }

}
