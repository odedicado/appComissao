/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.test;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 *
 * @author ALEX
 */
public class TestBundleJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Locale currentLocale;
        ResourceBundle messages;

        ResourceBundle jdbc = ResourceBundle.getBundle("jdbc");
        System.out.println("TESTANDO ARQUIVOS DE PRORPEIDADES" + jdbc.getString("driverClassName"));



    }
}
