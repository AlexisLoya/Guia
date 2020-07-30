/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.http.empleado;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.edu.utez.model.empleado.DaoEmpleado;
import mx.edu.utez.model.empleado.Empleado;
import mx.edu.utez.model.materia.DaoMateria;
import mx.edu.utez.model.materia.EmpleadoMateria;
import mx.edu.utez.model.materia.Materia;
import mx.edu.utez.model.usuario.Usuario;

/**
 *
 * @author alexl
 */
@WebServlet(name = "MateriaServlet", urlPatterns = {"/Materias"})
public class MateriaServlet extends HttpServlet {

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
        String action = request.getParameter("action");
        RequestDispatcher redirect = null;
        HttpSession sesionUsuario = request.getSession(true);
        Usuario usuario = (Usuario) sesionUsuario.getAttribute("usuario");
        Empleado empleado = (Empleado) sesionUsuario.getAttribute("empleado");
        if (usuario == null || usuario.getRol() != "Profesor") {
            response.sendRedirect("/Iniciar Sesion.jsp");
        }
        if (action == null) {
            DaoMateria daoMateria = new DaoMateria();
            ArrayList<Materia> materias = daoMateria.findAll();
            request.setAttribute("materias", materias);

            ArrayList<EmpleadoMateria> asesorias = daoMateria.findAllEmpleado(empleado.getId());
            request.setAttribute("asesorias", asesorias);
            redirect = request.getRequestDispatcher("views/profesor/materias.jsp");
            redirect.forward(request, response);

        } else if (action.equalsIgnoreCase("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id_a"));
            DaoMateria daoMateria = new DaoMateria();
            daoMateria.deleteEmpleado(id);
            redirect = request.getRequestDispatcher("views/profesor/materias.jsp");
            redirect.forward(request, response);

        } else if (action.equals("añadir")) {
            DaoMateria daoMateria = new DaoMateria();

            int id = Integer.parseInt(request.getParameter("id_b"));
            daoMateria.addEmpleado(daoMateria.findOne(id), empleado);
            request.setAttribute("message", "Materia añadida");
            request.setAttribute("type", "success");
            redirect = request.getRequestDispatcher("views/profesor/agenda.jsp");
            redirect.forward(request, response);
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
