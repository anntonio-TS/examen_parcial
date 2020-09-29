package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Marca {
    private int idMarca;
    private String marca;
    private Conexion cn;
    public Marca(){}
    public Marca(int idMarca, String marca) {
        this.idMarca = idMarca;
        this.marca = marca;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public HashMap drop_sangre(){
        HashMap<String,String> drop = new HashMap();
        try{
            cn = new Conexion();
            String query = "SELECT idMarca as id,marca FROM marcas;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while(consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("marca"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return drop;
    }
}
