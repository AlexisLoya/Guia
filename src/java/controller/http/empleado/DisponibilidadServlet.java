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
import mx.edu.utez.model.cuatrimestre.Cuatrimestre;
import mx.edu.utez.model.cuatrimestre.DaoCuatrimestre;
import mx.edu.utez.model.dia.DaoDia;
import mx.edu.utez.model.dia.Dia;
import mx.edu.utez.model.disponibilidad.DaoDisponibilidad;
import mx.edu.utez.model.disponibilidad.Disponibilidad;
import mx.edu.utez.model.empleado.DaoEmpleado;
import mx.edu.utez.model.empleado.Empleado;
import mx.edu.utez.model.rango_hora.DaoRango_Hora;
import mx.edu.utez.model.rango_hora.Rango_Hora;

/**
 *
 * @author alexl
 */
@WebServlet(name = "DisponibilidadServlet", urlPatterns = {"/Disponibilidad"})
public class DisponibilidadServlet extends HttpServlet {

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
        HttpSession sesion = request.getSession(true);
            Empleado empleado = (Empleado) sesion.getAttribute("empleado");
        if (action == null) {
            //iterar los dias en la aplicación
            DaoDia daoDia = new DaoDia();
            ArrayList<Dia> dias = daoDia.findAll();
            request.setAttribute("dias", dias);

            //iterar las horas en la aplicación
            DaoRango_Hora daoHora = new DaoRango_Hora();
            ArrayList<Rango_Hora> horas = daoHora.findAll();
            request.setAttribute("horas", horas);   

            //iterar los cuatrimestres en la aplicación
            DaoCuatrimestre daoCutrimestre = new DaoCuatrimestre();
            ArrayList<Cuatrimestre> cuatrimestres = daoCutrimestre.findAll();
            request.setAttribute("cuatrimestres", cuatrimestres);

            DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();
            ArrayList<Disponibilidad> asesorias = daoDisponibilidad.findEmpleado(2);
            request.setAttribute("asesorias", asesorias);

            redirect = request.getRequestDispatcher("views/profesor/disponibilidad.jsp");
            redirect.forward(request, response);
        } else if (action.equalsIgnoreCase("regisotrarHorario")) {
            int pdia = Integer.parseInt(request.getParameter("dia"));
            DaoDia dia = new DaoDia();

            int phora = Integer.parseInt(request.getParameter("hora"));
            DaoRango_Hora hora = new DaoRango_Hora();

            int pcuatrimestre = Integer.parseInt(request.getParameter("cuatrimestre"));
            DaoCuatrimestre cuatrimestre = new DaoCuatrimestre();

            //prueba
            DaoEmpleado pp = new DaoEmpleado();
            Disponibilidad disponibilidad = new Disponibilidad(0, pp.findOne(4), dia.findOne(pdia), hora.findOne(phora), cuatrimestre.findOne(pcuatrimestre));
            DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();
            daoDisponibilidad.add(disponibilidad);
            request.setAttribute("message", "Horario agregado!");
            request.setAttribute("type", "success");

            //Volver a mostrar los datoss
            //iterar los dias en la aplicación
            DaoDia daoDia = new DaoDia();
            ArrayList<Dia> dias = daoDia.findAll();
            request.setAttribute("dias", dias);

            //iterar las horas en la aplicación
            DaoRango_Hora daoHora = new DaoRango_Hora();
            ArrayList<Rango_Hora> horas = daoHora.findAll();
            request.setAttribute("horas", horas);

            //iterar los cuatrimestres en la aplicación
            DaoCuatrimestre daoCutrimestre = new DaoCuatrimestre();
            ArrayList<Cuatrimestre> cuatrimestres = daoCutrimestre.findAll();
            request.setAttribute("cuatrimestres", cuatrimestres);

            //iterar horarios
            ArrayList<Disponibilidad> asesorias = daoDisponibilidad.findEmpleado(empleado.getId());
            request.setAttribute("asesorias", asesorias);

            redirect = request.getRequestDispatcher("views/profesor/disponibilidad.jsp");
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
