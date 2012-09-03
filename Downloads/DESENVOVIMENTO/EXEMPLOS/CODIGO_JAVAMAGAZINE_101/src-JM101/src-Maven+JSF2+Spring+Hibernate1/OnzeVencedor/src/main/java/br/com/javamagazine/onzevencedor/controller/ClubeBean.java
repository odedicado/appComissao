package br.com.javamagazine.onzevencedor.controller;

import br.com.javamagazine.onzevencedor.entity.Clube;
import br.com.javamagazine.onzevencedor.enums.ModoCrud;
import br.com.javamagazine.onzevencedor.service.CidadeService;
import br.com.javamagazine.onzevencedor.service.ClubeService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "clubeBean")
@SessionScoped
public class ClubeBean extends BaseBean<Clube> {

    /**
     *
     */
    private static final long serialVersionUID = 15L;
    @ManagedProperty("#{clubeService}")
    private ClubeService model;
    
    
    


    public ClubeService getModel() {
        return model;
    }

    public void setModel(ClubeService model) {
        this.model = model;
    }

    public Clube getClube() {
        if (getPojo() == null) {
            setPojo(new Clube());
        }
        return getPojo();
    }

    public void setClube(Clube clube) {
        this.setPojo(clube);
    }

    public void finshCrud(Clube result, boolean isok, String msg) {
        if (isok) {
            System.out.println("METODO...finshCrud... inicio" + result);
            this.setClubes(new ArrayList<Clube>());
            this.getClubes().add(result);
            this.setPojo(new Clube());
            this.modoCrud = ModoCrud.NOVO;
            System.out.println("METODO...finshCrud... Final" + result);
            addInfoMessage(msg);
        } else {
            this.setClubes(getClubes());
            addErrorMessage(msg);
        }
    }

    @Override
    public void create() {
        try {
            setPojo(model.createClube(getPojo()));
            finshCrud(getPojo(), true, propriedades.getString("clube_crud_ok"));
        } catch (Exception e) {
            finshCrud(getPojo(), false, e.getMessage());
        }
    }

    @Override
    public void upDate() {
        try {
            System.out.println("METODO...upDate... inicio" + getPojo().getNome());
            model.updateClube(getPojo());
            finshCrud(getPojo(), true, propriedades.getString("clube_crud_update"));
        } catch (Exception e) {
            finshCrud(getPojo(), false, e.getMessage());
        }
    }

    public void delete() {
        try {
            model.deleteClube(getPojo());
            getToListAll();
            addInfoMessage(propriedades.getString("clube_crud_delete"));
        } catch (Exception e) {
            finshCrud(getPojo(), false, e.getMessage());
        }
    }

    public void doCreate() {
        this.setPojo(new Clube());
        this.modoCrud = ModoCrud.NOVO;
    }

    public void doUpdate() {
        this.modoCrud = ModoCrud.EDITAR;
    }

    public void doDelete() {
        this.modoCrud = ModoCrud.REMOVER;
    }

    public List<Clube> getClubes() {
        if (getListPojo() == null) {
            setListPojo(model.getAllClubes());
        }
        return getListPojo();
        //System.out.println("RESULTADO.. METODO...getClubes()" + getListPojo());
    }

    public void setClubes(List<Clube> clubes) {
        setListPojo(clubes);
    }

    public void doListClubAll() {
        setClubes(model.getAllClubes());
    }

    public void getToListAll() {
        this.setPojo(new Clube());
        setClubes(model.getAllClubes());
        System.out.println("METODO...getToListAll... QTD" + getClubes().size());
        System.out.println("METODO...getToListAll... POJO" + this.getPojo());
    }
}
