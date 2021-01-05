package br.com.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.conexao.ClasseDeConexaoBD;
import br.com.model.PesquisarModel;
import br.com.view.TelaMenu;
import br.com.view.TelaRemoverLivros;

public class BDPesquisar {
	

	
	

	public void metodoPesquisar(String pesquisarRemover){
		
		ClasseDeConexaoBD classeConexaoBD = new ClasseDeConexaoBD();
		Connection con = classeConexaoBD.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Statement statement = null;
		
		try {
			stmt = (PreparedStatement) con.prepareStatement("select * from tb_livros  where titulo_livro like '%senhor%'");
	
			stmt.executeQuery();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public List<PesquisarModel> read(String pesquisar1) {
		
		ClasseDeConexaoBD classeConexaoBD = new ClasseDeConexaoBD();
		Connection con = classeConexaoBD.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		TelaRemoverLivros telaRemover = new TelaRemoverLivros();
		
		List<PesquisarModel> pesquisar = new ArrayList();
		
		try{
			
		PesquisarModel pesquisa = new PesquisarModel();
		
		stmt = (PreparedStatement) con.prepareStatement("select * from tb_livros  where titulo_livro like ?");

		stmt.setString(1,'%'+pesquisa.getPesquisarRemover()+'%');
	
		
		rs = stmt.executeQuery();
		
		
		while (rs.next()){
			
			
			int a = rs.getInt("id_livro");
			String b = rs.getString("titulo_livro");
			int c = rs.getInt("qtd");
			String d = rs.getString("genero");
			String e = rs.getString("autor");
			String f = rs.getString("alugado");

			pesquisa.setIdLivro(rs.getInt("id_livro"));
			pesquisa.setNomeLivro(rs.getString("titulo_livro"));
			pesquisa.setQtdLivros(rs.getInt("qtd"));

			pesquisa.setGeneroLivro(rs.getString("genero"));
			pesquisa.setNomeAutor(rs.getString("autor"));
			pesquisa.setAlugado(rs.getString("alugado"));

		}
		
		
	} catch (SQLException e) {

		e.printStackTrace();
	} finally {
		ClasseDeConexaoBD.closeConnection(con, stmt, rs);
	}
		return pesquisar;
		
		
	
		
		
	}
	
	







	


}



