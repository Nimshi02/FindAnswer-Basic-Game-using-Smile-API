/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uob.server;

/**
 *
 * @author acer
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *This is for initializing user details from the RegisterUI
 * @author Nimshi
 * @version 1.0.0
 */
public class Player {
    private String username;
    private String email;
    private String password;
    private int score;
    private String time;
    
    /*
    *Constructor for initialize the user
    */
    public Player(String username,String email,String password){
    this.username=username;
    this.email=email;
    this.password=password;
    
}
    public Player()
    {
        
    }

    Player(String email, int score, String time) {
        this.email=email;
        this.score=score;
        this.time=time;//To change body of generated methods, choose Tools | Templates.
    }

    /*
    Get the username of the user
    */
    public String getUsername() {
        return username;
    }

    /*
    Get the Email of the user
    */
    public String getEmail() {
        return email;
    }

    /*
    Get the password of the user
    */
    public String getPassword() {
        return password;
    }
    /*
    Create the connection and save the data to the database
    */

    public int getScore() {
        return score;
    }

    public String getTime() {
        return time;
    }
}
