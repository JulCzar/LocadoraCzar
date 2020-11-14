package br.czar.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.czar.model.Movie;
import br.czar.model.Parental;
import br.czar.util.Utils;

public class MovieDAO implements DAO<Movie> {

	@Override
	public void insert(Movie obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("filmes ");
		sql.append("  (title, sinopse, release, rate, image) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?, ?) ");
		PreparedStatement stat = null;

		try {
			LocalDate birthdate = obj.getRelease();
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getTitle());
			stat.setString(2, obj.getSinopse());
			stat.setDate(3, (birthdate == null)?null:Date.valueOf(birthdate));
			stat.setInt(4, obj.getRate().getId());
			stat.setString(5, obj.getImage());

			stat.execute();

			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando SQL - INSERT.");
			e.printStackTrace();

			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar ROLLBACK.");
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
	public void update(Movie obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE filmes SET ");
		sql.append("  title = ?, ");
		sql.append("  sinopse = ?, ");
		sql.append("  release = ?, ");
		sql.append("  rate = ?, ");
		sql.append("  image = ? ");
		sql.append("WHERE ");
		sql.append("  id = ? ");

		PreparedStatement stat = null;

		try {
			LocalDate birthdate = obj.getRelease();
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getTitle());
			stat.setString(2, obj.getSinopse());
			stat.setDate(3, (birthdate == null)?null:Date.valueOf(birthdate));
			stat.setInt(4, obj.getRate().getId());
			stat.setString(5, obj.getImage());
			stat.setInt(6, obj.getId());

			stat.execute();

			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando SQL - UPDATE.");
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
	public void delete(Movie obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM filmes WHERE id = ?");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());
			stat.execute();

			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando SQL - DELETE.");
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
	public List<Movie> getAll() throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Movie> movieList = new ArrayList<>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  f.id, ");
		sql.append("  f.title, ");
		sql.append("  f.sinopse, ");
		sql.append("  f.release, ");
		sql.append("  f.rate, ");
		sql.append("  f.image ");
		sql.append("FROM  ");
		sql.append("  filmes f ");
		sql.append("ORDER BY f.title ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Movie movie = new Movie();
				Date release = rs.getDate("release");
				movie.setId(rs.getInt("id"));
				movie.setTitle(rs.getString("title"));
				movie.setSinopse(rs.getString("sinopse"));
				movie.setRelease(release == null ? null : release.toLocalDate());
				movie.setRate(Parental.valueOf(rs.getInt("rate")));
				movie.setImage(rs.getString("image"));

				movieList.add(movie);
			}

		} catch (SQLException e) {
			Utils.addErrorMessage("Não foi possivel buscar os dados do filme.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um SQL em MovieDAO.");
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

		return movieList;
	}

	@Override
	public Movie getOne(Movie obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		
		Movie movie = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  f.id, ");
		sql.append("  f.title, ");
		sql.append("  f.sinopse, ");
		sql.append("  f.release, ");
		sql.append("  f.rate, ");
		sql.append("  f.image ");
		sql.append("FROM  ");
		sql.append("  filmes f ");
		sql.append("WHERE f.id = ? ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());

			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				movie = new Movie();
				Date release = rs.getDate("release");
				movie.setId(rs.getInt("id"));
				movie.setTitle(rs.getString("title"));
				movie.setSinopse(rs.getString("sinopse"));
				movie.setRelease(release == null ? null : release.toLocalDate());
				movie.setRate(Parental.valueOf(rs.getInt("rate")));
				movie.setImage(rs.getString("image"));
			}

		} catch (SQLException e) {
			Utils.addErrorMessage("Não foi possivel buscar os dados do filme.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em MovieDAO.");
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

		return movie;
	}
	
	public List<Movie> search(String query) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Movie> movieList = new ArrayList<>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  f.id, ");
		sql.append("  f.title, ");
		sql.append("  f.sinopse, ");
		sql.append("  f.release, ");
		sql.append("  f.rate, ");
		sql.append("  f.image ");
		sql.append("FROM  ");
		sql.append("  filmes f ");
		sql.append("Where  ");
		sql.append("  f.title LIKE ?");
		sql.append("ORDER BY f.title ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + query + "%");

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Movie movie = new Movie();
				Date release = rs.getDate("release");
				movie.setId(rs.getInt("id"));
				movie.setTitle(rs.getString("title"));
				movie.setSinopse(rs.getString("sinopse"));
				movie.setRelease(release == null ? null : release.toLocalDate());
				movie.setRate(Parental.valueOf(rs.getInt("rate")));
				movie.setImage(rs.getString("image"));

				movieList.add(movie);
			}

		} catch (SQLException e) {
			Utils.addErrorMessage("Não foi possivel buscar os dados do filme.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um SQL em MovieDAO.");
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

		return movieList;
	}
}
