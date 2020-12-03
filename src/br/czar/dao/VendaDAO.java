package br.czar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.czar.model.Movie;
import br.czar.model.Sell;
import br.czar.model.SellItem;
import br.czar.model.User;
import br.czar.util.Utils;

public class VendaDAO implements DAO<Sell>{

	@Override
	public void insert(Sell obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("venda ");
		sql.append("  (date, id_usuario, total) ");
		sql.append("VALUES ");
		sql.append("  ( current_timestamp, ? , ?) ");
		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			stat.setInt(1, obj.getUser().getId());
			stat.setDouble(2, obj.getTotal());
			stat.execute();
			
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next())
				obj.setId(rs.getInt("id"));
			
			for (SellItem si : obj.getMovies()) 
				if (!insertSellItem(si, conn, obj.getId())) 
					new SQLException("Erro ao inserir um item de venda");
			
			
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();

			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

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
		
		
	}
	
	private boolean insertSellItem(SellItem sellItem, Connection conn, Integer sellId) {
		boolean retorno = true;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("item_venda ");
		sql.append("  (preco, id_filme, quantity, id_venda) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?) ");
		
		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setDouble(1, sellItem.getPrice());
			stat.setDouble(2, sellItem.getMovie().getId());
			stat.setDouble(3, sellItem.getQuantity());
			stat.setDouble(4, sellId);

			stat.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			retorno  = false;
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}
		}
		return retorno;
	}

	@Override
	public void update(Sell obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Sell obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Sell> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Sell> getAll(User user) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Sell> listaVenda = new ArrayList<>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  v.id, ");
		sql.append("  v.date, ");
		sql.append("  v.id_usuario, ");
		sql.append("  v.total ");
		sql.append("FROM  ");
		sql.append("  venda v ");
		sql.append("WHERE  ");
		sql.append(" v.id_usuario = ? ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, user.getId());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Sell venda = new Sell();
				venda.setId(rs.getInt("id"));
				venda.setDate(rs.getTimestamp("date").toLocalDateTime());
				venda.setTotal(rs.getDouble("total"));
				venda.setUser(user);

				listaVenda.add(venda);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em VendaDAO.");
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

		return listaVenda;
	}

	@Override
	public Sell getOne(Sell obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		DAO<User> ud = new UserDAO();
		
		Sell sell = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  v.id, ");
		sql.append("  v.id_usuario, ");
		sql.append("  v.date, ");
		sql.append("  v.total ");
		sql.append("FROM  ");
		sql.append("  venda v ");
		sql.append("WHERE v.id = ? ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());

			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				Timestamp sellDate = rs.getTimestamp("date");
				sell = new Sell();
				sell.setId(rs.getInt("id"));
				sell.setUser(ud.getOne(new User(rs.getInt("id_usuario"))));
				sell.setDate(sellDate==null?null:sellDate.toLocalDateTime());
				sell.setTotal(rs.getDouble("total"));
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

		return sell;
	}

	@Override
	public List<Sell> search(String q, String f) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
