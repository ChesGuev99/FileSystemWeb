/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletPackage.logic;

import java.util.List;

/**
 *
 * @author anagu
 */
public class RequestData {
    String user;
    String command;
    List<String> parameters;

    public String getUser() {
        return user;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getParameters() {
        return parameters;
    }
    
}
