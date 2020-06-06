package br.com.trainning.teste;

import java.sql.Connection;
import java.util.List;

import br.com.trainning.dao.SellerDAO;
import br.com.trainning.model.Seller;
import br.com.trainning.util.Conexao;

public class TestarSellerUm {

	public static void main(String[] args) {
		Connection con = Conexao.abrirConexao();
        
		Seller Seller = new Seller();
        SellerDAO dao = new SellerDAO(con);

		Seller = dao.listarUm(3);
      
        System.out.println(Seller);
 		
		Conexao.fecharConexao(con);

	}

}
