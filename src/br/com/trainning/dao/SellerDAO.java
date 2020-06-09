package br.com.trainning.dao;

import br.com.trainning.model.Department;
import br.com.trainning.model.Seller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class SellerDAO extends ConnectAbstract implements InterfaceSellerDAO {

	public SellerDAO(Connection con) {
		super(con);
	}

	private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller Seller = new Seller();

		Seller.setId(rs.getInt("Id"));
		Seller.setName(rs.getString("Name"));
		Seller.setEmail(rs.getString("Email"));
		Seller.setBirthdate(new java.util.Date(rs.getTimestamp("BirthDate").getTime()));
		Seller.setBaseSalary(rs.getDouble("BaseSalary"));
		Seller.setDepartment(dep);
		
		return Seller;
	}


	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		
		return dep;
	}

	public void saveOrUpdate(Seller obj) {
		if( obj.getId() <= 0) {
			inserir(obj);
		}
		else {
			alterar(obj);
		}
	}

	
	@Override
	public String inserir(Seller Seller) {

		String sql = "insert into coursejdbc.Seller (Name, Email, Birthdate, BaseSalary, DepartmentId) " +
		             "values ( ?, ?, ?, ?, ?)";

		try {
			PreparedStatement sel = getCon().prepareStatement(sql);
			// Posicao da ? ficar atento a sequencia
			
			sel.setString(1, Seller.getName());
			sel.setString(2, Seller.getEmail());
			sel.setDate(3, new java.sql.Date(Seller.getBirthdate().getTime()));
			sel.setDouble(4, Seller.getBaseSalary());
			sel.setInt(5, Seller.getDepartment().getId());
			
			int row = sel.executeUpdate();

			return (row > 0 ? "Inserido com Sucesso" : "Erro ao Inserir");

		} catch (SQLException e) {
			System.err.println(SellerDAO.class.getName());
			return e.getMessage();

		}

	}

	@Override
	public String alterar(Seller Seller) {

		String sql = "UPDATE seller " + 
				     "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " + 
				     "WHERE Id = ? ";

		try {
			PreparedStatement sel = getCon().prepareStatement(sql);
			
			sel.setString(1, Seller.getName());
			sel.setString(2, Seller.getEmail());
			sel.setDate(3, new java.sql.Date(Seller.getBirthdate().getTime()));
			sel.setDouble(4, Seller.getBaseSalary());
			sel.setInt(5, Seller.getDepartment().getId());
			sel.setInt(6, Seller.getId());

			int rows = sel.executeUpdate();
			
			System.out.println("Linha alteradas: " + rows);
			// ternario
			return ( rows > 0 ? "Alterado com Sucesso" : "Erro ao Alterar");

		} catch (SQLException e) {
			System.err.println(SellerDAO.class.getName());
			return e.getMessage();

		}

	}

	@Override
	public String excluir(Integer Id) {

		String sql = "delete from coursejdbc.Seller where id=?";

		try {
			PreparedStatement ps = getCon().prepareStatement(sql);
			// Posicao da ? lembrar da sequencia do sql
			ps.setInt(1, Id);

			// ternario
			return (ps.executeUpdate() > 0 ? "Excluido com Sucesso" : "Erro ao Excluir");

		} catch (SQLException e) {
			System.err.println(SellerDAO.class.getName());
			return e.getMessage();

		}

	}
	

	@Override
	public List<Seller> listarTodos() {

		String sql = "SELECT seller.*,department.Name as DepName " + 
				     "FROM seller INNER JOIN department " + 
				     "ON seller.DepartmentId = department.Id " +
				     "ORDER BY Name ";
				     
		List<Seller> listaSeller = new ArrayList();

		try {
			PreparedStatement ps = getCon().prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			Map<Integer, Department> map = new HashMap<>();

			if (rs != null) {
				while (rs.next()) {

					Department dep = map.get(rs.getInt("DepartmentId"));
					
					if( dep==null) {
						dep = instantiateDepartment(rs);
						map.put(rs.getInt("DepartmentId"),dep);
					}

					Seller Seller = instatiateSeller(rs,dep);

					listaSeller.add(Seller);
				}
				return (listaSeller);

			} else {
				return (null);

			}

		} catch (SQLException e) {
			System.err.println(SellerDAO.class.getName());
			return null;

		}

	}

	@Override
	public Seller listarUm(Integer Id) {

		String sql = "SELECT seller.*,department.Name as DepName " + 
				     "FROM seller INNER JOIN department " + 
				     "ON seller.DepartmentId = department.Id " +
				     "WHERE seller.Id = ?";
				     
		try {
			PreparedStatement ps = getCon().prepareStatement(sql);

			ps.setInt(1,Id);

			ResultSet rs = ps.executeQuery();

			if (rs != null) {
				if(rs.next() ) {
					Department dep = instantiateDepartment(rs);
					Seller Seller = instatiateSeller(rs,dep);
					
					return (Seller);
				}
			}
			return (null);


		} catch (SQLException e) {
			System.err.println(SellerDAO.class.getName());
			return null;

		}

	}
	

	@Override
	public List<Seller> listarUmDepto(Department depto) {

		String sql = "SELECT seller.*,department.Name as DepName " +
					 "FROM seller INNER JOIN department " +
					 "ON seller.DepartmentId = department.Id " +
					 "WHERE DepartmentId = ? " +
					 "ORDER BY Name ";

		List<Seller> listaSeller = new ArrayList();

		try {
			PreparedStatement ps = getCon().prepareStatement(sql);
			
			ps.setInt(1,depto.getId());

			ResultSet rs = ps.executeQuery();
			
			Map<Integer, Department> map = new HashMap<>();
			

			if (rs != null) {
				while(rs.next() ) {
					Department dep = map.get(rs.getInt("DepartmentId"));
					
					if( dep==null) {
						dep = instantiateDepartment(rs);
						map.put(rs.getInt("DepartmentId"),dep);
					}

					Seller Seller = instatiateSeller(rs,dep);
			
					listaSeller.add(Seller);
					
				}
				return (listaSeller);

			} else {
				return (null);
			}


		} catch (SQLException e) {
			System.err.println(SellerDAO.class.getName());
			return null;

		}

	}


	public boolean login(int id, String name) {
		boolean autenticado = false;

		String sql = "select * from coursejdbc.Seller where id=?";

		try {

			PreparedStatement ps = getCon().prepareStatement(sql);

			ps.setInt(1, id);
			ps.setString(2, name);

			ResultSet rs = ps.executeQuery();

			return rs.next() ? autenticado = true : autenticado;

		} catch (SQLException e) {
			System.err.println(SellerDAO.class.getName());
			return false;

		}

	}

}
