/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.utez.model.clave.Clave;
import mx.edu.utez.model.clave.DaoClave;
import mx.edu.utez.model.empleado.DaoEmpleado;
import mx.edu.utez.model.empleado.Empleado;
import mx.edu.utez.model.persona.DaoPersona;
import mx.edu.utez.model.persona.Persona;
import mx.edu.utez.model.rol.DaoRol;
import mx.edu.utez.model.rol.Rol;

/**
 *
 * @author alexl
 */
@WebServlet(name = "registrarEmpleado", urlPatterns = {"/Registro-E"})
public class registrarEmpleado extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String nombre = request.getParameter("nombre");
        String paterno = request.getParameter("paterno");
        String materno = request.getParameter("materno");
        String email = request.getParameter("email");
        String codigo = request.getParameter("codigo");
        String sexo = request.getParameter("sexoOption");

        String password = request.getParameter("password");
        int    status = 1;
        
        // Verificar su Codigo
        Clave clave = null;
        DaoClave daoClave = new DaoClave();
        clave = daoClave.searchOne(codigo);
        //daoClave.checkClave(clave.getId(), clave.getCaducidad());
        
        
        //Tomar los paramentros de persona
        DaoPersona daoPersona = new DaoPersona();
        Persona persona = new Persona(0, status, sexo, nombre, paterno, materno);
        int idPersona = daoPersona.add(persona);
        persona.setId(idPersona);
        
        //Tomar los paramentros de empleado
        ArrayList roles = new ArrayList();
        DaoRol rol = new DaoRol();
        roles.add(rol.findOne(1));
     
        DaoEmpleado daoEmpleado = new DaoEmpleado();
        Empleado empleado = new Empleado(0, persona, email, password, roles);
        int newId = daoEmpleado.add(empleado);
        empleado.setId(newId);
        //daoEmpleado.findOne(status)
        rol.setRolEmpleado(empleado.getId(), rol.findOne(1).getId());
        response.sendRedirect("Iniciar Sesion.jsp");
        
        
        //Validar codigo    
    
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

