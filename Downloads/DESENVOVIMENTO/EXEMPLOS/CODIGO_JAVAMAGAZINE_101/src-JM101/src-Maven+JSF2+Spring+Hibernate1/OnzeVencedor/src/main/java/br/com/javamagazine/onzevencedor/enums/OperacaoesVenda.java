package br.com.javamagazine.onzevencedor.enums;


public enum OperacaoesVenda {

    ATIVA("ATIVA"), 
    CONCLUIDA("CONCLUIDA"), 
    CANCELADA("C");
    
    private String desc;
    
    OperacaoesVenda(String desc) {
        this.desc = desc;
    }
    
    public String getDescription() {
        return desc;
    }
 
}
