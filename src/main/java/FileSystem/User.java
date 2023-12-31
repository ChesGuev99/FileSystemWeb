/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileSystem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author DiegoAlvarez
 */
public class User {
    public String name;
    public Folder mainFolder;
    public Folder currentFolder;
    public int size;
    public int usedSize;
    public Folder sharedFolder;
    public User() {}
    public User(String name, int size){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentDate = formatter.format(date);
        this.name = name;
        this.size = size;
        mainFolder = new Folder(name,name+"/",currentDate,name,"");
        sharedFolder = new Folder("shared",mainFolder.getDirectory()+"shared/",currentDate,name,"");
        mainFolder.foldersIn.add(sharedFolder);
        currentFolder = mainFolder;
    }
    public User(String name, int size, Folder folder){
        this.name = name;
        this.size = size;
        mainFolder = folder;
        currentFolder = mainFolder;
    }
    public void startFileSystem(){
        currentFolder = mainFolder;
        mainFolder.setChildrenFather();
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setMainFolder(Folder mainFolder) {
        this.mainFolder = mainFolder;
    }

    public void setCurrentFolder(Folder currentFolder) {
        this.currentFolder = currentFolder;
    }

    public int getUsedSize() {
        return usedSize;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Folder getMainFolder() {
        return mainFolder;
    }

    public Folder getCurrentFolder() {
        return currentFolder;
    }

    public int getSize() {
        return size;
    }
    public Folder getPath(String path, boolean isAbs){
        String[] folders = path.split("/");
        Folder auxCurrFolder;
        if(isAbs){
            auxCurrFolder = mainFolder;
            String[] foldersWithoutFirstValue = new String[folders.length - 1];
            System.arraycopy(folders, 1, foldersWithoutFirstValue, 0, foldersWithoutFirstValue.length);
            folders = foldersWithoutFirstValue;
        }
        else 
            auxCurrFolder= currentFolder;
        boolean found = false;
        for (String folder : folders) {
            for (int j = 0; j < auxCurrFolder.foldersIn.size(); j++) {
                if (auxCurrFolder.foldersIn.get(j).getName().equals(folder)) {
                    auxCurrFolder = auxCurrFolder.foldersIn.get(j);
                    found = true;
                    break;
                } else {
                }
            }
            if(found)
                found = false;
            else
                return null;
        }
        return auxCurrFolder;
    }
        
    public int currentMem(){
        return (this.size - this.usedSize);
    }
    
    public boolean calculateSize(int added, int removed) {
        int auxSize = this.usedSize + added - removed;
        if (auxSize <= this.size) {
            usedSize = auxSize;
            return true;
        }
        return false;
    }
}
