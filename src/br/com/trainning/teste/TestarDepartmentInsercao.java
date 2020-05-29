package br.com.trainning.teste;

import java.sql.Connection;
import br.com.trainning.dao.DepartmentDAO;
import br.com.trainning.model.Department;
import br.com.trainning.util.Conexao;

public class TestarDepartmentInsercao {

	public static void main(String[] args) {
        
        Connection con = Conexao.abrirConexao();
        
        Department dep = new Department();
        DepartmentDAO dao = new DepartmentDAO(con);
        
        dep.setId(5);
        dep.setName("55555");
        
        dao.inserir(dep);

        Conexao.fecharConexao(con);

 
	}

}
