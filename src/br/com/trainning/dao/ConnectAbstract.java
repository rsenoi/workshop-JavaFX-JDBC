/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trainning.dao;

import java.sql.Connection;

/**
 *
 * @author Trainning
 */
public abstract class ConnectAbstract {
    
    private Connection con;

    public ConnectAbstract(Connection con) {
        this.con = con;
    }
    
    

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    
    
    
    
    
}
