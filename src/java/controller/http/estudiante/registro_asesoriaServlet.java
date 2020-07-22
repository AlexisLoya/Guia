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
import mx.edu.utez.model.empleado.DaoEmpleado;
import mx.edu.utez.model.empleado.Empleado;
import mx.edu.utez.model.materia.DaoMateria;
import mx.edu.utez.model.materia.Materia;
import mx.edu.utez.model.rango_hora.DaoRango_Hora;
import mx.edu.utez.model.rango_hora.Rango_Hora;

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
        if (action == null) {
            //Iterar materias 
            DaoMateria daoMateria = new DaoMateria();
            ArrayList<Materia> materias = daoMateria.findAll();
            request.setAttribute("materias", materias);
            
            //Iterar empleados 
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            ArrayList<Empleado> empleados = daoEmpleado.findAll();
            request.setAttribute("empleados", empleados);
            
            //Iterar Horarios disponibles
            DaoRango_Hora rangoHora = new DaoRango_Hora();
            ArrayList<Rango_Hora> horarios = rangoHora.findAll();
            request.setAttribute("horarios", horarios);
            
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
