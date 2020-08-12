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
import mx.edu.utez.model.correo.Correo;
import mx.edu.utez.model.empleado.Empleado;
import mx.edu.utez.model.estudiante.Estudiante;
import mx.edu.utez.model.solicitud_asesoria.DaoSolicitud_Asesoria;
import mx.edu.utez.model.solicitud_asesoria.Solicitud_Asesoria;
import mx.edu.utez.model.usuario.Usuario;

/**
 *
 * @author alexl
 */
@WebServlet(name = "SolicitudServlet", urlPatterns = {"/Solicitud"})
public class SolicitudServlet extends HttpServlet {

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
            ArrayList<Solicitud_Asesoria> solicitudes = daoSolicitud.empleadoAsesoria(empleado.getId());
            request.setAttribute("solicitudes", solicitudes);
            redirect = request.getRequestDispatcher("views/profesor/solicitud.jsp");
            redirect.forward(request, response);

        } else if (action.equals("aceptarAsesoria")) {
            int id_solicitud = Integer.parseInt(request.getParameter("id_solicitud"));
            if (daoSolicitud.aceptarAsesoria(id_solicitud)) {

                request.setAttribute("message", "Asesoría aceptada!");
                request.setAttribute("type", "success");
            } else {
                request.setAttribute("message", "Ocurrió un error al aceptar la asesoría");
                request.setAttribute("type", "warning");
            }

            ArrayList<Solicitud_Asesoria> solicitudes = daoSolicitud.empleadoAsesoria(empleado.getId());
            request.setAttribute("solicitudes", solicitudes);

            redirect = request.getRequestDispatcher("views/profesor/solicitud.jsp");
            redirect.forward(request, response);

        } else if (action.equalsIgnoreCase("rechazarAsesoria")) {
            int id_solicitud = Integer.parseInt(request.getParameter("id_solicitud"));
            Solicitud_Asesoria solicitud_Asesoria = daoSolicitud.findOne(id_solicitud);
            Estudiante estudiante = solicitud_Asesoria.getEstudiante().get(0);
            String motivo = request.getParameter("motivo");
            String asunto = "Asesoría rechaza por el profesor:  " + solicitud_Asesoria.getEmpleado().getPersona().getNombre() + " "
                    + solicitud_Asesoria.getEmpleado().getPersona().getPaterno() + " " + solicitud_Asesoria.getEmpleado().getPersona().getMaterno();
            Correo correo = new Correo();

            correo.enviarMail("20193tn151@utez.edu.mx", "Motivo:\n"+motivo, asunto);
            daoSolicitud.rechazarAsesoria(id_solicitud);
            request.setAttribute("message", "Asesoría Rechazada");
            request.setAttribute("type", "warning");

            redirect = request.getRequestDispatcher("views/profesor/solicitud.jsp");
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
