package br.com.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.text.StyleContext.SmallAttributeSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import br.com.conexao.ClasseDeConexaoBD;
import br.com.model.LivrosCadastroModel;

public class BDLivrosCadastro {

	// Essa classe está relacionada ao cadastro dos livros

	public void inserindoLivros() {

		ClasseDeConexaoBD classeConexaoBD = new ClasseDeConexaoBD();
		Connection con = classeConexaoBD.getConnection();
		PreparedStatement stmt = null;
		JOptionPane mensagemCadastroLivros = new JOptionPane();

		LivrosCadastroModel livrosCadastroModel = new LivrosCadastroModel();

		try {


			stmt = con
					.prepareStatement("insert into tb_livros(titulo_livro, autor, genero, qtd) values (?,?,?,?);");

			stmt.setString(1, livrosCadastroModel.getNomeLivro());
			stmt.setString(2, livrosCadastroModel.getNomeAutor());
			stmt.setString(3, livrosCadastroModel.getGeneroLivro());
			stmt.setInt(4, livrosCadastroModel.getQtdLivros());

			stmt.executeUpdate();

		
			
			mensagemCadastroLivros.showMessageDialog(null,"Livro cadastrado com sucesso");

		} catch (Exception e) {
			System.out.println("erro na tela na insersão de livros"
					+ e.getMessage());

		} finally {
			classeConexaoBD.closeConnection(con, stmt);
		}

	}

}
