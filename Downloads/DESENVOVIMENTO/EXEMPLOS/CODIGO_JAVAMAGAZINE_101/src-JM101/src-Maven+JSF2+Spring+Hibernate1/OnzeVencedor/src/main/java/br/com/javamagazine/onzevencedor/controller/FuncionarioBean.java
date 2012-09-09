/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.controller;

import br.com.javamagazine.onzevencedor.entity.Funcionario;
import br.com.javamagazine.onzevencedor.service.FuncionarioService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ALEX
 */
@ManagedBean(name = "funcionarioBean")
@ViewScoped
public class FuncionarioBean extends BaseBean<Funcionario>  {
    
    @ManagedProperty("#{funcionarioService}")
    private FuncionarioService funcionarioService;
    

    @Override
    protected void finishCreate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void finishUpDate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void finishDelete() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Funcionario> getFuncionarios() {
        if (getListPojo() == null) {
            setListPojo(this.funcionarioService.getAllFuncionarios());
        }
        return getListPojo();
    }
    
    public Funcionario getFuncionario() {
        if (getPojo() == null) {
            setPojo(new Funcionario());
        }
        return getPojo();
    }

    public FuncionarioService getFuncionarioService() {
        return funcionarioService;
    }

    public void setFuncionarioService(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
    
    
    public void refreshFuncionarios(){
        if (getListPojo() == null) {
            setListPojo(new ArrayList<Funcionario>());
        }else{
            setListPojo(this.funcionarioService.getAllFuncionarios());
        }
    }
}
