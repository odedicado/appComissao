package br.com.javamagazine.onzevencedor.entity;

import br.com.javamagazine.onzevencedor.enums.OperacaoesVenda;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class Comissao implements Serializable {

    /**
     *
    */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comissao")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    @Valid
    private Funcionario funcionario;
   
    @ManyToOne
    @JoinColumn(name = "id_venda")
    @Valid
    private Venda venda;
  
    @Column()
    @NotNull(message="O numero da nota de venda  nao pode ser nulo ou vazio ")
    private String nVenda;
      
    @Column(name="dt_comissao")
    @Temporal(TemporalType.DATE)
    private Date dtaComissao;
    
    @Column(name="dt_conclusao")
    @Temporal(TemporalType.DATE)
    private Date dtaConclusao;
    
    @Column(name="status_comissao")
    @NotNull()
    @Enumerated(EnumType.STRING)
    private OperacaoesVenda statusVenda;
    
    @Column(name="valor_venda")
    private BigDecimal valorVenda ;
    
    @NotNull(message="O valor da comissao nãp pode ser nulo")
    @Column(name="valor_comissao")
    private BigDecimal valorComissao ;

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDtaConclusao() {
        return dtaConclusao;
    }

    public void setDtaConclusao(Date dtaConclusao) {
        this.dtaConclusao = dtaConclusao;
    }

   

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public br.com.javamagazine.onzevencedor.entity.Venda getVenda() {
        return venda;
    }

    public void setVenda(br.com.javamagazine.onzevencedor.entity.Venda venda) {
        this.venda = venda;
    }

   

    public OperacaoesVenda getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(OperacaoesVenda statusVenda) {
        this.statusVenda = statusVenda;
    }

    public BigDecimal getValorComissao() {
        return valorComissao;
    }

    public void setValorComissao(BigDecimal valorComissao) {
        this.valorComissao = valorComissao;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }
    
    ///************************************************    
        public void Venda(){
            this.dtaComissao = new Date();
            this.dtaConclusao = null ; 
            this.valorVenda =  new BigDecimal(0) ; 
            this.valorComissao = new BigDecimal(0) ;
            this.dtaConclusao = null ; 
            this.statusVenda = OperacaoesVenda.ATIVA; 
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
        final Comissao other = (Comissao) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
        
        
}
