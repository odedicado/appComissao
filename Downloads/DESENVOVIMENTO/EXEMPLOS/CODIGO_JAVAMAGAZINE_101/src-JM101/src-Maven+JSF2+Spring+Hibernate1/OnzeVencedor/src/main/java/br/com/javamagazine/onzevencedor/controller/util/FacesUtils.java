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
 * Classpath scanning é um recurso do Spring que lê o classpath da aplicação e busca 
 * classes que possam ser configuradas, isso evita que você tenha que declarar 
 * essas classes no XML. Essas classes são passadas por um filtro e então uma definição 
 * de um bean é criada para elas. Geralemente esse filtro é alguma anotação que marca que 
 * determinada classe deve ser utilizada como um componente. 
 * Essas anotações são: @Component, @Service, @Controller e @Repository 
 * A anotação @Component é um tipo genérico para qualquer bean que deve ser gerenciado pelo Spring. 
 * @Repository, @Service e @Controller são especializações de @Component e servem para as camadas 
 * de persistencia, serviço e apresentação respectivamente. Você pode utilizar @Component para 
 * qualquer classe da sua aplicação, mas utilizar uma anotação mais específica ajuda caso 
 * deseje criar um filtro, utilizar ferramentas, ou orientação a aspectos. Funcionalmente 
 * todas as anotações servem para declarar beans, não existe diferença entre elas a não 
 * ser na questão de organização da apliacação e utilidades citadas anteriormente.
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
	 * Limpa os dados dos componentes de edição e de seus filhos,
	 * recursivamente. Checa se o componente é instância de EditableValueHolder
	 * e 'reseta' suas propriedades.
	 * <p>
	 * Quando este método, por algum motivo, não funcionar, parta para ignorância
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
