/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trainning.teste;

import br.com.trainning.dao.DepartmentDAO;
import br.com.trainning.model.Department;
import br.com.trainning.util.Conexao;
import java.sql.Connection;

/**
 *
 * @author Trainning
 */
public class TestarDepartmentExcluir {
    
    public static void main(String[] args) {
        
        Connection con = Conexao.abrirConexao();
        
        Department dep = new Department(5,null);
        DepartmentDAO dao = new DepartmentDAO(con);
        
        dao.excluir(dep);

        Conexao.fecharConexao(con);


        
    }
}
