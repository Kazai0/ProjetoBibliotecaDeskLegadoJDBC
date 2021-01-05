package br.com.conexao;

// importação de todos os pacotes necesseários, entre eles, pacote de sql, jbdc e pacotes para extrair objetos de outras classes

import java.io.PrintStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import br.com.model.LoginModel;
import br.com.view.TelaLogin;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.ConnectionFeatureNotAvailableException;

// abertura de uma classe publica, que realizará nossa conexão com o banco de dados

public class ClasseDeConexaoBD {

	// declarando os atributos resonsáveis por:  1 endereço do banco de dados do servidor, 2 usuario, 3 senha e por ultimo 4 driver de conexão

	private static final String servidor = "jdbc:mysql://localhost:3306/BibliotecaDatacom";
	private static final String usuario = "root";
	private static final String senha = "";
	private static final String driver = "com.mysql.jdbc.Driver";

	// criação do método de conexão 
	
	public Connection getConnection() {

		// exceção utilizando o metodo Class. que retornará a conexão, se não exibirá uma exceção de Classe ou de SQl
		
		try {
			Class.forName(driver);

			return (Connection) DriverManager.getConnection(servidor, usuario,
					senha);

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("erro na conxeao", e);
		}

	}

	
	// está Classe é responsável por fechar a conexão com o BD, se a conexão for null
	
	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// Está classe serve para o fechamento do BD, usando como parâmetro con, stmt, se os dois forem null, fecham a conexão
	
	public static void closeConnection(Connection con,
			java.sql.PreparedStatement stmt) {

		closeConnection(con);

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Fecha a conexão com o BD, usando como parâmetro o con, stmt, rs
	
	public static void closeConnection(Connection con,
			java.sql.PreparedStatement stmt, ResultSet rs) {

		closeConnection(con, stmt);

		try {

			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
