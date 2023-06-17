/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

import java.util.ArrayList;

//import com.fasterxml.jackson.databind.util.JSONPObject;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Girome
 */
public class FileAdministrator {
    
    public boolean moveArchive(Archive archive, Folder folToMove, Folder folToDelete, String locationLogic){
        if(!folToMove.verNameArchive(archive.getName())){
            if(folToDelete.deleteArchive(archive)){
                folToMove.addArchive(archive);
                archive.setFather(folToMove);
                archive.setLocationLogic(locationLogic);
                return true;
            }
        }
        return false;
    }
    
    public boolean moveFolder(Folder folder, Folder folToMove, Folder folToDelete, String locationLogic){
        if(!folToMove.verNameFolder(folder.getName())){
            if(folToDelete.deleteFolder(folder)){
                folToMove.addFolder(folder);
                folder.setFather(folToMove);
                folder.setLocationLogic(locationLogic);
                return true;
            }
        }
        return false;
    }
    
    public boolean createFolder(Folder folder, String name, String directory, String createDate, String user, String locationLogic){
        if(!folder.verNameFolder(name)){
            Folder createFolder = new Folder(name, directory, folder, createDate, user, locationLogic);
            folder.addFolder(createFolder);
            return true;
        }
        return false;
    }

    public boolean createFile(Folder father, String name, String extension, String fileContent) {
        if(!father.verNameArchive(name)) {
            String currentDate = getCurrentDate();
            Archive archive = new Archive(name, 0, extension, currentDate, currentDate, 666, 0, fileContent);
            father.addArchive(archive);
            return true;
        }
        return false;
    }
    
    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentDate = formatter.format(date);
        return currentDate;
    }

    public boolean updateFile(Folder folder, String fileName, String fileContent) {
        if(folder.verNameArchive(fileName)) {
            String currentDate = getCurrentDate();
            Archive archive = folder.getArchive(fileName);
            archive.setFileContent(fileContent);
            archive.setDateModify(currentDate);
            return true;
        }
        return false;
    }

    public String seeFileProperties(Folder folder, String fileName) {
        String properties = "";
        if(folder.verNameArchive(fileName)) {
            Archive archive = folder.getArchive(fileName);
            properties += "Nombre: " + archive.getName() + "\n" 
                        + "Extensión: " + archive.getExtension() + "\n" 
                        + "Fecha de creación: " + archive.getDateCreate() + "\n" 
                        + "Fecha de modificación: " + archive.getDateModify() + "\n" 
                        + "Tamaño: " + archive.getSize() + "\n";
        }
        return properties;
    }

    /* 
    public JSONObject getFilePropertiesJson (Folder folder, String fileName) {
        JSONObject json = new JSONObject();
        if(folder.verNameArchive(fileName)){
            json.put("Nombre", folder.getArchive(fileName).getName());
            json.put("Extensión", folder.getArchive(fileName).getExtension());
            json.put("Fecha de creación", folder.getArchive(fileName).getDateCreate());
            json.put("Fecha de modificación", folder.getArchive(fileName).getDateModify());
            json.put("Tamaño", folder.getArchive(fileName).getSize());
        } else {
            json.put("Error", "Archivo no encontrado");
        }
        return json;
    } */

    public String seeFile(Folder folder, String fileName) {
        String fileContent = "";
        if(folder.verNameArchive(fileName)) {
            Archive archive = folder.getArchive(fileName);
            fileContent = archive.getFileContent();
        }
        return fileContent;
    }
    
    public String listarDirectorios(Folder folder){
        String lista = "";
        ArrayList<Folder> folders = folder.getFoldersIn();
        for(int i = 0; i < folders.size(); i++){
            lista += "F: " + folders.get(i).getName()+"\n";
        }
        
        ArrayList<Archive> archives = folder.getArchiveIn();
        for(int i = 0; i < archives.size(); i++){
            lista += "A: " + archives.get(i).getName() + " (" + archives.get(i).getFileContent() + ")" + "\n";
        }
        
        return lista;
    }
    
    public boolean deleteFile(Folder folder, String fileName) {
        boolean deleted = false;
        if(folder.verNameArchive(fileName)) {
            Archive archive = folder.getArchive(fileName);
            deleted = folder.deleteArchive(archive);
        }
        return deleted;
    }

    public boolean deleteFolder(Folder folder, String folderName) {
        boolean deleted = false;
        if(folder.verNameFolder(folderName)) {
            Folder folderToDelete = folder.getFolder(folderName);
            deleted = folder.deleteFolder(folderToDelete);
        }
        return deleted;
    }   
    
        
}
