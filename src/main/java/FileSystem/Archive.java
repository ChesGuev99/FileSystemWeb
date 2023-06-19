/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

/**
 *
 * @author Girome
 */
public class Archive {
    private String name;              //Nombre archivo
    private int size;                 //Tamanno del archivo
    private Folder father;            //Carpeta que almacena el archivo
    private String extension;         //Extension del archivo
    private String dateCreate;        //Fecha de creacion del archivo
    private String dateModify;        //Fecha de la ultima modificacion del archivo
    private int identifier;           //Numero de identificacion del archivo
    private int protection;           //numero que identifica la proteccion 666 reading, writing, executing
    private String locationLogic;     //Ubicacion logica del archivo
    private String locationphysical;  //Ubicacion fisica del archivo
    private String content;

    public Archive(String name, int size, String extension, String dateCreate, String dateModify, int protection, int identifier, String content) {
        this.name = name;
        this.size = size;
        this.extension = extension;
        this.dateCreate = dateCreate;
        this.dateModify = dateModify;
        this.protection = protection;
        this.identifier = identifier;
        this.content = content;
    }

    public Archive(String name, int size, String extension, String dateCreate, String dateModify, int protection, int identifier, String content, String logicalLocation) {
        this.name = name;
        this.size = size;
        this.extension = extension;
        this.dateCreate = dateCreate;
        this.dateModify = dateModify;
        this.protection = protection;
        this.identifier = identifier;
        this.content = content;
        this.locationLogic = logicalLocation;
    }

    public Archive() {
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDateModify() {
        return dateModify;
    }

    public void setDateModify(String dateModify) {
        this.dateModify = dateModify;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Folder getFather() {
        return father;
    }

    public void setFather(Folder father) {
        this.father = father;
    }

    public int getIdentifier() {
        return identifier;
    }

    public int getProtection() {
        return protection;
    }

    public String getLocationLogic() {
        return locationLogic;
    }

    public void setLocationLogic(String locationLogic) {
        this.locationLogic = locationLogic;
    }

    public String getLocationphysical() {
        return locationphysical;
    }

    public void setLocationphysical(String locationphysical) {
        this.locationphysical = locationphysical;
    }

    public void setFileContent(String content) {
        this.content = content;
    }

    public String getFileContent() {
        return content;
    }
    
}
