package br.com.trainning.teste;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import br.com.trainning.dao.DepartmentDAO;
import br.com.trainning.model.Department;
import br.com.trainning.util.Conexao;

public class TestarDepartmentLista {

	public static void main(String[] args) {

		Connection con = Conexao.abrirConexao();
        DepartmentDAO dao = new DepartmentDAO(con);
        
        List <Department> listar = dao.listarTodos();
        
        for( Department depto : listar ){
            System.out.println("id "+depto.getId()+" Name "+depto.getName());
        }
 		
		Conexao.fecharConexao(con);

	}

}
