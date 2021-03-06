/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.http;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.edu.utez.model.empleado.DaoEmpleado;
import mx.edu.utez.model.empleado.Empleado;
import mx.edu.utez.model.estudiante.DaoEstudiante;
import mx.edu.utez.model.estudiante.Estudiante;
import mx.edu.utez.model.usuario.Usuario;
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
        String action = request.getParameter("action");
        RequestDispatcher redirect = null;
        if (action == null) {
            redirect = request.getRequestDispatcher("Iniciar Sesion.jsp");
            redirect.forward(request, response);
        } else if (action.equalsIgnoreCase("iniciarSesion")) {
            //Tomar los paramentros
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            //Validar si existe en la base de datos
            DaoEstudiante daoEstudiante = new DaoEstudiante();
            DaoEmpleado daoEmpleado = new DaoEmpleado();

            if (daoEstudiante.check(email, password)) {
                String rol = "Estudiante";
                Estudiante estudiante = daoEstudiante.findOne(daoEstudiante.autentificacion(email, password));
                Usuario usuario = new Usuario(email, password, estudiante.getPersona(), rol);
                // Creación de Sesión para el usuario
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuario);
                session.setAttribute("estudiante", estudiante);
                session.setAttribute("rol", rol);
                request.setAttribute("message", "Bienvenido(a) a Guia!");
                request.setAttribute("type", "success");

                //Redireccionar a su inicio
                redirect = request.getRequestDispatcher("/EstudianteServlet");
                redirect.forward(request, response);

            } else if (daoEmpleado.autentificacion(email, password) != 0) {
                //Crear sesión para el empleado
                String rol = "Profesor";
                Empleado empleado = daoEmpleado.findOne(daoEmpleado.autentificacion(email, password));
                if(empleado.getRoles().size()>1){
                    
                }
                Usuario usuario = new Usuario(email, password, empleado.getPersona(), rol);

                // Creación de Sesión para el usuario
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuario);
                session.setAttribute("empleado", empleado);
                session.setAttribute("rol", rol);
                request.setAttribute("message", "Bienvenido(a) a Guia!");
                request.setAttribute("type", "success");
                //Redireccionar a su inicio
                redirect = request.getRequestDispatcher("/Agenda");
                redirect.forward(request, response);
            } else {
                request.setAttribute("message", "Usuario o Contraseña Incorrecto");
                request.setAttribute("type", "warning");
                redirect = request.getRequestDispatcher("Iniciar Sesion.jsp");
                redirect.forward(request, response);

            }
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
