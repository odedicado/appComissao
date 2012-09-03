/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.onzevencedor.sets;

import java.util.ResourceBundle;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author ALEX
 */
public class Connect extends DriverManagerDataSource {

   // private ResourceBundle jdbc = ResourceBundle.getBundle("br/com/javamagazine/onzevencedor/bundle/jdbc");
    
    public Connect() {
          ResourceBundle jdbc = ResourceBundle.getBundle("jdbc");
          this.setDriverClassName(jdbc.getString("driverClassName"));
          this.setUrl(jdbc.getString("url"));
          this.setUsername(jdbc.getString("username"));
          this.setPassword(jdbc.getString("password"));
         

          //jdbc = ResourceBundle.getBundle("br/com/javamagazine/onzevencedor/bundle/jdbc",ptBR);
         /* this.setDriverClassName("org.postgresql.Driver");
          this.setUrl("jdbc:postgresql://localhost:5432/bd1");
          this.setUsername("postgres");
          this.setPassword("postgres");*/
        //System.out.println("TESTANDO ARQUIVOS DE PRORPEIDADES" + jdbc.getString("driverClassName"));
    }
}
