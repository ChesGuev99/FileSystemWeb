/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Girome
 */
public class Main {
    static public void main(String args[]) throws JsonProcessingException, IOException  {
        FileAdministrator administrator = new FileAdministrator();
        Folder folder = new Folder("user", "c:", "24/01/2002", "Girome", "");
        Archive archive = new Archive("Archivo", 0, ".txt", "06/06/2002 17:40", "06/06/2002 17:40", 666, 0, "Hola qué tal");
        administrator.createFolder(folder, "raiz", "c:", "24/01/2002", "Diego", "");
        folder.addArchive(archive);

        boolean created = administrator.createFile(folder, "File by create", ".txt", "Hola mundo");
        System.out.println("Created = "+created);
        
        /*System.out.println("Folder = "+folder.getName()+" user = "+folder.getUser()+
                " | Folder in = "+folder.getFolder("raiz").getName()+" user = "+folder.getFolder("raiz").getUser()+
                " | Archive in = "+folder.getArchive("Archivo").getName()+" extension = "+folder.getArchive("Archivo").getExtension()+
                " datemodify = "+folder.getArchive("Archivo").getDateModify());*/
        
        System.out.println(administrator.listarDirectorios(folder));

        boolean updated = administrator.updateFile(folder, "Archivo", "Hola buenos días");
        System.out.println("Updated = "+updated);

        System.out.println(administrator.listarDirectorios(folder));

        String properties = administrator.seeFileProperties(folder, "Archivo");
        System.out.println("Properties = "+properties);

        String fileContent = administrator.seeFile(folder, "Archivo");
        System.out.println("File content = "+fileContent);

        
        JsonAdapter file = new JsonAdapter();            //Clase para guardar y cargar los folders
        file.saveJsonFolder(folder);                     //Guardar el folder raiz con todos los folders y archivos
        
        
        Folder saveFolder = file.loadJsonFolder();        //Cargar el folder raiz con todos los folders
        
        
        System.out.println("Folder = "+saveFolder.getName()+" user = "+saveFolder.getUser()+
                " | Folder in = "+saveFolder.getFolder("raiz2").getName()+" user = "+saveFolder.getFolder("raiz2").getUser()+
                " | Archive in = "+saveFolder.getArchive("Archivo").getName()+" extension = "+saveFolder.getArchive("Archivo").getExtension()+
                " datemodify = "+saveFolder.getArchive("Archivo").getDateModify());
    }
}
