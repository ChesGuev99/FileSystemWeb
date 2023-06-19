/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//import com.fasterxml.jackson.databind.util.JSONPObject;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Girome
 */
public class FileAdministrator {
    
    public String moveArchive(String nameArchive, Folder folToMove, Folder folToDelete){
        Archive archive = folToDelete.getArchive(nameArchive);
        if(archive != null){
            if(folToDelete.deleteArchive(archive)){
                folToMove.addArchive(archive);
                archive.setFather(folToMove);
                archive.setLocationLogic(folToMove.getLocationLogic()+archive.getName());
                return "Se logro mover el archivo";
            }
        }
        return "No se logro mover el archivo";
    }
    
    public String moveFolder(Folder folder, Folder folToMove){
        if(!folToMove.verNameFolder(folder.getName())){
            if(folder.getFather().deleteFolder(folder)){
                folToMove.addFolder(folder);
                folder.setFather(folToMove);
                folder.setLocationLogic(folToMove.getLocationLogic()+folder.getName()+"/");
                return "Se logro mover el folder";
            }
        }
        return "No se logro mover el folder";
    }
    
    public String createFolder(Folder folder, String name, String directory, String createDate, String user, String locationLogic){
        if(!folder.verNameFolder(name)){
            Folder createFolder = new Folder(name, directory, folder, createDate, user, locationLogic);
            folder.addFolder(createFolder);
            return "Se creo correctamente el folder.";
        }
        return "No se logro crear correctamente el folder";
    }

    public String createFile(Folder father, String name, String extension, String fileContent, String logicalLocation) {
        if(!father.verNameArchive(name)) {
            String currentDate = getCurrentDate();
            int size = fileContent.getBytes().length;
            Archive archive = new Archive(name, size, extension, currentDate, currentDate, 666, 0, fileContent, logicalLocation);
            father.addArchive(archive);
            return "Se creó el archivo correctamente";
        }
        return "No se pudo crear el archivo";
    }
    
    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentDate = formatter.format(date);
        return currentDate;
    }

    public String updateFile(Folder folder, String fileName, String fileContent) {
        if(folder.verNameArchive(fileName)) {
            String currentDate = getCurrentDate();
            int size = fileContent.getBytes().length;
            Archive archive = folder.getArchive(fileName);
            archive.setFileContent(fileContent);
            archive.setSize(size);
            archive.setDateModify(currentDate);
            return "Se actualizó el archivo correctamente";
        }
        return "No se pudo actualizar el archivo";
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
    
    public String deleteFile(Folder folder, String fileName) {
        boolean deleted = false;
        if(folder.verNameArchive(fileName)) {
            Archive archive = folder.getArchive(fileName);
            deleted = folder.deleteArchive(archive);
        }
        return deleted ? "Se eliminó el archivo correctamente" : "No se pudo eliminar el archivo";
    }

    public String deleteFolder(Folder folder, String folderName) {
        boolean deleted = false;
        if(folder.verNameFolder(folderName)) {
            Folder folderToDelete = folder.getFolder(folderName);
            deleted = folder.deleteFolder(folderToDelete);
        }
        return deleted ? "Se eliminó el folder correctamente" : "No se pudo eliminar el folder";
    }   
    
    public String copiarVVFolder(Folder folder, Folder folToCopy){
        if(folToCopy.verNameFolder(folder.getName())){
            Folder newFolder = new Folder(folder.getName(), folToCopy.getDirectory(), 
                    folToCopy, getCurrentDate(), folder.getUser(), 
                    folToCopy.getLocationLogic()+folder.getName()+"/");
            newFolder.setFoldersIn(folder.getFoldersIn());
            newFolder.setArchiveIn(folder.getArchiveIn());
            folToCopy.addFolder(newFolder);
            return "Se copio correctamente el folder.";
        }
        return "No se puede copiar el folder.";
    }
    public String copiarVVArchive(Folder folder, String nameArchive, Folder currentFolder){
        Archive archive = currentFolder.getArchive(nameArchive);
        if(folder.verNameArchive(archive.getName())){
            Archive newArchive = new Archive(archive.getName(), archive.getSize(), 
                    archive.getExtension(), archive.getDateModify(), getCurrentDate(), 
                    666, 0, archive.getFileContent());
            folder.addArchive(newArchive);
            return "Se copio correctamente el archivo.";
        }
        return "No se puede copiar el archivo.";
    }
    public String changeDirectory(User u, String path){
        if(path.equals("..")){
            u.currentFolder = u.currentFolder.getFather();
            return u.currentFolder.name;
        }
        if(u.mainFolder.getName().equals(path)){
            u.currentFolder = u.mainFolder;
            return path;
        }
        Folder searched = u.getPath(path, false);
        //Folder searched = u.currentFolder.getFolder(foundDirectory);
        if (searched != null){
            u.currentFolder = searched;
            return path;
        }
        return "Folder not found: "+path;    
    }

    public String changeADirectory(User u, String path) {
        if(path.equals("..")){
            u.currentFolder = u.currentFolder.getFather();
        }
        if(u.mainFolder.getName().equals(path)){
            u.currentFolder = u.mainFolder;
            return path;
        }
        Folder searched = u.getPath(path, true);
        //Folder searched = u.mainFolder.getFolder(path);
        if (searched != null){
            u.currentFolder = searched;
            return path;
        }
        return "Folder not found: "+path;  
    }
        
    public String vrArchive(String fileName, Folder folder){
        if(folder.verNameArchive(fileName)) {
            Archive archive = folder.getArchive(fileName);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName+".txt"))) {
                writer.write(archive.getFileContent());
                return "Se descargo correctamente el archivo";
            } catch (IOException e) {
                return "No se descargar correctamente el archivo";
            }
        }
        return "No se logro encontrar el archivo";
    }
}
