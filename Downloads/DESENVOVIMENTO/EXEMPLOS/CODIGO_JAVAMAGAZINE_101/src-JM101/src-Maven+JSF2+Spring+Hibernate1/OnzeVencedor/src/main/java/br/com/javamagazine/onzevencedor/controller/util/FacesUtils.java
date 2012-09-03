/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.controller.util;

import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author ALEX
 */

/*
 * Classpath scanning � um recurso do Spring que l� o classpath da aplica��o e busca 
 * classes que possam ser configuradas, isso evita que voc� tenha que declarar 
 * essas classes no XML. Essas classes s�o passadas por um filtro e ent�o uma defini��o 
 * de um bean � criada para elas. Geralemente esse filtro � alguma anota��o que marca que 
 * determinada classe deve ser utilizada como um componente. 
 * Essas anota��es s�o: @Component, @Service, @Controller e @Repository 
 * A anota��o @Component � um tipo gen�rico para qualquer bean que deve ser gerenciado pelo Spring. 
 * @Repository, @Service e @Controller s�o especializa��es de @Component e servem para as camadas 
 * de persistencia, servi�o e apresenta��o respectivamente. Voc� pode utilizar @Component para 
 * qualquer classe da sua aplica��o, mas utilizar uma anota��o mais espec�fica ajuda caso 
 * deseje criar um filtro, utilizar ferramentas, ou orienta��o a aspectos. Funcionalmente 
 * todas as anota��es servem para declarar beans, n�o existe diferen�a entre elas a n�o 
 * ser na quest�o de organiza��o da apliaca��o e utilidades citadas anteriormente.
 */


@Component
@Scope("request")
public class FacesUtils {
        
        private final FacesContext facesContext;
	
        @Autowired
	public FacesUtils(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
	
	public void adicionaMensagemDeErro(String msg) {
		facesContext.addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	public void adicionaMensagemDeInformacao(String msg) {
		facesContext.addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public boolean possuiMensagem(String msg) {
		Iterator<FacesMessage> messages = facesContext.getMessages();
		while (messages.hasNext()) {
			FacesMessage message = messages.next();
			boolean confere = message.getDetail().equals(msg);
			if (confere)
				return true;
		}
		return false;
	}
	
	/**
	 * Limpa os dados dos componentes de edi��o e de seus filhos,
	 * recursivamente. Checa se o componente � inst�ncia de EditableValueHolder
	 * e 'reseta' suas propriedades.
	 * <p>
	 * Quando este m�todo, por algum motivo, n�o funcionar, parta para ignor�ncia
	 * e limpe o componente assim:
	 * <p><blockquote><pre>
	 * 	component.getChildren().clear()
	 * </pre></blockquote>
	 * :-)
	 */
	public void cleanSubmittedValues(UIComponent component) {
		if (component instanceof EditableValueHolder) {
			EditableValueHolder evh = (EditableValueHolder) component;
			evh.setSubmittedValue(null);
			evh.setValue(null);
			evh.setLocalValueSet(false);
			evh.setValid(true);
		}
		if(component.getChildCount()>0){
			for (UIComponent child : component.getChildren()) {
				cleanSubmittedValues(child);
			}
		}
	}
}
