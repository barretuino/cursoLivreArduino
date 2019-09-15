package br.barretuino.repository;
/**
 * Projeto de Ger�ncia
 * ConnectionFactory - F�brica de Conex�es com o Banco
 * @autor Prof. Paulo Barreto
 * @date 21/09/2019
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/pjGerencia","root","admin");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}
	}
}