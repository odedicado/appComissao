/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.controller;

import br.com.javamagazine.onzevencedor.entity.Cidade;
import br.com.javamagazine.onzevencedor.entity.Clube;
import br.com.javamagazine.onzevencedor.enums.TipoPopUp;
import br.com.javamagazine.onzevencedor.service.CidadeService;
import br.com.javamagazine.onzevencedor.service.ClubeService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author ALEX
 */
public final class PopUp{
    private List<ItemPopUp> listaEntity = new ArrayList<ItemPopUp>(); 
    private String busca ; 
    private ItemPopUp popup ; 
    private String componentUpDate ; 
    private TipoPopUp  tipo = TipoPopUp.NOVO ; 
    
    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public List<ItemPopUp> getListaEntity() {
        if(listaEntity==null){
            listaEntity = new ArrayList<ItemPopUp>();
        }
        
        return listaEntity;
    }

    public void setListaEntity(List<ItemPopUp> listaEntity) {
        
        this.listaEntity = listaEntity;
    }

   
    public ItemPopUp getPopup() {
        return popup;
    }

    public void setPopup(ItemPopUp popup) {
        this.popup = popup;
    }

    public PopUp() {
        clearPopUp() ; 
    }
    
    public PopUp(String componenteUpDate) {
        clearPopUp() ; 
        this.componentUpDate = componenteUpDate ; 
    }
    
    public String getComponentUpDate() {
        return componentUpDate;
    }

    public void setComponentUpDate(String componentUpDate) {
        this.componentUpDate = componentUpDate;
    }
    
    
    public void clearPopUp() {
        this.listaEntity = new ArrayList<ItemPopUp>();
        this.busca = new String();  
        this.popup = new ItemPopUp();
        this.tipo = TipoPopUp.NOVO ; 
        this.componentUpDate = new String();
    }
    public void doCreatePopUp(List<ItemPopUp>listaPopUp, TipoPopUp tipo, String uptadeComponent){
        clearPopUp() ; 
        this.tipo = tipo ; 
        this.listaEntity = listaPopUp ; 
        this.componentUpDate = uptadeComponent ; 
    }

    public TipoPopUp getTipo() {
        return tipo;
    }

    public void setTipo(TipoPopUp tipo) {
        this.tipo = tipo;
    }
}
