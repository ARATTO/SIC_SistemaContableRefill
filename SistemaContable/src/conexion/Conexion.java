
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class Conexion{
    public Conexion(){
            
        }
    
        public Connection conexion(){
        Connection conexion = null;
        
          try {
                conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contables", "postgres", "Sistemas");
                
            
                } catch (SQLException ex) {
                    Logger.getLogger(ex.getLocalizedMessage());

                }
          
         
          
          return conexion;
    }
    
}
