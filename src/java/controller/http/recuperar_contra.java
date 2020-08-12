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
import mx.edu.utez.model.clave.Clave;
import mx.edu.utez.model.clave.DaoClave;
import mx.edu.utez.model.correo.Correo;
import mx.edu.utez.model.empleado.DaoEmpleado;
import mx.edu.utez.model.estudiante.DaoEstudiante;

/**
 *
 * @author alexl
 */
@WebServlet(name = "recuperar_contra", urlPatterns = {"/Recuperacion"})
public class recuperar_contra extends HttpServlet {

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
            redirect = request.getRequestDispatcher("recuperar_contrasenia.jsp");
            redirect.forward(request, response);
        } else if (action.equalsIgnoreCase("validarCorreo")) {
            String email = request.getParameter("correo");
            DaoEstudiante daoEstudiante = new DaoEstudiante();
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            if (daoEstudiante.checkEmail(email)) {
                Correo correo = new Correo();
                DaoClave daoClave = new DaoClave();
                Clave clave = new Clave(0, daoClave.generator(8), 1, daoClave.dateCaducidad(0));
                daoClave.add(clave);
                correo.enviarMail(email, "Restablece tu contraseña utilizando el siguinte código: " + clave.getClave(), "Guia- Restablecimiento de contraseña");

                request.setAttribute("message", "Se ha enviado un codigo para restablecer tu contraseña al correo: " + email);
                request.setAttribute("type", "success");

                request.setAttribute("validacion", "validacion");
                request.setAttribute("email", "email");
                request.setAttribute("correo", email);
            } else if (daoEmpleado.checkEmail(email)) {
                Correo correo = new Correo();
                DaoClave daoClave = new DaoClave();
                Clave clave = new Clave(0, daoClave.generator(8), 1, daoClave.dateCaducidad(0));
                daoClave.add(clave);
//                correo.enviarMail(email, "Restablece tu contraseña utilizando el siguinte código: " + clave.getClave(), "Guia- Restablecimiento de contraseña");

                request.setAttribute("message", "Se ha enviado un codigo para restablecer tu contraseña al correo: " + email);
                request.setAttribute("type", "success");

                request.setAttribute("validacion", "validacion");
                request.setAttribute("email", "email");
                request.setAttribute("correo", email);
            } else {
                request.setAttribute("message", "El correo: " + email + " no esta registrado");
                request.setAttribute("type", "warning");

            }
            redirect = request.getRequestDispatcher("recuperar_contrasenia.jsp");
            redirect.forward(request, response);
        } else if (action.equalsIgnoreCase("validarCodigo")) {
            String codigo = request.getParameter("clave");
            String correo = request.getParameter("correo");
            DaoClave daoClave = new DaoClave();
            Clave clave = daoClave.searchOne(codigo);
            if (clave == null) {
                request.setAttribute("email", "email");
                request.setAttribute("message", "El codigo: " + codigo + " no es correcto");
                request.setAttribute("type", "warning");
                redirect = request.getRequestDispatcher("recuperar_contrasenia.jsp");
                redirect.forward(request, response);
            }
            if (daoClave.checkClave(clave)) {
                request.setAttribute("email", "email");
                request.setAttribute("message", "Parece que el código: " + codigo + " a expirado");
                request.setAttribute("type", "warning");
                redirect = request.getRequestDispatcher("recuperar_contrasenia.jsp");
                redirect.forward(request, response);
            }
            daoClave.delete(clave.getId());
            request.setAttribute("email", "email");
            request.setAttribute("message", "Ingresa una nueva contraseña!");
            request.setAttribute("type", "success");
            request.setAttribute("Cambiar", "Cambiar");
            request.setAttribute("correo", correo);
            redirect = request.getRequestDispatcher("recuperar_contrasenia.jsp");
            redirect.forward(request, response);
        } else if (action.equalsIgnoreCase(action)) {
            String password = request.getParameter("password");
            String correo = request.getParameter("correo");
            DaoEstudiante daoEstudiante = new DaoEstudiante();
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            if (daoEstudiante.checkEmail(correo)) {
                daoEstudiante.updatePassword(password, correo);
                
            }else{
                daoEmpleado.updatePassword(password, correo);
            }
            redirect = request.getRequestDispatcher("recuperar_contrasenia.jsp");
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
