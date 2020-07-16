/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.edu.utez.model.empleado.DaoEmpleado;
import mx.edu.utez.model.estudiante.DaoEstudiante;
import mx.edu.utez.model.estudiante.Estudiante;
import mx.edu.utez.utils.Consulta;

/**
 *
 * @author alexl
 */
@WebServlet(name = "IniciarSesion", urlPatterns = {"/Iniciar"})
public class IniciarSesion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //Tomar los paramentros
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        
    
        //Validar si existe en la base de datos
        DaoEstudiante estudiante = new DaoEstudiante();
        DaoEmpleado empleado = new DaoEmpleado();
        if(estudiante.autentificacion(email, pass)){
            response.sendRedirect("views/alumno/inicio_alumno.jsp");
           
        }else if (empleado.autentificacion(email,pass)){
            response.sendRedirect("views/profesor/index.html");
                    

        }else{
            response.sendRedirect("Iniciar Sesion.jsp");
        }
            
            

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
