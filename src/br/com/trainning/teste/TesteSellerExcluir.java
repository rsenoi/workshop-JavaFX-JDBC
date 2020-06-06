package br.com.trainning.teste;

import java.sql.Connection;
import java.util.Scanner;

import br.com.trainning.dao.SellerDAO;
import br.com.trainning.util.Conexao;

public class TesteSellerExcluir {

	public static void main(String[] args) {
        Connection con = Conexao.abrirConexao();
        SellerDAO dao = new SellerDAO(con);
      
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Entre com o Id para exclusao: ");
        int id = sc.nextInt();
        dao.excluir(id);
        System.out.println("Fim da exclusao... ");

        sc.close();

	}

}
