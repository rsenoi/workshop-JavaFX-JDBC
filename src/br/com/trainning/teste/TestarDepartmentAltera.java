package br.com.trainning.teste;

import java.sql.Connection;

import br.com.trainning.dao.DepartmentDAO;
import br.com.trainning.model.Department;
import br.com.trainning.util.Conexao;

public class TestarDepartmentAltera {

	public static void main(String[] args) {
		
		Connection con = Conexao.abrirConexao();
		
        DepartmentDAO dao = new DepartmentDAO(con);
        
		Department department = new Department(5, "6666");
		
		dao.alterar(department);
        
        System.out.println(department);
 		
		Conexao.fecharConexao(con);

	}

}
