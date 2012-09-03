/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ALEX
 */

@Entity
public class Cidade implements Serializable
{
  private static final long serialVersionUID = 150L;
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade")
    private Integer id;
    
    @Column(name = "nome_atleta")
    @NotNull
    @NotEmpty
    private String nome;

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
    
    public Cidade(Integer id, String nome ){
        this.id = id ; 
        this.nome  = nome ; 
    }
    public Cidade( ){
      
    }
}
