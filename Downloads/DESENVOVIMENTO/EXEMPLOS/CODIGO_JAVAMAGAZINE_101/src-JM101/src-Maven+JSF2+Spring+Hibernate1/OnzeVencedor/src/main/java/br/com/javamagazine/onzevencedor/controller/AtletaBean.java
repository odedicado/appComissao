package br.com.javamagazine.onzevencedor.controller;

import br.com.javamagazine.onzevencedor.entity.Atleta;
import br.com.javamagazine.onzevencedor.entity.Cidade;
import br.com.javamagazine.onzevencedor.entity.Clube;
import br.com.javamagazine.onzevencedor.entity.Posicao;
import br.com.javamagazine.onzevencedor.enums.ModoCrud;
import br.com.javamagazine.onzevencedor.enums.TipoPopUp;
import br.com.javamagazine.onzevencedor.service.AtletaService;
import br.com.javamagazine.onzevencedor.service.CidadeService;
import br.com.javamagazine.onzevencedor.service.ClubeService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;

@ManagedBean(name = "atletaBean")
@ViewScoped
public class AtletaBean extends BaseBean<Atleta> {

    /**
       *-----------------------------ATRIBUTOS-----------------------------------------
    */
    private static final long serialVersionUID = 1L;
    //private List<Clube> clubes;
    //private List<Cidade> cidades;
    private Integer idfClube;
    
    //private String pesquisaClube;
    
    
    private PopUp popup;
    private UIForm form;
    
    private UIForm formPopUp;

    private UIInput txt;
    
   
    
    @ManagedProperty("#{clubeService}")
    private ClubeService clubeService;
    
    @ManagedProperty("#{atletaService}")
    private AtletaService atletaService;
    
    @ManagedProperty("#{cidadeService}")
    private CidadeService cidadeService;

    /*
     * ---------------------------CRUD-------------------------------------------
     */
    @Override
    protected void upDate() {
        try {
            System.out.println("METODO...upDate... inicio" + getPojo().getNome());
            System.out.println("METODO...upDate... inicio" + getPojo().getClube().getNome());
            //Clube c = new Clube();
            //c.setIdf(idfClube);
            //getPojo().setClube(c);
            atletaService.updateAtleta(getPojo());
            finshCrud(getPojo(), true, propriedades.getString("atleta_crud_update"));
        } catch (Exception e) {
            finshCrud(getPojo(), false, e.getMessage());
        }
    }

    @Override
    public void create() {
        try {
            ///Clube c = new Clube();
            //c.setIdf(idfClube);
            //getPojo().setClube(c);
            setPojo(atletaService.createAtleta(getPojo()));
            finshCrud(getPojo(), true, propriedades.getString("atleta_crud_ok"));
        } catch (Exception e) {
            finshCrud(getPojo(), false, e.getMessage());
        }
    }

    @Override
    public void delete() {
        try {
            atletaService.deleteAtleta(getPojo());
            getToListAll();
            addInfoMessage(propriedades.getString("atleta_crud_delete"));
        } catch (Exception e) {
            finshCrud(getPojo(), false, e.getMessage());
        }
    }

    /*
     * --------------------------------------------------------------------
     */
    public Posicao[] getPosicoes() {
        return Posicao.values();
    }

 
    public ClubeService getClubeService() {
        return clubeService;
    }

    public void setClubeService(ClubeService clubeService) {
        this.clubeService = clubeService;
    }

    public AtletaService getAtletaService() {
        return atletaService;
    }

    public void setAtletaService(AtletaService atletaService) {
        this.atletaService = atletaService;
    }

    public List<Atleta> getAtletas() {
        if (getListPojo() == null) {
            setListPojo(atletaService.getAllAtletas());
        }
        return getListPojo();
    }

    public void setAtletas(List<Atleta> atletas) {
        setListPojo(atletas);
    }

    public Atleta getAtleta() {
        if (getPojo() == null) {
            setPojo(new Atleta());
        }
        return getPojo();
    }

   

    public void setAtleta(Atleta atleta) {
        this.setPojo(atleta);
    }

    public Integer getIdfClube() {
        return idfClube;
    }

