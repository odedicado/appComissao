package br.com.javamagazine.onzevencedor.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class Funcionario implements Serializable {

    /**
     *
    */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Integer id;
    
    @Column(name="nome")
    @NotNull(message="O nome do funcionário nao pode ser nulo ou vazio")
    @Length(min=3 ,max=50, message="O nome precisa conter entre 3 e 50 letras")
    private String nome;
    
    @Column(name="cpf")
    @NotNull(message="O CPF precisa ser um numero valido")
    private String cpf;
    
    @Column(name="email")
    @NotNull(message="O email precisa ser valido")
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "id_cidade")
    @Valid
    private Cidade cidade;
    
    @Column(name="dt_admissao")
    @Temporal(TemporalType.DATE)
    private Date dtaAdmissao;
    
    
    @Column(name="dt_demissao")
    @Temporal(TemporalType.DATE)
    private Date dtaDemissao;
    
    @Column(name="ativio")
    private boolean ativo;
    
    @Column(name="comissao_maxima")
    private BigDecimal comissaoMaxima ; 

    @Column(name="comissao_dono")
    private BigDecimal comissaoDono ; 
    
    @Column(name="comissao_conjunta")
    private BigDecimal comissaoConjunta ;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public BigDecimal getComissaoConjuntA() {
        return comissaoConjunta;
    }

    public void setComissaoConjuntA(BigDecimal comissaoConjuntA) {
        this.comissaoConjunta = comissaoConjuntA;
    }

    public BigDecimal getComissaoDono() {
        return comissaoDono;
    }

    public void setComissaoDono(BigDecimal comissaoDono) {
        this.comissaoDono = comissaoDono;
    }

    public BigDecimal getComissaoMaxima() {
        return comissaoMaxima;
    }

    public void setComissaoMaxima(BigDecimal comissaoMaxima) {
        this.comissaoMaxima = comissaoMaxima;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDtaAdmissao() {
        return dtaAdmissao;
    }

    public void setDtaAdmissao(Date dtaAdmissao) {
        this.dtaAdmissao = dtaAdmissao;
    }

    public Date getDtaDemissao() {
        return dtaDemissao;
    }

    public void setDtaDemissao(Date dtaDemissao) {
        this.dtaDemissao = dtaDemissao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
    public Cidade getCidade() {
        if (this.cidade == null){ 
            this.cidade= new Cidade() ; 
        }
        return cidade;
    }
    
    
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    ///************************************************    
        public void funcionario(){
            this.comissaoDono = new BigDecimal(0);
            this.comissaoConjunta =  new BigDecimal(0) ; 
            this.comissaoMaxima = new BigDecimal(0) ;
            this.dtaDemissao = null ; 
            this.ativo = true ; 
        }
    
    //-------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

}
