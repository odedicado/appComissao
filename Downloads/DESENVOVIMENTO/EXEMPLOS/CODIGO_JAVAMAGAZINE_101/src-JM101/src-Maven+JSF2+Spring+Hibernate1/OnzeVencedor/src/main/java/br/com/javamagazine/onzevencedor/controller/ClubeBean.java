package br.com.javamagazine.onzevencedor.controller;

import br.com.javamagazine.onzevencedor.entity.Clube;
import br.com.javamagazine.onzevencedor.enums.ModoCrud;
import br.com.javamagazine.onzevencedor.service.ClubeService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "clubeBean")
@ViewScoped
public class ClubeBean extends BaseBean<Clube> {

    /**
     *
     */
    private static final long serialVersionUID = 15L;
    @ManagedProperty("#{clubeService}")
    private ClubeService model;
    
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
    public void finishCreate() {
        try {
            setPojo(model.createClube(getPojo()));
            setClubes(getListPojo());
            addInfoMessage(propriedades.getString("clube_crud_delete"));
        } catch (Exception e) {
            finshCrud(getPojo(), false, e.getMessage());
        }
    }
    @Override
    public void finishUpDate() {
        try {
            model.updateClube(getPojo());
            setClubes(getListPojo());
            addInfoMessage(propriedades.getString("clube_crud_delete"));
        } catch (Exception e) {
            finshCrud(getPojo(), false, e.getMessage());
        }
    }
    
    @Override
    public void finishDelete() {
        try {
            model.deleteClube(getPojo());
            setClubes(model.getAllClubes());
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
    }

    public void setClubes(List<Clube> clubes) {
        setListPojo(clubes);
    }

    public void doListClubAll() {
        setClubes(model.getAllClubes());
    }
}
