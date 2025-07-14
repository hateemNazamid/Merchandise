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
public class RegisterBean implements Serializable{
    private String name;
    private String email;    
    private String username;   
    private String password;    
    private String address;
    private int id;
    private String type;
    
    public RegisterBean(){
        username = "";
        password = "";        
        email = "";
        name = "";        
        address = "";
        id = 0;
        type = "";
    }
    
    public RegisterBean(String name,String email, String username, String password,String address, int id){
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;       
        this.address = address;
        this.id = id;
    }
    
    public String getEmail(){
        return email;
    }
    public String getName(){
        return name;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getAddress(){
        return address;
    }
    public int getId(){
        return id;
    }
    
    public String getType(){
        return type;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public void setData(String name,String email, String username, String password,String address, int id){
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.id = id;
    }
    
    public void setType(String type){
        this.type = type;
    }
}
