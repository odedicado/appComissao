package br.com.javamagazine.onzevencedor.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Clube implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_clube")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idf;
    @Column(name = "nome_clube", unique = true)
    @NotNull
    @NotEmpty
    private String nome;
    @Column(name = "endereco_clube")
    @NotNull
    @NotEmpty
    private String endereco;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clube")
    private List<Atleta> atletas;

    public Integer getIdf() {
        return idf;
    }

    public void setIdf(Integer idf) {
        this.idf = idf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        if (idf != null) {
            return idf.toString();
        } else {
            return "";
        }
    }

    public Clube(Integer idf, String nome) {
        this.idf = idf;
        this.nome = nome ; 
    }
    
    public Clube() {
        
    }
    
}
