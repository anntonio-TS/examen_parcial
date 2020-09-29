package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;

public class sr_producto extends HttpServlet {


    Producto producto;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_producto</title>");            
            out.println("</head>");
            out.println("<body>");
                
            producto = new Producto(Integer.valueOf(request.getParameter("txt_id")),Integer.valueOf(request.getParameter("drop_idMarca")),Integer.valueOf(request.getParameter("txt_existencia")),request.getParameter("txt_producto"),request.getParameter("txt_descripcion"),Float.parseFloat(request.getParameter("txt_precio_costo")),Float.parseFloat(request.getParameter("txt_precio_venta")));
            //AGREGAR
                if("agregar".equals(request.getParameter("btn_agregar"))){
                    if(producto.agregar() > 0){ 
                        response.sendRedirect("index.jsp");
                    }else{
                        out.println("<h1>No se Agrego...........</h1>");
                        out.println("<a href ='index.jsp'>Regresar</a>");
                    }
                }
            //MODIFICAR
                if("modificar".equals(request.getParameter("btn_modificar"))){
                    if(producto.modificar() > 0){ 
                        response.sendRedirect("index.jsp");
                    }else{
                        out.println("<h1>No se modifico...........</h1>");
                        out.println("<a href ='index.jsp'>Regresar</a>");
                    }
                }
            //ELIMINAR
                if("eliminar".equals(request.getParameter("btn_eliminar"))){
                    if(producto.eliminar() > 0){ 
                    response.sendRedirect("index.jsp");
                    }else{
                        out.println("<h1>No se eliminar...........</h1>");
                        out.println("<a href ='index.jsp'>Regresar</a>");
                    }
                }
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
