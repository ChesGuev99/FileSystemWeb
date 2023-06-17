/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;

/**
 *
 * @author Girome
 */
public class Folder {
    public String name;
    public ArrayList<Folder> foldersIn;   //Arreglo para folders que estan en el folder
    public ArrayList<Archive> archiveIn; //Arreglo para los archivos que estan en el folder
    private String directory;             //Guarda la url desde el directorio raiz
    @JsonIgnore
    private Folder father;
    private String createDate;            //Fecha de creacion
    private String user;                  //Usuario que lo crea
    private String locationLogic;     //Ubicacion logica del folder

    public Folder(String name, String directory, String createDate, String user, String locationLogic) {
        this.name = name;
        this.directory = directory;
        this.createDate = createDate;
        this.user = user;
        this.archiveIn = new ArrayList<>();
        this.foldersIn = new ArrayList<>();
        this.locationLogic = locationLogic;
    }
    public Folder(String name, String directory, Folder father, String createDate, String user, String locationLogic) {
        this.name = name;
        this.directory = directory;
        this.father = father;
        this.createDate = createDate;
        this.user = user;
        this.archiveIn = new ArrayList<>();
        this.foldersIn = new ArrayList<>();
        this.locationLogic = locationLogic;
    }

    public Folder() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Folder> getFoldersIn() {
        return foldersIn;
    }

    public void setFoldersIn(ArrayList<Folder> foldersIn) {
        this.foldersIn = foldersIn;
    }

    public ArrayList<Archive> getArchiveIn() {
        return archiveIn;
    }

    public void setArchiveIn(ArrayList<Archive> archiveIn) {
        this.archiveIn = archiveIn;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public Folder getFather() {
        return father;
    }

    public void setFather(Folder father) {
        this.father = father;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUser() {
        return user;
    }

    public String getLocationLogic() {
        return locationLogic;
    }

    public void setLocationLogic(String locationLogic) {
        this.locationLogic = locationLogic;
    }
    
    public void addFolder(Folder folder){
        foldersIn.add(folder);
    }
    
    public Folder getFolder(String name){
        if(!foldersIn.isEmpty()){
            for(int i = 0; i < foldersIn.size(); i++){
                if(foldersIn.get(i).getName().equals(name)){
                    return foldersIn.get(i);
                }
            }
        }
        return null;
    }
    
    public void addArchive(Archive archive){
        archiveIn.add(archive);
    }
    
    public Archive getArchive(String name){
        if(!archiveIn.isEmpty()){
            for(int i = 0; i < archiveIn.size(); i++){
                if(archiveIn.get(i).getName().equals(name)){
                    return archiveIn.get(i);
                }
            }
        }
        return null;
    }
    
    public boolean deleteArchive(Archive archive){
        return archiveIn.remove(archive);
    }
    
    public boolean deleteFolder(Folder folder){
        return foldersIn.remove(folder);
    }
    
    public boolean verNameArchive(String archive){
        for(int i = 0; i < archiveIn.size(); i++){
            if(archiveIn.get(i).getName().equals(archive)){
                return true;
            }
        }
        return false;
    }
    
    public boolean verNameFolder(String folComp){
        for(int i = 0; i < foldersIn.size(); i++){
            if(foldersIn.get(i).getName().equals(folComp)){
                return true;
            }
        }
        return false;
    }
    public void setChildrenFather(){
        if (foldersIn != null){
            for(int i = 0; i < foldersIn.size(); i++){
                if (foldersIn.get(i).getFather() == null){
                    foldersIn.get(i).setFather(this);
                    foldersIn.get(i).setChildrenFather();
                }
            }
        }
    }
}
