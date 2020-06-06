package br.com.trainning.teste;

import java.sql.Connection;
import java.util.Date;

import br.com.trainning.dao.SellerDAO;
import br.com.trainning.model.Department;
import br.com.trainning.model.Seller;
import br.com.trainning.util.Conexao;

public class TesteSellerInsercao {

	public static void main(String[] args) {
        Connection con = Conexao.abrirConexao();

        Department dep = new Department();
        Seller sell = new Seller();
        SellerDAO dao = new SellerDAO(con);
       
        sell.setName("Teste7");
        sell.setEmail("EmailTeste7");
        sell.setBirthdate(new Date());
        sell.setBaseSalary(1000.00);
        dep.setId(2);

        sell.setDepartment(dep);

        dao.inserir(sell);

        Conexao.fecharConexao(con);

	}

}
