/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileSystem;

/**
 *
 * @author DiegoAlvarez
 */
public class User {
    String name;
    Folder mainFolder;
    Folder currentFolder;
    int size;
    
    public User() {}
    public User(String name, int size){
        this.name = name;
        this.size = size;
        mainFolder = new Folder();
        currentFolder = mainFolder;
    }
    public void startFileSystem(){
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
    public User(String name, int size, Folder folder){
        this.name = name;
        this.size = size;
        mainFolder = folder;
        currentFolder = mainFolder;
    }
}
