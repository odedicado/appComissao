package br.com.javamagazine.onzevencedor.controller;


import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "menuBean")
@ViewScoped
public class MenuBean extends BaseBean<Menu> {

    /**
     * -----------------------------ATRIBUTOS-----------------------------------------
     */
    private static final long serialVersionUID = 1L;
    private List<Menu> listMenuHome;

    public String goToAtleta() {
        refresh();
        String page = "/pages/atleta/atleta";
        return page;
    }

    public String goToClube() {
        refresh();
        String page = "/pages/clube/clube";
        return page;
    }
    
    
    public String goToFuncionario() {
        refresh();
        String page = "/pages/funcionario/consultaFuncionairo.jsf";
        return page;
    }


    public String goToHome() {
        refresh();
        String page = "/index";
        return page;
    }

    public void goToAtletaTeste() {
        String page = "atleta/atleta.xhtml";
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getApplication().getNavigationHandler().handleNavigation(fc, "null", "/pages/" + page + "?faces-redirect=true");
    }

    public void doPrepareMenuConfiguracoes() {
        this.listMenuHome = new ArrayList<Menu>();
        this.listMenuHome.add(new Menu("/resources/images/basket.png", "/configuracoes.jsf", "Temas"));
    }

    public List<Menu> getListMenuHome() {
        if (this.listMenuHome == null) {
            return new ArrayList<Menu>();
        }
        return listMenuHome;
    }

    public void setListMenuHome(List<Menu> listMenuHome) {
        this.listMenuHome = listMenuHome;
    }

    @Override
    protected void finishCreate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void finishUpDate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void finishDelete() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
