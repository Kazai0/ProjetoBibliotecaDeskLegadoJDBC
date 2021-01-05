package br.com.control;

import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.conexao.ClasseDeConexaoBD;
import br.com.model.CadastroModel;
import br.com.model.IdsImportantesModel;
import br.com.model.LoginModel;
import br.com.model.LoginModel;
import br.com.view.TelaLogin;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ConnectionFeatureNotAvailableException;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javax.swing.*;

public class BDLogin extends ClasseDeConexaoBD {


	boolean check = false;

	public void listaContatos() {

		ClasseDeConexaoBD classeConexao = new ClasseDeConexaoBD();
		
		Connection con = classeConexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet resultset = null;
		TelaLogin tl = new TelaLogin();
		IdsImportantesModel idF = new IdsImportantesModel();

		try {

			String usuario;

			usuario = tl.txtUsuario.getText();

			// Glória a Deus

			String rs = "SELECT * FROM tb_funcionarios where usuario ='"
					+ usuario + "'";

			stmt = (PreparedStatement) con
					.prepareStatement("SELECT * FROM tb_funcionarios");
			resultset = stmt.executeQuery(rs);
			while (resultset.next()) {

				System.out.println("oi");

				System.out.println("Id livro: "
						+ resultset.getString("id_funcionario"));

				int idFuncionario;

				// valor do atributo idCliente da classe IdFuncionario receberá
				// o id do funcionario que se logou do banco de dados

				idF.IdFuncionario = resultset.getInt("id_funcionario");

				System.out.println(idF.IdFuncionario);

				// resultset.getString("id_funcionario");

			}

		} catch (Exception e) {

			System.out.println("erro na lista de contatos" + e.getMessage());

		}
		finally {
			ClasseDeConexaoBD.closeConnection(con, stmt);
		}
		
	}

	public boolean metodoVerificarNome(String nome, String senha) {

		ClasseDeConexaoBD classeConexao = new ClasseDeConexaoBD();
		
		Connection con = classeConexao.getConnection();

		ResultSet resultset = null;
		PreparedStatement stmt = null;

		try {

			stmt = (PreparedStatement) con
					.prepareStatement("SELECT * FROM tb_funcionarios where usuario = ? and senha = ? ");
			stmt.setString(1, nome);
			stmt.setString(2, senha);

			resultset = stmt.executeQuery();

			if (resultset.next()) {
				check = true;

			}

		} catch (Exception ex) {

			Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		finally {
			classeConexao.closeConnection(con, stmt);
		}
		

		return check;
	}
}
