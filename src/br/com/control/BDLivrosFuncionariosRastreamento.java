package br.com.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import br.com.conexao.ClasseDeConexaoBD;
import br.com.model.LivrosFuncionariosRastreamentoModel;
import br.com.model.LivrosFuncionariosRastreamentoModel;
import br.com.view.TelaRastreamentoLivros;


public class BDLivrosFuncionariosRastreamento {

	public List<LivrosFuncionariosRastreamentoModel> read() {

		ClasseDeConexaoBD conexao = new ClasseDeConexaoBD();

		Connection con = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<LivrosFuncionariosRastreamentoModel> rastreamentos = new ArrayList();

		try {
			stmt = con
					.prepareStatement("select f.nome, l.titulo_livro, e.data_emprestimo, data_devolucao from tb_funcionarios f\r\n" + 
							"join tb_emprestimo e\r\n" + 
							"on f.id_funcionario = e.id_funcionario\r\n" + 
							"join tb_livros l\r\n" + 
							"on l.id_livro = e.id_livro_fk\r\n" + 
							"order by f.nome;");
			rs = stmt
					.executeQuery("select f.nome, l.titulo_livro, e.data_emprestimo, data_devolucao from tb_funcionarios f\r\n" + 
							"join tb_emprestimo e\r\n" + 
							"on f.id_funcionario = e.id_funcionario\r\n" + 
							"join tb_livros l\r\n" + 
							"on l.id_livro = e.id_livro_fk\r\n" + 
							"order by f.nome;");

			while (rs.next()) {

				LivrosFuncionariosRastreamentoModel rastreamento = new LivrosFuncionariosRastreamentoModel();

				rastreamento.setNome(rs.getString("nome"));
				rastreamento.setLivro(rs.getString("titulo_livro"));
				rastreamento.setDataEmprestimo(rs.getString("data_emprestimo"));
				rastreamento.setDataDevolucao(rs.getString("data_devolucao"));

				rastreamentos.add(rastreamento);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ClasseDeConexaoBD.closeConnection(con, stmt, rs);
		}

		return rastreamentos;
}

}