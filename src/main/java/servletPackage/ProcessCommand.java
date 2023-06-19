/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servletPackage;

import FileSystem.FileAdministrator;
import FileSystem.Folder;
import FileSystem.JsonAdapter;
import FileSystem.MainFileSystem;
import FileSystem.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
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
    MainFileSystem fs = null;
    JsonAdapter fileManager = null;

    protected void startServer() {
        fs = new MainFileSystem();
        fileManager = new JsonAdapter();

        //testing
        //Folder folder = new Folder("user", "c:", "24/01/2002", "Girome", "");
        //User u = new User("Girome", 9999,folder);
        //fs.users.add(u);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (fs == null) {
            startServer();
            fs = fileManager.loadJsonFileSystem();
        }
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
            int memory = requestData.getMemory();

            String command = requestData.getCommand();
            User u = fs.getUser(username, memory); //Cambiar 1000 por el paramatro del size
//            
//            /* TODO output your page here. You may use following sample code. */
//            
            out.println(u.currentFolder.getDirectory() + "->" + requestData.getCommand() + " " + requestData.getParameters());
            //out.println(requestData.getUser() + " requested command: " + requestData.getCommand() + " with params: " + ((requestData.getParameters().size() > 0) ? requestData.getParameters().get(0) : "none"));

            FileAdministrator administrator = new FileAdministrator();
            Folder currFolder = u.currentFolder;
            String result;
            List<String> data;
            Folder folderDestiny;
            switch (command) {
                case "cd":
                    // Handle "cd" command
                    data = requestData.getParameters();
                    out.println(administrator.changeDirectory(u, data.get(0)));
                    out.println(u.getPath(data.get(0), true).getDirectory());
                    out.println(u.getCurrentFolder().getDirectory());
                    break;
                case "cda":
                    // Handle "cd" command
                    data = requestData.getParameters();
                    out.println(administrator.changeADirectory(u,data.get(0)));
                    break;
                case "edit":
                    // Handle "edit" command
                    break;
                case "help":
                    // Handle "help" command
                    out.println( "Comandos disponibles: \n"
                            + "cd: Cambiar de directorio.\n"
                            + "      Uso: cd <path>\n"
                            + "cda: Cambiar de directorio desde el absoluto(raíz).\n"
                            + "      Uso: cda <path>\n"
                            + "help: Mostrar ayuda.\n"
                            + "     Uso: help\n"
                            + "ls: Listar directorios y archivos en el directorio actual.\n"
                            + "     Uso: ls\n"
                            + "mkDir: Crear un folder.\n"
                            + "     Uso: mkDir <nombreFolder>\n"
                            + "mkFile: Crear un archivo.\n"
                            + "    Uso: mkFile <nombreArchivo> <extensión> <contenido>\n"
                            + "updFile: Actualizar el contenido de un archivo.\n"
                            + "    Uso: updFile <nombreArchivo> <nuevoContenido>\n"
                            + "mem: Ver espacio disponible.\n"
                            + "   Uso: mem\n"
                            + "mvA: Mover un archivo.\n"
                            + "   Uso: mvA <nombreArchivo> <rutaDestino>\n"
                            + "mvF: Mover un directorio.\n"
                            + "   Uso: mvF <rutaOrigen> <rutaDestino>\n"
                            + "prop: Ver propiedades de un archivo.\n"
                            + "   Uso: prop <nombreArchivo>\n"
                            + "seeFile: Ver contenido de un archivo.\n"
                            + "   Uso: seeFile <nombreArchivo>\n"
                            + "rmFile: Eliminar un archivo.\n"
                            + "    Uso: rmFile <nombreArchivo>\n"
                            + "rmDir: Eliminar un directorio.\n"
                            + "    Uso: rmDir <nombreDirectorio>\n"
                            + "vr: Descargar un archivo.\n"
                            + "    Uso: vr <nombreArchivo>\n"
                            + "vvA: Copiar un archivo a una ruta específica.\n"
                            + "    Uso: vvA <nombreArchivo> <rutaDestino>\n"
                            + "vvF: Copiar un directorio a una ruta específica.\n"
                            + "   Uso: vvF <rutaOrigen> <rutaDestino>\n"
                    );
                    break;
                case "log":
                    // Handle "log" command
                    break;
                case "ls":
                    // Handle "ls" command

                    // ls
                    String list = administrator.listarDirectorios(currFolder);
                    out.println(list);
                    break;
                case "mkDir":
                    // Handle "mkDir" command

                    // mkDir nombreFolder
                    data = requestData.getParameters();
                    result = administrator.createFolder(currFolder, data.get(0),
                            currFolder.getDirectory() + data.get(0) + "/",
                            administrator.getCurrentDate(), u.getName(),
                            currFolder.getDirectory() + data.get(0) + "/");
                    out.println(result);
                    break;
                case "mkFile":
                    // mkFile filename extension content
                    data = requestData.getParameters();
                    String content = "";
                    for (int i = 2; i < data.size(); i++) {
                        content += data.get(i) + (i == data.size() - 1 ? "" : " ");
                    }
                    String logicalLocation = currFolder.getDirectory() + data.get(0) + "." + data.get(1) + "/";
                    // call calculate size calculateSize(int added, int removed)
                    result = administrator.createFile(currFolder, data.get(0), data.get(1), content, logicalLocation, u);
                    out.println(result);
                    break;
                case "updFile":
                    // updFile filename newcontent
                    data = requestData.getParameters();
                    String newContent = "";
                    for (int i = 1; i < data.size(); i++) {
                        newContent += data.get(i) + (i == data.size() - 1 ? "" : " ");
                    }
                    result = administrator.updateFile(currFolder, data.get(0), newContent, u, fs);
                    out.println(result);
                    break;
                case "mkDrv":
                    // Handle "mkDrv" command
                    break;
                case "mvA":
                    // Handle "mv" command

                    // mvA nombreArchive path(user:folder/folder/archive)
                    data = requestData.getParameters();
                    folderDestiny = u.getPath(data.get(1), true);
                    result = administrator.moveArchive(data.get(0), folderDestiny, currFolder);
                    out.println(result);
                    break;

                case "mvF":
                    // Handle "mv" command

                    // mvF path(user:folder/folder/archive) path(user:folder/folder/archive)
                    data = requestData.getParameters();
                    Folder folderMove = u.getPath(data.get(0), true);
                    folderDestiny = u.getPath(data.get(1), true);
                    result = administrator.moveFolder(folderMove, folderDestiny);
                    out.println(result);
                    break;
                case "prop":
                    // Handle "prop" command
                    // prop nombreArchivo
                    data = requestData.getParameters();
                    result = administrator.seeFileProperties(currFolder, data.get(0));
                    out.println(result);
                    break;
                // command to see file content
                case "seeFile":
                    // seeFile nombreArchivo
                    data = requestData.getParameters();
                    result = administrator.seeFile(currFolder, data.get(0));
                    out.println(result);
                    break;
                case "rmFile":
                    // Handle "rm" command
                    // rmfile filename
                    data = requestData.getParameters();
                    result = administrator.deleteFile(currFolder, data.get(0),fs);
                    out.println(result);
                    break;
                case "rmDir":
                    // Handle "rmdir" command
                    // rmdir foldername
                    data = requestData.getParameters();
                    result = administrator.deleteFolder(currFolder, data.get(0));
                    out.println(result);
                    break;
                case "share":
                    data = requestData.getParameters();
                    out.println(administrator.shareFile(currFolder,data.get(0),fs.getU(data.get(1))));
                    break;
                case "vr":
                    // Handle "vr" command

                    // vr fileName
                    data = requestData.getParameters();
                    result = administrator.vrArchive(data.get(0),data.get(1), u.currentFolder);
                    out.println(result);
                    break;
                case "vvA":
                    // Handle "vv" command

                    // vvA nombreArchivo path(user:folder/folder/archive)
                    data = requestData.getParameters();
                    folderDestiny = u.getPath(data.get(1), true);
                    result = administrator.copiarVVArchive(folderDestiny, data.get(0), currFolder);
                    out.println(result);
                    break;

                case "vvF":
                    // Handle "vv" command

                    // vvF path(user:folder/folder/archive) path(user:folder/folder/archive)
                    data = requestData.getParameters();
                    folderDestiny = u.getPath(data.get(0), true);
                    Folder folderCopy = u.getPath(data.get(1), true);
                    result = administrator.copiarVVFolder(folderCopy, folderCopy);
                    out.println(result);
                    break;
                    
                case "mem":
                    out.println("Espacio inicial: "+ u.getSize());
                    out.println("Espacio utilizado: " + u.getUsedSize());
                    out.println("Espacio disponible: " + u.currentMem());
                default:
                    // Handle unknown command
                    break;
            }
            fileManager.saveJsonFileSystem(fs);
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
