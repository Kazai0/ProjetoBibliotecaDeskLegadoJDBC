package br.com.control;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import br.com.conexao.ClasseDeConexaoBD;
import br.com.model.IdsImportantesModel;
import br.com.model.LoginModel;
import br.com.view.TelaEmprestimoLivros;
import br.com.view.TelaRemoverLivros;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class BDRemoverLivros {



	public void metodoRemoverLivro(int idFuncionario) {

		IdsImportantesModel idf = new IdsImportantesModel();

		ClasseDeConexaoBD classeConexaoBD = new ClasseDeConexaoBD();
		Connection con = classeConexaoBD.getConnection();
		PreparedStatement stmt = null;

		TelaRemoverLivros tRemover = new TelaRemoverLivros();
		JOptionPane mensagem = new JOptionPane();

		try {

			stmt = (PreparedStatement) con
					.prepareStatement("delete from tb_livros where id_livro = ?");
			stmt.setInt(1, idf.getIdLivroRemove());

			stmt.executeUpdate();

			mensagem.showMessageDialog(null, "livro excluido com sucesso");

		} catch (Exception ex) {

			mensagem.showMessageDialog(null, "ocorreu um erro inesperado"+ ex);
			
			Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE,null, ex);
		}
		finally{
			ClasseDeConexaoBD.closeConnection(con, stmt);
		}

	}

}
