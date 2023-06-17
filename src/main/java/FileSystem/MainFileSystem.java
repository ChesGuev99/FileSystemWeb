/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileSystem;

import java.util.ArrayList;

/** 
 *
 * @author DiegoAlvarez
 */
public class MainFileSystem {
    public ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }
    public void startFileSystem(){
        for(int i = 0; i<users.size();i++){
            users.get(i).startFileSystem();
        }
    }
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    
    public MainFileSystem(){
        users = new ArrayList();
    }
    public User getUser(String name){
        for(int i=0;i<users.size();i++){
            if(users.get(i).name.equals(name))
                return users.get(i);
        }
        return null;
    }
}
