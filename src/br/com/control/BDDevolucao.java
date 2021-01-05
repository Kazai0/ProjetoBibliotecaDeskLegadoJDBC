package br.com.control;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.conexao.ClasseDeConexaoBD;
import br.com.model.CadastroEmprestimoModel;
import br.com.model.IdsImportantesModel;
import br.com.model.LivrosEmprestimoModelTabela;
import br.com.model.LoginModel;
import br.com.view.TelaDevolucao;
import br.com.view.TelaEmprestimoLivros;

public class BDDevolucao {

	

	public void metodoDevolveLivro(int idFuncionario) {

		IdsImportantesModel idf = new IdsImportantesModel();
		
		BDAlugar bDesalugar = new BDAlugar();

		ClasseDeConexaoBD classeConexaoBD = new ClasseDeConexaoBD();
		Connection con = classeConexaoBD.getConnection();

		PreparedStatement stmt = null;
	

		try {

			stmt = (PreparedStatement) con
					.prepareStatement("delete from tb_emprestimo where id_funcionario = ? and id_livro_fk = ?");
			stmt.setInt(1, idf.IdFuncionario);
			stmt.setInt(2, idf.idLivroDevolve);

			if ( metodoVerificaLivro(idFuncionario) == true) {
				stmt.executeUpdate();
				System.out.println("ok");
				
				JOptionPane JMsg = new JOptionPane();
				JMsg.showMessageDialog(null, "Livro Devolvido");
				bDesalugar.metodoDesalugar(idf.idLivroDevolve);
				
			}
			else{
				JOptionPane JMsg = new JOptionPane();
				JMsg.showMessageDialog(null, "você não alugou este livro");
			}
			
			// resultset = smt.executeQuery();

		} catch (Exception ex) {

			Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		finally {
		 ClasseDeConexaoBD.closeConnection(con, stmt);
		}

	}

	// método para encontrar o ID do livro alugado através do id do funcionario

	public boolean metodoVerificaLivro(int idFuncionario) {
		IdsImportantesModel idf = new IdsImportantesModel();
		
		BDAlugar bDesalugar = new BDAlugar();

		ClasseDeConexaoBD classeConexaoBD = new ClasseDeConexaoBD();
		Connection con = classeConexaoBD.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Statement statement = null;
		
		TelaDevolucao telaDevolucao = new TelaDevolucao();
		
		
		int i = 0 ;


		try {

			int usuario;

			usuario = idf.getIdFuncionario();

			System.out.println("j");
			System.out.println(usuario);
	
			stmt = (PreparedStatement) con
					.prepareStatement("select * from tb_emprestimo where id_funcionario = ? and id_livro_fk = ?");
			
			stmt.setInt(1, idf.IdFuncionario);
			stmt.setInt(2, idf.idLivroDevolve);

			System.out.println(rs);

			stmt.executeQuery();

			rs = stmt.executeQuery();

			
			while (rs.next()) {	
				
			i = rs.getInt("id_livro_fk");
				
			System.out.println("id_livro_fk é"+i);
			
			if (i == idf.idLivroDevolve) {

				return true;
			}
		
			}
	

		} catch (Exception ex) {

			Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE,null, ex);
		}	
		finally {
			ClasseDeConexaoBD.closeConnection(con, stmt, rs);
		}
		
	
		return false;


	}

	

}




