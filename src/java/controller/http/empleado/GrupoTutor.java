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
import mx.edu.utez.model.empleado.Empleado;
import mx.edu.utez.model.grupo.DaoGrupo;
import mx.edu.utez.model.grupo.Grupo;
import mx.edu.utez.model.grupo_Tutor.DaoGrupo_Tutor;
import mx.edu.utez.model.grupo_Tutor.Grupo_Tutor;
import mx.edu.utez.model.grupo_estudiante.DaoGrupo_Estudiante;
import mx.edu.utez.model.grupo_estudiante.Grupo_Estudiante;
import mx.edu.utez.model.usuario.Usuario;

/**
 *
 * @author alexl
 */
@WebServlet(name = "GrupoTutor", urlPatterns = {"/Tutor"})
public class GrupoTutor extends HttpServlet {

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
        HttpSession sesionUsuario = request.getSession(true);
        Usuario usuario = (Usuario) sesionUsuario.getAttribute("usuario");
        Empleado empleado = (Empleado) sesionUsuario.getAttribute("empleado");

        if (action == null) {
            DaoGrupo daoGrupo = new DaoGrupo();
            ArrayList<Grupo> grupos = daoGrupo.findAll();
            request.setAttribute("grupos", grupos);

        } else if (action.equalsIgnoreCase("addGrupo")) {
            int id_grupo = Integer.parseInt(request.getParameter("id_grupo"));

            DaoGrupo daoGrupo = new DaoGrupo();
            ArrayList<Grupo> grupos = daoGrupo.findAll();
            request.setAttribute("grupos", grupos);

            Grupo_Tutor grupo_Tutor = new Grupo_Tutor(0, daoGrupo.findOne(id_grupo), empleado);
            DaoGrupo_Tutor daoGrupo_Tutor = new DaoGrupo_Tutor();
            daoGrupo_Tutor.add(grupo_Tutor);

            request.setAttribute("message", "Grupo Asignado Correctamente");
            request.setAttribute("type", "success");
        }
        DaoGrupo_Estudiante daoGrupo_Estudiante = new DaoGrupo_Estudiante();
        ArrayList<Grupo_Estudiante> alumnos = daoGrupo_Estudiante.grupoEstudianteAll(3);
        request.setAttribute("alumnos",alumnos);
        
        redirect = request.getRequestDispatcher("views/profesor/grupo.jsp");
        redirect.forward(request, response);
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
