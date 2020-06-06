package br.com.trainning.teste;

import java.sql.Connection;

import br.com.trainning.dao.SellerDAO;
import br.com.trainning.model.Seller;
import br.com.trainning.util.Conexao;


public class TestarSellerAlteracao {

	public static void main(String[] args) {
		
        Connection con = Conexao.abrirConexao();
        
        Seller sell = new Seller();
        SellerDAO dao = new SellerDAO(con);
       
        sell = dao.listarUm(1);
        sell.setName("Teste7");
 
        dao.alterar(sell);

        Conexao.fecharConexao(con);
	}

}
