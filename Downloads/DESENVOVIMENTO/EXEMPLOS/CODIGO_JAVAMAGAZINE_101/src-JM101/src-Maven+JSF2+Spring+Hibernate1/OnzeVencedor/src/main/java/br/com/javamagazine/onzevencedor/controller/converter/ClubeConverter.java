/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.javamagazine.onzevencedor.entity.Clube;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ALEX
 */
@FacesConverter(value="clubeConverter", forClass=Clube.class)  
public class ClubeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        System.out.println(value) ; 
        int index = value.indexOf(':');
        if (index != -1) {
            return new Clube(Integer.parseInt(value.substring(0,index)),value.substring(index + 1));
        }
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            Clube optionItem = (Clube) value;
            return optionItem.getIdf() + ":" + optionItem.getNome();
        } catch (Exception e) {
        }
        return (String) value;
    }
}