    public void setIdfClube(Integer idfClube) {
        this.idfClube = idfClube;
    }
    
    
    public CidadeService getCidadeService() {
        return cidadeService;
    }

    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }
    
    /*
     * CONTROLE TELA TIPO DE OPERACAO DO CRUD-----------------------------------
     */
    public void doCreate() {
        this.setAtleta(new Atleta());
        this.getAtleta().setClube(new Clube());
        this.getAtleta().setCidade(new Cidade());
        this.modoCrud = ModoCrud.NOVO;
        this.popup = new PopUp();
    }

    public void doUpdate() {
        //idfClube = getAtleta().getClube().getIdf();
        //System.out.println("doUpdate()" + getAtleta().getClube().getNome());
        this.modoCrud = ModoCrud.EDITAR;
    }

    public void doDelete() {
        this.modoCrud = ModoCrud.REMOVER;
    }
    /*
     * ----------------------------------------------------------------------
     */
  
  // Melhorar este método 
  public void finshCrud(Atleta result, boolean isok, String msg) {
        if (isok) {
            System.out.println("METODO...finshCrud... inicio" + result);
            this.setAtletas(new ArrayList<Atleta>());
            this.getAtletas().add(result);
            this.setPojo(new Atleta());
            this.modoCrud = ModoCrud.NOVO;
            System.out.println("METODO...finshCrud... Final" + result);
            addInfoMessage(msg);
        } else {
            this.setAtletas(getAtletas());
            addErrorMessage(msg);
        }
    }
    
  public void getToListAll() {
        doCreate();
        setAtletas(atletaService.getAllAtletas());
    }
    
   
  //------------------------POP UP----------------------------------------------------------------------
    
    public void finishPopUpClube() {
        Clube c = new Clube(popup.getPopup().getId(), popup.getPopup().getNome());
        Clube clube = clubeService.getClube(c);
        this.getAtleta().setClube(clube);
        //System.out.println("Metodo.... doFinishPopUpClube...." + popup.getPopup().getId());
        //System.out.println("Metodo.... doFinishPopUpClube...." + clube.getIdf());
   }
   
   public void finishPopUpCidade() {
        Cidade c = new Cidade(popup.getPopup().getId(), popup.getPopup().getNome()) ; 
        Cidade cidade = cidadeService.getCidade(c);
        this.getAtleta().setCidade(cidade);
        //System.out.println("Metodo.... doFinishPopUpClubeCIDADE...." + popup.getPopup().getId());
        //System.out.println("Metodo.... doFinishPopUpClubeCIDADE...." + cidade.getId());
    }

   public void doFinishPopUp() {
        cleanSubmittedValues(form);
        if (this.popup.getTipo().equals(TipoPopUp.CLUBE)) {
            finishPopUpClube();
        } else if (this.popup.getTipo().equals(TipoPopUp.CIDADE)) {
            finishPopUpCidade() ; 
        }
        this.popup = new PopUp();
        //close(txt) ; 
        //System.out.println("...metodo  doFinishPopUpClube()..." + getAtleta());
        //System.out.println("...metodo  doFinishPopUpClube()..." + getAtleta().getClube());
        //this.tipoPopup = TipoPopUp.NOVO;
   }
   
   public void doPesquisaPopUp() {
       List<ItemPopUp> listPesquisa = new ArrayList<ItemPopUp>(); 
       if (this.popup.getTipo().equals(TipoPopUp.CLUBE)) {
            listPesquisa =  getClubeByName(popup.getBusca()) ; 
        } else if (this.popup.getTipo().equals(TipoPopUp.CIDADE)) {
            
        }
        popup.setListaEntity(listPesquisa);
        //close(txt) ; 
        //System.out.println("...metodo  doFinishPopUpClube()..." + getAtleta());
        //System.out.println("...metodo  doFinishPopUpClube()..." + getAtleta().getClube());
        //this.tipoPopup = TipoPopUp.NOVO;
    }
   
    
   public void doCancelpopUp(){
        cleanSubmittedValues(formPopUp);
    }
    
    public void doCreatePopUpClube() {
        cleanSubmittedValues(formPopUp);
        this.popup = new PopUp();
        this.popup.doCreatePopUp(getAllClube(), TipoPopUp.CLUBE,":frm_cad_atleta:txtClubeAtleta");
        //System.out.println("...metodo  doCreatePopUpClube()..." + this.popup.getTipo());
        //System.out.println("...metodo  doCreatePopUpClube()...Atualiza texto getComponentUpDate..." + this.popup.getComponentUpDate());
    }

    public void doCreatePopUpCidade() {
        cleanSubmittedValues(formPopUp);
        this.popup = new PopUp();
        this.popup.doCreatePopUp(getAllCidade(), TipoPopUp.CIDADE,":frm_cad_atleta:txtCidadeAtleta");
        //System.out.println("...metodo  doCreatePopUpClube()..." + this.popup.getTipo());
        //System.out.println("...metodo  doCreatePopUpClube()...Atualiza texto getComponentUpDate..." + this.popup.getComponentUpDate());
    }
    
    
//-----------------------------------------------------------------------------------------------------------------------------------------------
    
    public UIForm getForm() {
        return form;
    }

    public void setForm(UIForm form) {
        this.form = form;
    }

    public UIInput getTxt() {
        return txt;
    }

    public void setTxt(UIInput txt) {
        this.txt = txt;
    }
    
    public PopUp getPopup() {
        if (popup == null) {
            popup = new PopUp();
        }
        return popup;
    }

    public UIForm getFormPopUp() {
        return formPopUp;
    }

    public void setFormPopUp(UIForm formPopUp) {
        this.formPopUp = formPopUp;
    }
  
    public void setPopup(PopUp popup) {
        this.popup = popup;
    }

    public List<ItemPopUp> getAllClube() {
        List<ItemPopUp> popula = new ArrayList<ItemPopUp>();
        for (Clube clube : clubeService.getAllClubes()) {
            popula.add(new ItemPopUp(clube.getIdf(), clube.getNome()));
        }
        return popula;
    }

    public List<ItemPopUp> getAllCidade() {
        List<ItemPopUp> popula = new ArrayList<ItemPopUp>();
        for (Cidade cidade : cidadeService.getAllCidades()) {
            popula.add(new ItemPopUp(cidade.getId(), cidade.getNome()));
        }
        return popula;
    }
    
    public List<ItemPopUp> getClubeByName(String nome) {
        List<ItemPopUp> popula = new ArrayList<ItemPopUp>();
        for (Clube clube : clubeService.getClubesByName(nome)) {
            popula.add(new ItemPopUp(clube.getIdf(), clube.getNome()));
        }
        return popula;
    }
}
