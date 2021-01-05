package br.com.control;

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import br.com.conexao.ClasseDeConexaoBD;
import br.com.model.CadastroEmprestimoModel;
import br.com.model.IdsImportantesModel;
import br.com.model.LivrosCadastroModel;
import br.com.model.LoginModel;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class BDEmprestimo {

	ClasseDeConexaoBD classeConexaoBD = new ClasseDeConexaoBD();
	Connection con = classeConexaoBD.getConnection();
	ResultSet rs = null;
	PreparedStatement stmt = null;
	Statement statement = null;

	private boolean VerificaIdsUso = false;

	public boolean isVerificaIdsUso() {
		return VerificaIdsUso;
	}

	public void setVerificaIdsUso(boolean verificaIdsUso) {
		VerificaIdsUso = verificaIdsUso;
	}

	public void mostrandoLivros() {

		ClasseDeConexaoBD classeConexaoBD = new ClasseDeConexaoBD();
		Connection con = classeConexaoBD.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Statement statement = null;

		try {

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("erro na tela na insersão de livros"
					+ e.getMessage());

		} finally {
			classeConexaoBD.closeConnection(con, stmt);
		}

	}

	// metodo para fazer o emprestimo do livro

	public void fazendoEmprestimo() {

		ClasseDeConexaoBD classeConexaoBD = new ClasseDeConexaoBD();
		Connection con = classeConexaoBD.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Statement statement = null;

		try {

			// declarando objetos

			CadastroEmprestimoModel cadastroEmModel = new CadastroEmprestimoModel();

			stmt = con
					.prepareStatement("insert into tb_emprestimo(data_emprestimo, data_devolucao,id_funcionario, id_livro_fk) values (?,?,?,?);");

			stmt.setString(1, cadastroEmModel.getData_devolucao());
			stmt.setString(2, cadastroEmModel.getData_emprestimo());
			stmt.setInt(3, cadastroEmModel.getIdFuncinario());
			stmt.setInt(4, cadastroEmModel.getIdLivro());

			// System.out.println(livrosCadastroModel.getDataPublicao());

			stmt.executeUpdate();

			
			JOptionPane mensagemOk = new JOptionPane();

			
			mensagemOk.showMessageDialog(null, "Livro Alugado");
			

			
		} catch (Exception e) {
			

		

		
			
			System.out.println("erro na tela na insersão do emprestimo"
					+ e.getMessage());

		} finally {
			classeConexaoBD.closeConnection(con, stmt);
		}

	}

	public boolean metodoVericaTbEmprestimo(int idLivro) {

		ResultSet resultset = null;
		PreparedStatement smt = null;
		Connection con = classeConexaoBD.getConnection();

		try {

			CadastroEmprestimoModel cadastroEmModel = new CadastroEmprestimoModel();

			smt = (PreparedStatement) con
					.prepareStatement("SELECT * FROM tb_emprestimo where id_livro_fk = ?");
			smt.setInt(1, cadastroEmModel.getIdLivro());

			resultset = smt.executeQuery();

			if (resultset.next()) {
				VerificaIdsUso = true;

				System.out.println(VerificaIdsUso);

			}

		} catch (Exception ex) {

			Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		return VerificaIdsUso;

	}

}
