/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.http.estudiante;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
import mx.edu.utez.model.invitado.DaoInvitado;
import mx.edu.utez.model.invitado.Invitado;
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

        if (action == null) {
            //Borrar atributos de la session
            sesionUsuario.removeAttribute("empleados");
            sesionUsuario.removeAttribute("selectMatera");
            sesionUsuario.removeAttribute("disponibilidades");
            sesionUsuario.removeAttribute("selectEmpleado");
            sesionUsuario.removeAttribute("materias");

            ArrayList<Materia> materias = daoMateria.findAll();
            sesionUsuario.setAttribute("materias", materias);
            redirect = request.getRequestDispatcher("views/alumno/registro_asesoria.jsp");
            redirect.forward(request, response);

        } else if (action.equals("formMaterias")) {
            int id_materia = Integer.parseInt(request.getParameter("materia"));
            ArrayList<EmpleadoMateria> empleados = daoMateria.findAllAsesorias(id_materia);
            sesionUsuario.setAttribute("empleados", empleados);

            Materia selectMatera = daoMateria.findOne(id_materia);
            sesionUsuario.setAttribute("selectMatera", selectMatera);

            //Redireacionar
//            request.setAttribute("message", "empleados:" + empleados + "\nselectMatera: " + selectMatera);
//            request.setAttribute("type", "success");
            ArrayList<Materia> materias = daoMateria.findAll();
            sesionUsuario.setAttribute("materias", materias);
            redirect = request.getRequestDispatcher("views/alumno/registro_asesoria.jsp");
            redirect.forward(request, response);

        } else if (action.equals("formEmpleados")) {
            int id_materia = Integer.parseInt(request.getParameter("materia"));
            int id_empleado = Integer.parseInt(request.getParameter("empleado"));

            ArrayList<EmpleadoMateria> empleados = daoMateria.findAllAsesorias(id_materia);
            sesionUsuario.setAttribute("empleados", empleados);

            DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();
            ArrayList<Disponibilidad> disponibilidades = daoDisponibilidad.findEmpleado(id_empleado);
            sesionUsuario.setAttribute("disponibilidades", disponibilidades);

            Empleado selectEmpleado = daoEmpleado.findOne(id_empleado);
            sesionUsuario.setAttribute("selectEmpleado", selectEmpleado);

            ArrayList<Materia> materias = daoMateria.findAll();
            sesionUsuario.setAttribute("materias", materias);

            redirect = request.getRequestDispatcher("views/alumno/disponibilidad_asesoria.jsp");
            redirect.forward(request, response);

        } else if (action.equalsIgnoreCase("disponibilidad")) {

            //Tomar los paramentros
            int id_materia = Integer.parseInt(request.getParameter("materia"));
            int id_empleado = Integer.parseInt(request.getParameter("empleado"));
            int id_horario = Integer.parseInt(request.getParameter("disponibilidad"));
            int status = 2;
            String tema = request.getParameter("tema");

            //Objetos necesarios
            DaoSolicitud_Asesoria daoAsesoria = new DaoSolicitud_Asesoria();
            DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();
            Disponibilidad disponibilidad = daoDisponibilidad.findOne(id_horario);
            // Construir la asesoría 
            String fecha = disponibilidad.getDia().getNombre() + "";
            String hora = disponibilidad.getRango_hora().getInicio() + " - " + disponibilidad.getRango_hora().getFin();
            int add = 0;
            switch (fecha) {
                case "Lunes":
                    add = 1;
                    break;
                case "Martes":
                    add = 2;
                    break;
                case "Miercoles":
                    add = 3;
                    break;
                case "Jueves":
                    add = 4;
                    break;
                case "Viernes":
                    add = 5;
                    break;
                case "Sabado":
                    add = 5;
                    break;
                case "Domingo":
                    add = 7;
                    break;
            }
            Calendar c1 = Calendar.getInstance();
            c1.add(Calendar.DATE, add);
            java.sql.Date date = new Date(c1.getTimeInMillis());
            ArrayList<Estudiante> estudiantes = new ArrayList();
            estudiantes.add(estudiante);
            Solicitud_Asesoria solicitud = new Solicitud_Asesoria(0, daoEmpleado.findOne(id_empleado), daoMateria.findOne(id_materia), tema, estudiantes,date + "", hora, 1, status);

            //Añadirla a la base de datos 
            int id = daoAsesoria.add(solicitud);
            solicitud.setId(id);
            Invitado invitado = new Invitado(0,solicitud, estudiante,1);
            DaoInvitado daoInvitado = new DaoInvitado();
            daoInvitado.add(invitado);
            //Redireacionar
            request.setAttribute("message", "asesoría registrada correctamente!");
            request.setAttribute("type", "success");

            redirect = request.getRequestDispatcher("views/alumno/registro_asesoria.jsp");
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
