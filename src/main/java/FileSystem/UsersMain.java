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
public class UsersMain {
    static public void main(String args[]) throws JsonProcessingException, IOException  {
        FileAdministrator administrator = new FileAdministrator();
        Folder folder = new Folder("user", "c:", "24/01/2002", "Girome", "");
        Archive archive = new Archive("Archivo", 0, ".txt", "06/06/2002 17:40", "06/06/2002 17:40", 666, 0, "Hola que tal");
        administrator.createFolder(folder, "raiz", "c:", "24/01/2002", "Diego", "");
        folder.addArchive(archive);
        
        MainFileSystem fs = new MainFileSystem();
        User u = new User("Girome", 9999,folder);
        fs.users.add(u);
        String created = administrator.createFile(folder, "File by create", ".txt", "Hola mundo");
        System.out.println("Created = "+created);
        
        /*System.out.println("Folder = "+folder.getName()+" user = "+folder.getUser()+
                " | Folder in = "+folder.getFolder("raiz").getName()+" user = "+folder.getFolder("raiz").getUser()+
                " | Archive in = "+folder.getArchive("Archivo").getName()+" extension = "+folder.getArchive("Archivo").getExtension()+
                " datemodify = "+folder.getArchive("Archivo").getDateModify());*/
        
        System.out.println(administrator.listarDirectorios(folder));

        boolean updated = administrator.updateFile(folder, "Archivo", "Hola buenos dias");
        System.out.println("Updated = "+updated);

        System.out.println(administrator.listarDirectorios(folder));

        String properties = administrator.seeFileProperties(folder, "Archivo");
        System.out.println("Properties = "+properties);

        String fileContent = administrator.seeFile(folder, "Archivo");
        System.out.println("File content = "+fileContent);
        
        JsonAdapter file = new JsonAdapter();            //Clase para guardar y cargar los folders
        //file.saveJsonFileSystem(fs);
        MainFileSystem fs2 = file.loadJsonFileSystem();
        fs2.startFileSystem();
        
    }
}
