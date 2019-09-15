package br.barretuino.dao;
/**
 * Classe DAO - Data Access Object
 * @date 29/11/2015
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.GregorianCalendar;

import br.barretuino.modelagem.Medida;
import br.barretuino.repository.ConnectionFactory;

public class MedidaDAO {
	//Estabele conexão com o banco de dados
	private Connection connection;
	public MedidaDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}
	public void adiciona(Medida medida) throws SQLException {
		//Prepara statement para inserção
		PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO MEDIDAS (DATA, HORA, VALOR) VALUES (?, ?, ?)");
		
		//Seta os valores dos argumentos da stmt	
		GregorianCalendar atual = new GregorianCalendar();
		Date data = new Date(atual.getTime().getYear(), atual.getTime().getMonth(), atual.getTime().getDate());
		Time hora = new Time(atual.getTime().getHours(), atual.getTime().getMinutes(), atual.getTime().getSeconds());
		
		stmt.setDate(1, data);
		stmt.setTime(2, hora);
		stmt.setFloat(3,(float) medida.getValor());
		
		//Executa query
		stmt.execute();
		stmt.close();
	}
}