<%@page import="modelo.Producto" %>
<%@page import="modelo.Marca" %>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    </head>
    <body>
        <h1 align="left" >Productos</h1>        
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal_producto" onclick="limpiar()">Nuevo</button>
        
        <div class="container">
            <div class="modal" id="modal_producto">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <form action="sr_producto" method="post" class="form-group">
                                <label for="lbl_id" ><b>ID del Producto</b></label>
                                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                                <label for="lbl_producto" ><b>Producto</b></label>
                                <input type="text" name="txt_producto" id="txt_producto" class="form-control" placeholder="Ejemplo: Pan" required>
                                <label for="lbl_marca" ><b>Marca del producto</b></label>
                                <select name="drop_idMarca" id="drop_idMarca" class="form-control">
                                <%
                                    Marca marca = new Marca();
                                    HashMap<String,String> drop = marca.drop_sangre();
                                    for(String i: drop.keySet()){
                                        out.println("<option value='" + i + "' >" + drop.get(i) +"</option>");
                                    }
                                %>
                                </select>                                
                                
                                <label for="lbl_descripcion" ><b>Descripcion</b></label>
                                <input type="text" name="txt_descripcion" id="txt_descripcion" class="form-control" placeholder="Ejemplo: Es para..." required>
                                <label for="lbl_precio_costo" ><b>Precio costo</b></label>
                                <input type="number" step="0.1" name="txt_precio_costo" id="txt_precio_costo" class="form-control" placeholder="Ejemplo: 1.50" required>
                                <label for="lbl_precio_venta" ><b>Precio venta</b></label>
                                <input type="number" step="0.1" name="txt_precio_venta" id="txt_precio_venta" class="form-control" placeholder="Ejemplo: 2.00" required>                                
                                <label for="lbl_existencia" ><b>Existencia</b></label>
                                <input type="number" name="txt_existencia" id="txt_existencia" class="form-control" placeholder="Ejemplo: 1000" required>
                                <br>
                                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-primary btn-lg">Agregar</button>
                                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-success btn-lg">Modificar</button>
                                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger btn-lg" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false">Eliminar</button>
                                <button type="button" class="btn btn-warning btn-lg" data-dismiss="modal">Cerrar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <br>                
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th>Producto</th>
                        <th>Marca</th>
                        <th>Existencia</th>                        
                        <th>Precio costo</th>
                        <th>Precio venta</th>
                        <th>Descripcion</th>
                    </tr>
                </thead>
                <tbody id="tbl_producto">
                <%
                    Producto producto = new Producto();
                    DefaultTableModel tabla = new DefaultTableModel();
                    tabla = producto.leer();
                    for (int t=0;t<tabla.getRowCount();t++){
                        out.println("<tr data-id=" + tabla.getValueAt(t,0) + " data-id_t=" + tabla.getValueAt(t,3) + ">");
                        out.println("<td>" + tabla.getValueAt(t,1) + "</td>");
                        out.println("<td>" + tabla.getValueAt(t,2) + "</td>");
                        out.println("<td>" + tabla.getValueAt(t,4) + "</td>");
                        out.println("<td>" + tabla.getValueAt(t,5) + "</td>");
                        out.println("<td>" + tabla.getValueAt(t,6) + "</td>");
                        out.println("<td>" + tabla.getValueAt(t,7) + "</td>");
                        out.println("</tr>");
                    }
                %>
                </tbody>
            </table>
        </div>
                
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>    
        <script>
            function limpiar(){
                $("#txt_id").val(0);
                $("#txt_producto").val('');
                $("#drop_idMarca").val(1);
                $("#txt_existencia").val('');
                $("#txt_descripcion").val('');
                $("#txt_precio_costo").val('');
                $("#txt_precio_venta").val('');
            }
            $('#tbl_producto').on('click','tr td', function(evt){
                var target,id,id_t,producto,existencia,descripcion,precio_costo,precio_venta;
                target = $(event.target);
                id = target.parent().data('id');
                id_t = target.parent().data('id_t');
                producto= target.parents("tr").find("td").eq(0).html();
                existencia= target.parents("tr").find("td").eq(2).html();
                descripcion= target.parents("tr").find("td").eq(3).html();
                precio_costo= target.parents("tr").find("td").eq(4).html();
                precio_venta= target.parents("tr").find("td").eq(5).html();
                
                $("#txt_id").val(id);
                $("#txt_producto").val(producto);
                $("#drop_idMarca").val(id_t);
                $("#txt_existencia").val(existencia);
                $("#txt_precio_costo").val(descripcion);
                $("#txt_precio_venta").val(precio_costo);
                $("#txt_descripcion").val(precio_venta);
                
                $("#modal_producto").modal('show');
            });
        </script>
    </body>
</html>
