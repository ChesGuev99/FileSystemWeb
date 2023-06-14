/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Girome
 */
public class JsonAddapter {
    private String json = "";
    private ObjectMapper mapper = new ObjectMapper();
    private String relativePath = "src/main/java/FileSystem/folders.json";
    private String filePath = System.getProperty("user.dir") + File.separator + relativePath;
        
    public void saveJsonFolder(Folder folder){
        try {
            json = mapper.writeValueAsString(folder);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Folder loadJsonFolder(){
        File file = new File(filePath);

        Folder object = null;
        try {
            object = mapper.readValue(file, Folder.class);
            System.out.println("Name= "+object.getName()+"   Date= "+object.getCreateDate()+"   User= "+object.getUser());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }
}
