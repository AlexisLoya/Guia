/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.http.estudiante;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
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
import mx.edu.utez.model.estudiante.DaoEstudiante;
import mx.edu.utez.model.estudiante.Estudiante;
import mx.edu.utez.model.invitado.DaoInvitado;
import mx.edu.utez.model.invitado.Invitado;
import mx.edu.utez.model.solicitud_asesoria.DaoSolicitud_Asesoria;
import mx.edu.utez.model.solicitud_asesoria.Solicitud_Asesoria;

/**
 *
 * @author alexl
 */
@WebServlet(name = "asesorias_pendientesServlet", urlPatterns = {"/asesorias_pendientes"})
public class asesorias_pendientesServlet extends HttpServlet {

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
        if (sesionUsuario == null) {
            request.setAttribute("message", "Tu sesión a caducado");
            request.setAttribute("type", "warning");
            redirect = request.getRequestDispatcher("/");
            redirect.forward(request, response);
        }
        Estudiante estudiante = null;
        estudiante = (Estudiante) sesionUsuario.getAttribute("estudiante");
        DaoEstudiante dao = new DaoEstudiante();
        if (action == null) {
            //Iterar Asesorias
            DaoSolicitud_Asesoria daoAsesoria = new DaoSolicitud_Asesoria();
            DaoInvitado daoInvitado = new DaoInvitado();

            ArrayList<Invitado> asesorias = daoInvitado.estudiantefindAll(estudiante.getId());
            request.setAttribute("asesorias", asesorias);

            redirect = request.getRequestDispatcher("views/alumno/asesorias_pendientes.jsp");
            redirect.forward(request, response);

        } else if (action.equalsIgnoreCase("addInvitado")) {
            DaoInvitado daoInvitado = new DaoInvitado();
            int id_asesoria = Integer.parseInt(request.getParameter("id_asesoria"));
            String matricula = request.getParameter("matricula");
            Estudiante estudianteAdd = dao.findOneMatricula(matricula);
            if (estudianteAdd != null) {
                if (daoInvitado.estudianteRepetido(id_asesoria, estudianteAdd.getId())) {
                    request.setAttribute("message", "La persona con la matricula: " + matricula + " ya esta registrada en esa asesoria");
                    request.setAttribute("type", "info");
                } else {
                    Invitado invitado = new Invitado(0,new DaoSolicitud_Asesoria().findOne(id_asesoria), estudianteAdd,0);
                    daoInvitado.add(invitado);

                    request.setAttribute("message", "La persona con la matricula: " + matricula + " ha sido agregada" +  estudianteAdd.getId());
                    request.setAttribute("type", "success");
                }
            } else {
                request.setAttribute("message", "No se encontró a nadie con la matricula: " + matricula);
                request.setAttribute("type", "warning");
            }
            ArrayList<Invitado> asesorias = daoInvitado.estudiantefindAll(estudiante.getId());
            request.setAttribute("asesorias", asesorias);
            redirect = request.getRequestDispatcher("views/alumno/asesorias_pendientes.jsp");
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
