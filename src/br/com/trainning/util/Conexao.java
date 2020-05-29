/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trainning.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Trainning
 */
public class Conexao {
    
    public static Connection abrirConexao(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            String url = "";
            url += "jdbc:mysql://localhost:3306/coursejdbc?";
            url += "user=root&password=";
            con = DriverManager.getConnection(url);
            System.out.println("Conexao Aberta");
            
                    
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }catch(ClassNotFoundException e){
           System.err.println(e.getMessage());
        }catch(Exception e){
           System.err.println(e.getMessage());
        }
 
        
        return con;
    }
            
    public static void fecharConexao(Connection con){
        try{
            con.close();
            System.out.println("Conexao Fechada");

        }catch(SQLException e){
            System.err.println(e.getMessage());
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
