/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servletPackage;

import FileSystem.FileAdministrator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import servletPackage.logic.*;

/**
 *
 * @author anagu
 */
public class ProcessCommand extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {

            // Read the request body
            BufferedReader reader = request.getReader();
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
            reader.close();

            // Process the request body
            String requestBodyString = requestBody.toString();

            //Parse the JSON request body
            ObjectMapper objectMapper = new ObjectMapper();
            RequestData requestData;
            requestData = objectMapper.readValue(requestBody.toString(), RequestData.class);

            // Access the parsed JSON data
            String username = requestData.getUser();
            String command = requestData.getCommand();
//            
//            /* TODO output your page here. You may use following sample code. */
//            
            out.println(requestData.getUser() + " requested command: " + requestData.getCommand() + "with params" + requestData.getParameters().get(0));
            
            FileAdministrator administrator = new FileAdministrator();
            switch (command) {
                case "cd":
                    // Handle "cd" command
                    out.println("Aqui muestra lo que hace el cd :v");
                    break;
                case "edit":
                    // Handle "edit" command
                    break;
                case "help":
                    // Handle "help" command
                    break;
                case "log":
                    // Handle "log" command
                    break;
                case "ls":
                    // Handle "ls" command
                    
                    administrator.listarDirectorios(folder);
                    break;
                case "mkDir":
                    // Handle "mkDir" command
                    break;
                case "mkDrv":
                    // Handle "mkDrv" command
                    break;
                case "mv":
                    // Handle "mv" command
                    break;
                case "prop":
                    // Handle "prop" command
                    break;
                case "rm":
                    // Handle "rm" command
                    break;
                case "rmdir":
                    // Handle "rmdir" command
                    break;
                case "rv":
                    // Handle "rv" command
                    break;
                case "share":
                    // Handle "share" command
                    break;
                case "touch":
                    // Handle "touch" command
                    break;
                case "vr":
                    // Handle "vr" command
                    break;
                case "vv":
                    // Handle "vv" command
                    break;
                default:
                    // Handle unknown command
                    break;
            }

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
