package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public Connection getConexion() {
        Connection cn;
        
        try {
          Class.forName("org.apache.derby.jdbc.ClientDriver");  
          cn=DriverManager.getConnection("jdbc:derby://localhost:1527/SupermercadoPiscoya","jesus","piscoya");
        } catch (ClassNotFoundException e) {
            cn=null;
        } catch (SQLException e) {
            cn=null;
        }
        return cn;
    }
}
