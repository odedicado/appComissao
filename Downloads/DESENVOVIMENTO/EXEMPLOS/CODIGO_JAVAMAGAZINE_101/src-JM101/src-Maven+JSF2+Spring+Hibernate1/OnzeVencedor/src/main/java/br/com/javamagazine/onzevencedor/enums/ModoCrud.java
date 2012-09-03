package br.com.javamagazine.onzevencedor.enums;


public enum ModoCrud {

    NOVO("NOVO"), 
    EDITAR("EDITAR"), 
    REMOVER("REMOVER"), 
    VISUALIZAR("VISUALIZAR"), 
    CONSULTAR("CONSULTAR");
    
    private String desc;
    
    ModoCrud(String desc) {
        this.desc = desc;
    }
    
    public String getDescription() {
        return desc;
    }
 
}
