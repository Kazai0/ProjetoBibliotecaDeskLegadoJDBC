package br.com.control;

import java.util.ArrayList;
import java.util.List;
import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import br.com.conexao.ClasseDeConexaoBD;
import br.com.model.LivrosCadastroModel;
import br.com.model.LivrosEmprestimoModelTabela;
import br.com.model.PesquisarModel;
import br.com.view.TelaEmprestimoLivros;

public class EmprestimoLivrosDao {

	//método responsével por mostrar 
	
	public List<LivrosEmprestimoModelTabela> read() {

		ClasseDeConexaoBD conexao = new ClasseDeConexaoBD();

		Connection con = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		TelaEmprestimoLivros t = new TelaEmprestimoLivros();

		List<LivrosEmprestimoModelTabela> livros = new ArrayList();

		try {
			stmt = con
					.prepareStatement("");
			rs = stmt
					.executeQuery("SELECT * FROM tb_livros order by titulo_livro");

			while (rs.next()) {

				LivrosEmprestimoModelTabela livro = new LivrosEmprestimoModelTabela();

				livro.setIdLivro(rs.getInt("id_livro"));
				livro.setNomeLivro(rs.getString("titulo_livro"));
				livro.setQtdLivros(rs.getInt("qtd"));

				livro.setGeneroLivro(rs.getString("genero"));
				livro.setNomeAutor(rs.getString("autor"));
				livro.setAlugado(rs.getString("alugado"));

				livros.add(livro);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ClasseDeConexaoBD.closeConnection(con, stmt, rs);
		}

		return livros;
}

	//método referente a pesquisa no banco de dados:
	
	public List<LivrosEmprestimoModelTabela> readPesquisar(String pesquisa) {

		ClasseDeConexaoBD conexao = new ClasseDeConexaoBD();

		Connection con = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		TelaEmprestimoLivros t = new TelaEmprestimoLivros();

		List<LivrosEmprestimoModelTabela> livros = new ArrayList();

		try {
			PesquisarModel pesquisaModel = new PesquisarModel();
			
			stmt = (PreparedStatement) con.prepareStatement("select * from tb_livros  where titulo_livro like ? order by titulo_livro");

			stmt.setString(1,'%'+pesquisaModel.getPesquisarRemover()+'%');

			rs = stmt.executeQuery();
			
			rs = stmt.executeQuery();

			while (rs.next()) {

				LivrosEmprestimoModelTabela livro = new LivrosEmprestimoModelTabela();

				livro.setIdLivro(rs.getInt("id_livro"));
				livro.setNomeLivro(rs.getString("titulo_livro"));
				livro.setQtdLivros(rs.getInt("qtd"));

				livro.setGeneroLivro(rs.getString("genero"));
				livro.setNomeAutor(rs.getString("autor"));
				livro.setAlugado(rs.getString("alugado"));

				livros.add(livro);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ClasseDeConexaoBD.closeConnection(con, stmt, rs);
		}

		return livros;

	}

	}

