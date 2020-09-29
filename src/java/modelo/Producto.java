package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

public class Producto {
    private int id; 
    private int idMarca;
    private int existencia;
    private String producto;
    private String descripcion; 
    private Float precio_costo;
    private Float precio_venta;
    private Conexion cn;
    
    public Producto(){}
    public Producto(int id, int idMarca, int existencia, String producto, String descripcion, Float precio_costo, Float precio_venta) {
        this.id = id;
        this.idMarca = idMarca;
        this.existencia = existencia;
        this.producto = producto;
        this.descripcion = descripcion;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(Float precio_costo) {
        this.precio_costo = precio_costo;
    }

    public Float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Float precio_venta) {
        this.precio_venta = precio_venta;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT p.idProducto as id, p.producto, m.marca, m.idMarca, p.descripcion, p.precio_costo, p.precio_venta, p.existencia FROM productos as p inner join marcas as m on p.idMarca = m.idMarca;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[]={"id","producto","marca","idMarca","existencia","precio_costo","precio_venta","descripcion"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[8];
            while(consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("producto");
                datos[2] = consulta.getString("marca");
                datos[3] = consulta.getString("idMarca");
                datos[4] = consulta.getString("existencia");
                datos[5] = consulta.getString("precio_costo");
                datos[6] = consulta.getString("precio_venta");
                datos[7] = consulta.getString("descripcion");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return tabla;
    }
    
    public int agregar(){
    int retorno = 0;
    try{
        PreparedStatement parametro;
        cn = new Conexion();
        String query="INSERT INTO productos(producto,idMarca,descripcion,precio_costo,precio_venta,existencia) VALUES (?,?,?,?,?,?);";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getProducto());
        parametro.setInt(2, getIdMarca());
        parametro.setString(3, getDescripcion());
        parametro.setFloat(4, getPrecio_costo());
        parametro.setFloat(5, getPrecio_venta());
        parametro.setInt(6, getExistencia());
        retorno=parametro.executeUpdate();
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno=0;
    }
    return retorno;
    }

    public int modificar(){
    int retorno = 0;
    try{
        PreparedStatement parametro;
        cn = new Conexion();
        String query= "update productos set producto=?,idMarca=?,descripcion=?,precio_costo=?,precio_venta=?,existencia=? where idProducto = ?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getProducto());
        parametro.setInt(2, getIdMarca());
        parametro.setString(3, getDescripcion());
        parametro.setFloat(4, getPrecio_costo());
        parametro.setFloat(5, getPrecio_venta());
        parametro.setInt(6, getExistencia());
        parametro.setInt(7, getId());
        retorno=parametro.executeUpdate();
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno=0;
    }
    return retorno;
    }
   
    public int eliminar(){
    int retorno = 0;
    try{
        PreparedStatement parametro;
        cn = new Conexion();
        String query = "delete from productos where idProducto = ?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setInt(1, getId());
        retorno=parametro.executeUpdate();
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno=0;
    }
    return retorno;
    }
}
