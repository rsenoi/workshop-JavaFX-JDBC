package br.com.trainning.teste;

import java.sql.Connection;
import java.util.List;

import br.com.trainning.dao.DepartmentDAO;
import br.com.trainning.model.Department;
import br.com.trainning.util.Conexao;

public class TestarDepartmentUm {

	public static void main(String[] args) {
		Connection con = Conexao.abrirConexao();
        DepartmentDAO dao = new DepartmentDAO(con);
        
		Department department = new Department();
		
		department = dao.listarUm(5);
        
        System.out.println(department);
 		
		Conexao.fecharConexao(con);

	}
}

