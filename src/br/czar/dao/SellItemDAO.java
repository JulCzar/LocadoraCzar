package br.czar.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.czar.model.Privilege;
import br.czar.model.Sell;
import br.czar.model.SellItem;
import br.czar.model.User;
import br.czar.util.Utils;

public class SellItemDAO implements DAO<SellItem> {

	@Override
	public void insert(SellItem obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SellItem obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SellItem obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SellItem> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<SellItem> getAll(Sell sell) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<User> userList = new ArrayList<>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  u.id, ");
		sql.append("  u.id_venda, ");
		sql.append("  u.id_filme, ");
		sql.append("  u.preco, ");
		sql.append("  u.quantity ");
		sql.append("FROM  ");
		sql.append("  usuario u ");
		sql.append("ORDER BY u.name ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				User user = new User();
				Date birthdate = rs.getDate("birthdate");
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setPrivilege(Privilege.valueOf(rs.getInt("privilege")));
				user.setBirthdate(birthdate == null ? null : birthdate.toLocalDate());
				user.setCpf(rs.getString("cpf"));
				user.setPassword(rs.getString("password"));

				userList.add(user);
			}

		} catch (SQLException e) {
			Utils.addErrorMessage("Não foi possivel buscar os dados do usuario.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em UsuarioDAO.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

		return userList;
	}

	@Override
	public SellItem getOne(SellItem obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SellItem> search(String q, String f) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
