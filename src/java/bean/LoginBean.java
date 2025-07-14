/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class LoginBean implements Serializable{
    private int id;
    private String username;
    private String password;   
    private String email;
    private String address;
    private String role;
    private String name;
    
    public LoginBean(){
        id = 0;
        username = "";
        password = "";
        email = "";
        address = "";
        role = "";
        name = "";
    }
    
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public int getID(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String getAddress(){
        return address;
    }
    public String getRole(){
        return role;
    }
    public String getName(){
        return name;
    }
    
    public void setUsername(String user){
        this.username = user;
    }
    
    public void setPassword( String pass){
        this.password = pass;        
    }
    
    public void setID(int id){
        this.id = id;
    }
    
    public void setRole(String role){
        this.role = role;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public void setName(String name){
        this.name = name;
    }
}
