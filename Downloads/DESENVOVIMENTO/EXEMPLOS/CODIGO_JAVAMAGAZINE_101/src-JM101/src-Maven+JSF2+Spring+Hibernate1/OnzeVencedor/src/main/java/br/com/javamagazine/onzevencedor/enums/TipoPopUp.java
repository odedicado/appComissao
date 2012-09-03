package br.com.javamagazine.onzevencedor.enums;


public enum TipoPopUp {
    CLUBE("CLUBE"), 
    CIDADE("CIDADE"), 
    NOVO("NOVO"); 
    
    private String desc;
    
    TipoPopUp(String desc) {
        this.desc = desc;
    }
    
    public String getDescription() {
        return desc;
    }
 
}
