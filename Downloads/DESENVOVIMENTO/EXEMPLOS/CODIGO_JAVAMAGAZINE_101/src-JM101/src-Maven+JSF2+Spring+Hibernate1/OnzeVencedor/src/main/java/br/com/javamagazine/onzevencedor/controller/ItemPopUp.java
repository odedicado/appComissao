/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.controller;

/**
 *
 * @author ALEX
 */
public class ItemPopUp {
    private Integer id ;
    private String nome ; 
    public  ItemPopUp (){
         
    }

    public ItemPopUp(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
