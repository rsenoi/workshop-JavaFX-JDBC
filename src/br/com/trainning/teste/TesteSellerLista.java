package br.com.trainning.teste;

import java.sql.Connection;
import java.util.List;

import br.com.trainning.dao.SellerDAO;
import br.com.trainning.model.Seller;
import br.com.trainning.util.Conexao;

public class TesteSellerLista {

	public static void main(String[] args) {
		Connection con = Conexao.abrirConexao();
        SellerDAO dao = new SellerDAO(con);
        
        List <Seller> listar = dao.listarTodos();
        
        for( Seller sell : listar ){
            System.out.println(sell);
        }
 		
		Conexao.fecharConexao(con);

	}

}
