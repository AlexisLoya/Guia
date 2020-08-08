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
import mx.edu.utez.model.estudiante.Estudiante;
import mx.edu.utez.model.invitado.DaoInvitado;
import mx.edu.utez.model.invitado.Invitado;
import mx.edu.utez.model.pase_lista.DaoPase_Lista;
import mx.edu.utez.model.pase_lista.Pase_Lista;
import mx.edu.utez.model.solicitud_asesoria.DaoSolicitud_Asesoria;
import mx.edu.utez.model.solicitud_asesoria.Solicitud_Asesoria;
import mx.edu.utez.model.usuario.Usuario;

/**
 *
 * @author alexl
 */
@WebServlet(name = "PaseLista", urlPatterns = {"/Asistencia"})
public class PaseLista extends HttpServlet {

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
        RequestDispatcher redirect = null;
        String action = request.getParameter("action");

        HttpSession sesionUsuario = request.getSession(true);
        Usuario usuario = (Usuario) sesionUsuario.getAttribute("usuario");
        Empleado empleado = (Empleado) sesionUsuario.getAttribute("empleado");
        DaoSolicitud_Asesoria daoSolicitud = new DaoSolicitud_Asesoria();
        if (action == null) {

        } else if (action.equalsIgnoreCase("asistencia")) {
            int id_estudiante = Integer.parseInt(request.getParameter("id_estudiante"));
            int id_asesoria = Integer.parseInt(request.getParameter("id_asesoria"));

            Solicitud_Asesoria asesoria = daoSolicitud.findOne(id_asesoria);
            request.setAttribute("asesoria", asesoria);

            DaoInvitado daoInvitado = new DaoInvitado();
            ArrayList<Invitado> invitados = daoInvitado.estudiantefindAll(id_asesoria);
            request.setAttribute("invitados", invitados);

            redirect = request.getRequestDispatcher("views/profesor/pase_lista.jsp");
            redirect.forward(request, response);

        } else if (action.equalsIgnoreCase("asistenciaAll")) {
            int id_asesoria = Integer.parseInt(request.getParameter("id_asesoria"));
            Solicitud_Asesoria asesoria = daoSolicitud.findOne(id_asesoria);
            request.setAttribute("asesoria", asesoria);

            ArrayList<Estudiante> estudiantes = asesoria.getEstudiante();
            request.setAttribute("estudiantes", estudiantes);

            DaoPase_Lista daoPase_Lista = new DaoPase_Lista();
            for (Estudiante estudiante : estudiantes) {

            }
        }

        int id_asesoria = Integer.parseInt(request.getParameter("id_asesoria"));
        Solicitud_Asesoria asesoria = daoSolicitud.findOne(id_asesoria);
        request.setAttribute("asesoria", asesoria);

        DaoInvitado daoInvitado = new DaoInvitado();
        ArrayList<Invitado> invitados = daoInvitado.estudiantefindAll(id_asesoria);
        request.setAttribute("invitados", invitados);

        redirect = request.getRequestDispatcher("views/profesor/pase_lista.jsp");
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
