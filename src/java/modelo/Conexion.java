package modelo;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class Conexion {
    public Connection conexionBD;
    private final String puerto= "3306";
    private final String bd= "parcial_progra";
    private final String urlConexion = String.format("jdbc:mysql://127.0.0.1:%s/%s?serverTimezone=UTC",puerto, bd);
    private final String usuario = "Antonio";
    private final String contra = "hola";
    private final String jdbc ="com.mysql.cj.jdbc.Driver";
    
     public void abrir_conexion(){
            try{
                Class.forName(jdbc);
                conexionBD = DriverManager.getConnection(urlConexion,usuario,contra); 
            }catch(ClassNotFoundException | SQLException ex){
                    System.out.println("ERROR" + ex.getMessage());
            }
    }
    
    public void cerrar_conexion(){
        try{
            conexionBD.close();
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
