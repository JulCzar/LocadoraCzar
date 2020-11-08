package br.czar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	public void insert(T obj) throws Exception;
	public void update(T obj) throws Exception;
	public void delete(T obj) throws Exception;
	public List<T> getAll() throws Exception;
	public T getOne(T obj) throws Exception;
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/locadora_czar", "topicos1", "123456");
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao registar a conexao.");
			e.printStackTrace();
		}

		return conn;
	}
}