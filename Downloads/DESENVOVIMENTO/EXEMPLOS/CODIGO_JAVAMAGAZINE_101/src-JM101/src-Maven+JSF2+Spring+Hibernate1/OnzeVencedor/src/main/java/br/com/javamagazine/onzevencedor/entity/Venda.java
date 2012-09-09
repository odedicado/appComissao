package br.com.javamagazine.onzevencedor.entity;

import br.com.javamagazine.onzevencedor.enums.OperacaoesVenda;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class Venda implements Serializable {

    /**
     *
    */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venda")
    private Integer id;
    
    @Column()
    @NotNull(message="O numero da nota de venda  nao pode ser nulo ou vazio ")
    private String nVenda;
    
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    @Valid
    private Funcionario funcionario;
    
    @Column(name="dt_venda")
    @Temporal(TemporalType.DATE)
    private Date dtaVenda;
    
    @Column(name="dt_conclusao")
    @Temporal(TemporalType.DATE)
    private Date dtaConclusao;
    
    @Column(name="status_venda")
    @NotNull()
    @Enumerated(EnumType.STRING)
    private OperacaoesVenda statusVenda;
    
    @Column(name="valor_venda")
    private BigDecimal valorVenda ;
    
    @ManyToOne
    @JoinColumn(name = "id_comissao_dono_venda")
    @Valid
    private Comissao comissaoDonoVenda;
    
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venda")
    private List<Comissao> comissoes;

    

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

    public Date getDtaVenda() {
        return dtaVenda;
    }

    public void setDtaVenda(Date dtaVenda) {
        this.dtaVenda = dtaVenda;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getnVenda() {
        return nVenda;
    }

    public void setnVenda(String nVenda) {
        this.nVenda = nVenda;
    }

    public OperacaoesVenda getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(OperacaoesVenda statusVenda) {
        this.statusVenda = statusVenda;
    }

  

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public List<Comissao> getComissoes() {
        return comissoes;
    }

    public void setComissoes(List<Comissao> comissoes) {
        this.comissoes = comissoes;
    }
    
    
    ///************************************************    
        public void Venda(){
            this.dtaVenda = new Date();
            this.valorVenda =  new BigDecimal(0) ; 
            this.comissaoDonoVenda = new Comissao() ;
            this.funcionario = new Funcionario();
            this.comissoes = new ArrayList<Comissao>();
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
        final Venda other = (Venda) obj;
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
