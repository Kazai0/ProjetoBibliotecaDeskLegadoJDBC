package br.com.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.conexao.ClasseDeConexaoBD;
import br.com.model.CadastroEmprestimoModel;
import br.com.model.IdsImportantesModel;
import br.com.model.LivrosEmprestimoModelTabela;
import br.com.model.LoginModel;
import br.com.view.TelaEmprestimoLivros;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class BDAlugar {

	public void metodoAtulizandoAlugar(int idFuncionario) {

		ClasseDeConexaoBD classeConexao = new ClasseDeConexaoBD();
		Connection con = classeConexao.getConnection();
		PreparedStatement stmt = null;

		try {

			CadastroEmprestimoModel caE = new CadastroEmprestimoModel();

			stmt = (PreparedStatement) con
					.prepareStatement("update tb_livros set alugado = 'alugado' where id_livro = ?");
			stmt.setInt(1, caE.getIdLivro());

			stmt.executeUpdate();

		} catch (Exception ex) {

			Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		finally {
			classeConexao.closeConnection(con, stmt);
		}
	


	}

	public void metodoDesalugar(int idFuncionario) {

		IdsImportantesModel idf = new IdsImportantesModel();

		ClasseDeConexaoBD classeConexao = new ClasseDeConexaoBD();
		Connection con = classeConexao.getConnection();
		PreparedStatement stmt = null;

		try {

			CadastroEmprestimoModel caE = new CadastroEmprestimoModel();

			stmt = (PreparedStatement) con
					.prepareStatement("update tb_livros set alugado = ' ' where id_livro = ?");
			stmt.setInt(1, idf.getIdLivroDevolve());

			stmt.executeUpdate();

		} catch (Exception ex) {

			Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		finally {
			classeConexao.closeConnection(con, stmt);
		}
		
		

	}

}
