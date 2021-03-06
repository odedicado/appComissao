package br.com.javamagazine.onzevencedor.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Atleta implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDF_ATLETA")

    private Integer idf;
    
    @Column(name = "nome_atleta")
    @NotNull(message="O nome do Atleta nao pode ser nulo")
    @NotEmpty(message="O nome do atleta nao pode ser vazio")
    @Length(min=1 ,max=20,message="O nome precisa conter no maximo 20 letras")
    private String nome;
    
    @Column(name = "DTA_NASCIMENTO")
    @Temporal(TemporalType.DATE)
    private Date dtaNascimento;
    
    @Column(name = "IDF_POSICAO")
    @Enumerated(EnumType.ORDINAL)
    private Posicao posicao;
    
    @ManyToOne
    @JoinColumn(name = "IDF_CLUBE")
    private Clube clube;
    
    
    @ManyToOne
    @JoinColumn(name = "id_cidade")
    @Valid
    private Cidade cidade;

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

    public Date getDtaNascimento() {
        return dtaNascimento;
    }

    public void setDtaNascimento(Date dtaNascimento) {
        this.dtaNascimento = dtaNascimento;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    
}
