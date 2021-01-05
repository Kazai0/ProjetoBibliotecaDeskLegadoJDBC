package br.com.control;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import br.com.conexao.ClasseDeConexaoBD;
import br.com.model.CadastroModel;
import br.com.view.TelaCadastro;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class BDCadastro {

	public void metodoInserindoDados(CadastroModel f) {
		ClasseDeConexaoBD classeConexao = new ClasseDeConexaoBD();
		Connection con = classeConexao.getConnection();
		PreparedStatement stmt = null;
		Statement statement = null;

		CadastroModel cadastroModel = new CadastroModel();
		JOptionPane mensagemOk = new JOptionPane();

		try {
			stmt = (PreparedStatement) con
					.prepareStatement("insert into tb_funcionarios(nome, email, usuario, senha, nascimento, telefone) values (?,?,?,?,?,?);");

			stmt.setString(1, f.getNome());
			stmt.setString(2, f.getEmail());
			stmt.setString(3, f.getUsuario());
			stmt.setString(4, f.getSenha());
			stmt.setString(5, f.getDataNascimentoParaMysql());
			stmt.setString(6, f.getTelefone());

			stmt.executeUpdate();

			mensagemOk.showMessageDialog(null, "Usuario Cadastrado com Sucesso");
			mensagemOk.showMessageDialog(null, "Abra a Biblioteca e faça Login");

			System.exit(0);

		} catch (Exception e) {
			JOptionPane mensagemErro = new JOptionPane();

			mensagemErro.showMessageDialog(null,
					"Erro desconhecido, revise seus dados");

		} finally {
			classeConexao.closeConnection(con, stmt);

		}

	}

}
