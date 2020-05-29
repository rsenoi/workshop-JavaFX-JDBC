/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trainning.teste;

import br.com.trainning.util.Conexao;
import java.sql.Connection;

/**
 *
 * @author Trainning
 */
public class TestarConexao {
    
    public static void main(String[] args) {
        Connection con = Conexao.abrirConexao();
        
        
        
        Conexao.fecharConexao(con);
        
    }
    
}
