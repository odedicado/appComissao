/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.controller;

/**
 *
 * @author ALEX
 */
public final class Menu{
    private String imagemMenuHome ; 
    private String linkMenuHome ; 
    private String nomeMenuPai ; 
    
    
    public Menu(){
        
    }

    public Menu(String imagemMenuHome, String linkMenuHome, String nomeMenuPai) {
        this.imagemMenuHome = imagemMenuHome;
        this.linkMenuHome = linkMenuHome;
        this.nomeMenuPai = nomeMenuPai;
    }
    
    

    public String getImagemMenuHome() {
        return imagemMenuHome;
    }

    public void setImagemMenuHome(String imagemMenuHome) {
        this.imagemMenuHome = imagemMenuHome;
    }

    public String getLinkMenuHome() {
        return linkMenuHome;
    }

    public void setLinkMenuHome(String linkMenuHome) {
        this.linkMenuHome = linkMenuHome;
    }

    public String getNomeMenuPai() {
        return nomeMenuPai;
    }

    public void setNomeMenuPai(String nomeMenuPai) {
        this.nomeMenuPai = nomeMenuPai;
    }
  
}
