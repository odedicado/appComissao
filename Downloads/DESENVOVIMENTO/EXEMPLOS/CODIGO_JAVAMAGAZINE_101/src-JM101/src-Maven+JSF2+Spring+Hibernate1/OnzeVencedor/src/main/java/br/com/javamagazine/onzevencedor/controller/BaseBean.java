package br.com.javamagazine.onzevencedor.controller;

import br.com.javamagazine.onzevencedor.enums.ModoCrud;
import br.com.javamagazine.onzevencedor.enums.TipoPopUp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.application.ViewHandler;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

public abstract class BaseBean<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected ResourceBundle propriedades = ResourceBundle.getBundle("messages");
    protected ModoCrud modoCrud = ModoCrud.NOVO;
    protected TipoPopUp tipoPopup = TipoPopUp.NOVO;
    private T pojo;
    private List<T> listPojo;

    protected void addErrorMessage(String componentId, String errorMessage) {
        addMessage(componentId, errorMessage, FacesMessage.SEVERITY_ERROR);
    }

    protected void addErrorMessage(String errorMessage) {
        addErrorMessage(null, errorMessage);
    }

    protected void addInfoMessage(String componentId, String infoMessage) {
        addMessage(componentId, infoMessage, FacesMessage.SEVERITY_INFO);
    }

    protected void addInfoMessage(String infoMessage) {
        addInfoMessage(null, infoMessage);
    }

    private void addMessage(String componentId, String errorMessage, Severity severity) {
        FacesMessage message = new FacesMessage(errorMessage);
        message.setSeverity(severity);
        FacesContext.getCurrentInstance().addMessage(componentId, message);
        //System.out.println("FacesContext.getCurrentInstance()" + FacesContext.getCurrentInstance());
        //System.out.println("ANTES DA MENSSAGEM" + FacesContext.getCurrentInstance().getMessages());
        //System.out.println("DEPOIS  DA MENSSAGEM" + FacesContext.getCurrentInstance().getMessages());
    }

    public void doCrud() {
        if (modoCrud.equals(ModoCrud.NOVO)) {
            finishCreate();
        } else if (modoCrud.equals(ModoCrud.EDITAR)) {
            finishUpDate();
        } else if (modoCrud.equals(ModoCrud.REMOVER)) {
            finishDelete();
        }
        //System.out.println("RESULTADO.. METODO...doCrud" + modoCrud);
    }

    public void finshCrud1(T result, boolean isok, String msg, T nv) {
        if (isok) {
            this.setListPojo(new ArrayList<T>());
            this.getListPojo().add(result);
            this.setPojo(nv);
            this.modoCrud = ModoCrud.NOVO;
            addInfoMessage(msg);
        } else {
            this.setListPojo(getListPojo());
            addErrorMessage(msg);
        }
    }

    public T getPojo() {
        return pojo;
    }

    public void setPojo(T pojo) {
        this.pojo = pojo;
    }

    public ModoCrud getModoCrud() {
        return modoCrud;
    }

    public void setModoCrud(ModoCrud modoCrud) {
        this.modoCrud = modoCrud;
    }

    public List<T> getListPojo() {
        return listPojo;
    }

    public void setListPojo(List<T> listPojo) {
        this.listPojo = listPojo;
    }

    public boolean getExisteMessagemErro() {
        Severity maximumSeverity = FacesContext.getCurrentInstance().getMaximumSeverity();
        boolean validationFailed = false;
        if (maximumSeverity != null && (maximumSeverity == FacesMessage.SEVERITY_ERROR || maximumSeverity == FacesMessage.SEVERITY_FATAL)) {
            validationFailed = true;
        }
        System.out.println("RESULTADO.. METODO...existeMessagemErro" + validationFailed);
        return validationFailed;
    }

    public RequestContext getContext() {
        return RequestContext.getCurrentInstance();
    }

    public boolean getModoVisualizacao() {
        if ((modoCrud.equals(ModoCrud.CONSULTAR)) || (modoCrud.equals(ModoCrud.REMOVER))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getModoPopUp() {
        System.out.println("...metodo  getModoPopUp()" + tipoPopup);
        if ((tipoPopup.equals(TipoPopUp.NOVO))) {
            return false;
        } else {
            return true;
        }

    }

    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse(); //Optional
    }

    public void close(UIComponent component) {
        System.out.println("...metodo  close" + component.getClientId());
        component.getChildren().clear();
    }

    public void cleanSubmittedValues(UIComponent component) {
        System.out.println("...metodo  cleanSubmittedValues" + component);
        if (component instanceof EditableValueHolder) {
            EditableValueHolder evh = (EditableValueHolder) component;
            evh.setSubmittedValue(null);
            evh.setValue(null);
            evh.setLocalValueSet(false);
            evh.setValid(true);
        }
        if (component.getChildCount() > 0) {
            for (UIComponent child : component.getChildren()) {
                cleanSubmittedValues(child);
            }
        }
    }

    protected abstract void finishCreate();

    protected abstract void finishUpDate();

    protected abstract void finishDelete();
}
