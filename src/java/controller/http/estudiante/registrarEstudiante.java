/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.http.estudiante;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.utez.model.empleado.DaoEmpleado;
import mx.edu.utez.model.empleado.Empleado;
import mx.edu.utez.model.estudiante.DaoEstudiante;
import mx.edu.utez.model.estudiante.Estudiante;
import mx.edu.utez.model.grado.Grado;
import mx.edu.utez.model.grupo.Grupo;
import mx.edu.utez.model.persona.DaoPersona;
import mx.edu.utez.model.persona.Persona;

/**
 *
 * @author alexl
 */
@WebServlet(name = "registrarUsuario", urlPatterns = {"/Registro"})
public class registrarEstudiante extends HttpServlet {

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
        // response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("acction");
        RequestDispatcher redirect = null;
        
        if (action == null) {
            ArrayList<Grado> grados = new ArrayList<>();
            grados.add(new Grado(1, "1ro"));
            grados.add(new Grado(2, "2do"));
            grados.add(new Grado(3, "3er"));
            grados.add(new Grado(4, "4to"));
            
            request.setAttribute("grados", grados);
            redirect = request.getRequestDispatcher("Registro.jsp");
            redirect.forward(request, response);
        }

        else if (action.equalsIgnoreCase("regisotroEstudiante")) {
            String nombre = request.getParameter("nombre");
            String paterno = request.getParameter("paterno");
            String materno = request.getParameter("materno");
            String email = request.getParameter("email");
            String matricula = request.getParameter("matricula");
            String sexo = request.getParameter("sexoOption");
            String password = request.getParameter("password");
            int status = 1;

            //Tomar los paramentros de persona
            DaoPersona daoPersona = new DaoPersona();
            Persona persona = new Persona(0, status, sexo, nombre, paterno, materno);
            int idPersona = daoPersona.add(persona);
            persona.setId(idPersona);
            //Tomar los paramentros de estudiante
            DaoEstudiante daoEstudiante = new DaoEstudiante();
            Estudiante estudiante = new Estudiante(0, persona, matricula, email, password);
            int idEstudiante = daoEstudiante.add(estudiante);
            estudiante.setId(idEstudiante);
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
