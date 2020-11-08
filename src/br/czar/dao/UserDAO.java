package br.czar.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.czar.model.Privilege;
import br.czar.model.User;
import br.czar.util.Utils;

public class UserDAO implements DAO<User> {

	@Override
	public void insert(User obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("public.\"user\" ");
		sql.append("  (name, lastname, email, privilege, birthdate, cpf, password) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?, ?, ?, ?) ");
		PreparedStatement stat = null;

		try {
			LocalDate birthdate = obj.getBirthdate();
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getName());
			stat.setString(2, obj.getLastname());
			stat.setString(3, obj.getEmail());
			stat.setInt(4, obj.getPrivilege().getId());
			stat.setDate(5, (birthdate == null)?null:Date.valueOf(birthdate));
			stat.setString(6, obj.getCpf());
			stat.setString(7, obj.getPassword());

			stat.execute();

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

	@Override
	public void update(User obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE public.\"user\" SET ");
		sql.append("  name = ?, ");
		sql.append("  lastname = ?, ");
		sql.append("  email = ?, ");
		sql.append("  privilege = ?, ");
		sql.append("  birthdate = ?, ");
		sql.append("  cpf = ?, ");
		sql.append("  password = ? ");
		sql.append("WHERE ");
		sql.append("  id = ? ");

		PreparedStatement stat = null;

		try {
			LocalDate birthdate = obj.getBirthdate();
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getName());
			stat.setString(2, obj.getLastname());
			stat.setString(3, obj.getEmail());
			stat.setInt(4, obj.getPrivilege().getId());
			stat.setDate(5, (birthdate == null)?null:Date.valueOf(birthdate));
			stat.setString(6, obj.getCpf());
			stat.setObject(7, obj.getPassword());
			stat.setInt(8, obj.getId());

			stat.execute();

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

	@Override
	public void delete(User obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM public.\"user\" WHERE id = ?");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());
			stat.execute();

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

	@Override
	public List<User> getAll() throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<User> listaUsuario = new ArrayList<>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  u.id, ");
		sql.append("  u.name, ");
		sql.append("  u.lastname, ");
		sql.append("  u.email, ");
		sql.append("  u.privilege, ");
		sql.append("  u.birthdate, ");
		sql.append("  u.cpf, ");
		sql.append("  u.password ");
		sql.append("FROM  ");
		sql.append("  public.\"user\" u ");
		sql.append("ORDER BY u.nome ");

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
				user.setPassword(rs.getString("pasword"));

				listaUsuario.add(user);
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

		return listaUsuario;
	}

	@Override
	public User getOne(User obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		
		User user = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  u.id, ");
		sql.append("  u.name, ");
		sql.append("  u.lastname, ");
		sql.append("  u.email, ");
		sql.append("  u.privilege, ");
		sql.append("  u.birthdate, ");
		sql.append("  u.cpf, ");
		sql.append("  u.password ");
		sql.append("FROM  ");
		sql.append("  public.\"user\" u ");
		sql.append("WHERE u.id = ? ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());

			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				Date birthdate = rs.getDate("data_nascimento");
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setPrivilege(Privilege.valueOf(rs.getInt("privilege")));
				user.setBirthdate(birthdate == null ? null : birthdate.toLocalDate());
				user.setCpf(rs.getString("cpf"));
				user.setPassword(rs.getString("password"));
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

		return user;
	}

	public static User validateLogin(User obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  u.id, ");
		sql.append("  u.name, ");
		sql.append("  u.lastname, ");
		sql.append("  u.email, ");
		sql.append("  u.privilege, ");
		sql.append("  u.birthdate, ");
		sql.append("  u.cpf, ");
		sql.append("  u.password ");
		sql.append("FROM  ");
		sql.append("  public.\"user\" u ");
		sql.append("WHERE u.email = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getEmail());

			ResultSet rs = stat.executeQuery();
			
			if (rs.next()) {
				User user = new User();
				Date birthdate = rs.getDate("birthdate");
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setPrivilege(Privilege.valueOf(rs.getInt("privilege")));
				user.setBirthdate(birthdate == null ? null : birthdate.toLocalDate());
				user.setCpf(rs.getString("cpf"));
				user.setPasswordBase64(rs.getString("password"));

				if (user.getPassword().equals(obj.getPassword()))
					return user;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
