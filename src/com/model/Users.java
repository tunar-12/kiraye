/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author Turan
 */
public class Users {
   private Integer id;
   private String user;

    public Users(Integer id, String user) {
        this.id = id;
        this.user = user;
    }
    public Users(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", user=" + user + '}';
    }

   
   
   
   
}
