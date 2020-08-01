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
import javax.servlet.http.HttpSession;
import mx.edu.utez.model.disponibilidad.DaoDisponibilidad;
import mx.edu.utez.model.disponibilidad.Disponibilidad;
import mx.edu.utez.model.empleado.DaoEmpleado;
import mx.edu.utez.model.empleado.Empleado;
import mx.edu.utez.model.estudiante.DaoEstudiante;
import mx.edu.utez.model.estudiante.Estudiante;
import mx.edu.utez.model.materia.DaoMateria;
import mx.edu.utez.model.materia.EmpleadoMateria;
import mx.edu.utez.model.materia.Materia;
import mx.edu.utez.model.rango_hora.DaoRango_Hora;
import mx.edu.utez.model.rango_hora.Rango_Hora;
import mx.edu.utez.model.solicitud_asesoria.DaoSolicitud_Asesoria;
import mx.edu.utez.model.solicitud_asesoria.Solicitud_Asesoria;
import mx.edu.utez.model.usuario.Usuario;

/**
 *
 * @author alexl
 */
@WebServlet(name = "registro_asesoriaServlet", urlPatterns = {"/Agendar-asesoria"})
public class registro_asesoriaServlet extends HttpServlet {

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
        Estudiante estudiante = (Estudiante) sesionUsuario.getAttribute("estudiante");
        DaoMateria daoMateria = new DaoMateria();
        DaoEmpleado daoEmpleado = new DaoEmpleado();
        int id_materia = 0;
        int id_empleado = 0;
        if (action == null) {
            //Iterar materias 

        } else if (action.equalsIgnoreCase("registrarAsesoria")) {
            //Tomar los paramentros
            String empleado = request.getParameter("empleado");
            String materia = request.getParameter("materia");
            String horario = request.getParameter("horario");
            String tema = request.getParameter("tema");

            //Objetos necesarios
            DaoEstudiante daoEstudiante = new DaoEstudiante();
            DaoSolicitud_Asesoria daoAsesoria = new DaoSolicitud_Asesoria();
            // Construir la asesoría 
            Solicitud_Asesoria solicitud = new Solicitud_Asesoria(0, daoEmpleado.findOne(Integer.parseInt(empleado)), daoMateria.findOne(Integer.parseInt(materia)), tema, estudiante, daoAsesoria.fechaActual(), 1);
            //Añadirla a la base de datos 
            daoAsesoria.add(solicitud);

            //Redireacionar
            request.setAttribute("message", "asesoría registrada correctamente!");
            request.setAttribute("type", "success");

        } else if (action.equals("formMaterias")) {
            id_materia = Integer.parseInt(request.getParameter("materia"));
            ArrayList<EmpleadoMateria> empleados = daoMateria.findAllAsesorias(id_materia);
            request.setAttribute("empleados", empleados);

            Materia selectMatera = daoMateria.findOne(id_materia);
            request.setAttribute("selectMatera", selectMatera);

        } else if (action.equals("formEmpleados")) {

            id_empleado = Integer.parseInt(request.getParameter("empleado"));
            id_materia = Integer.parseInt(request.getParameter("id_materia"));
            
            ArrayList<EmpleadoMateria> empleados = daoMateria.findAllAsesorias(id_materia);
            request.getSession().setAttribute("empleados", empleados);
            DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();
            ArrayList<Disponibilidad> disponibilidades = daoDisponibilidad.findEmpleado(id_empleado);
            request.getSession().setAttribute("disponibilidades", disponibilidades);

            Empleado selectEmpleado = daoEmpleado.findOne(id_empleado);
           request.getSession().setAttribute("selectEmpleado", selectEmpleado);
           
            //Redireacionar
            request.setAttribute("message", "xd!");
            request.setAttribute("type", "success");

        }
        ArrayList<Materia> materias = daoMateria.findAll();
        request.getSession().setAttribute("materias", materias);
        redirect = request.getRequestDispatcher("views/alumno/registro_asesoria.jsp");
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
