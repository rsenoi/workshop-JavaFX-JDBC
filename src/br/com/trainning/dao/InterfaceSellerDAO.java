package br.com.trainning.dao;

import java.util.List;

import br.com.trainning.model.Department;
import br.com.trainning.model.Seller;


public interface InterfaceSellerDAO {

    public String inserir(Seller Seller);
    
    public String alterar(Seller Seller);
    
    public String excluir(Integer Id);
    
    public List<Seller> listarTodos();
    
    public Seller listarUm(Integer Id);

	List<Seller> listarUmDepto(Department depto);

	public void saveOrUpdate(Seller obj);
}
