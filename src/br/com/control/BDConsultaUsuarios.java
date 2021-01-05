package br.com.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.conexao.ClasseDeConexaoBD;
import br.com.model.IdsImportantesModel;
import br.com.model.LoginModel;
import br.com.model.UsuariosModel;
import br.com.view.TelaConsultaUsuarios;

public class BDConsultaUsuarios {

	public List<UsuariosModel> read() {

		ClasseDeConexaoBD conexao = new ClasseDeConexaoBD();

		Connection con = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<UsuariosModel> usuarios = new ArrayList<>();

		try {

			stmt = (PreparedStatement) con.prepareStatement("");
			rs = stmt
					.executeQuery("SELECT * FROM tb_funcionarios order by nome");

			while (rs.next()) {

				UsuariosModel usuario = new UsuariosModel();

				usuario.setId_funcionario(rs.getInt("id_funcionario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTelefone(rs.getString("telefone"));

				usuarios.add(usuario);

			}

		} catch (Exception e) {

		} finally {
			ClasseDeConexaoBD.closeConnection(con, stmt, rs);
		}

		return usuarios;

	}

	public List<UsuariosModel> ReadPesquisar(String pesquisa) {

		ClasseDeConexaoBD conexao = new ClasseDeConexaoBD();

		Connection con = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<UsuariosModel> usuarios = new ArrayList<>();

		try {

			UsuariosModel usuariosModel = new UsuariosModel();

			stmt = (PreparedStatement) con
					.prepareStatement("select * from tb_funcionarios  where nome like ? order by nome");
			stmt.setString(1, '%' + usuariosModel.getPesquisarUsuarios() + '%');

			rs = stmt.executeQuery();

			while (rs.next()) {

				UsuariosModel usuario = new UsuariosModel();

				usuario.setId_funcionario(rs.getInt("id_funcionario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTelefone(rs.getString("telefone"));

				usuarios.add(usuario);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ClasseDeConexaoBD.closeConnection(con, stmt, rs);
		}

		return usuarios;

	}

	public void metodoRemoverUsuarios() {
		

		ClasseDeConexaoBD conexao = new ClasseDeConexaoBD();

		Connection con = conexao.getConnection();
		PreparedStatement stmt = null;

		IdsImportantesModel iDI = new IdsImportantesModel();
		TelaConsultaUsuarios tConsultaUsuarios = new TelaConsultaUsuarios();

		try {

			stmt = (PreparedStatement) con
					.prepareStatement("delete from tb_funcionarios where id_funcionario = ?");

			stmt.setInt(1, IdsImportantesModel.getIdFuncionario());

			stmt.executeUpdate();

			JOptionPane mensagemOk = new JOptionPane();

			mensagemOk.showMessageDialog(null, "Usuario Excluido com Sucesso ");

		} catch (Exception ex) {

			JOptionPane mensagemOk = new JOptionPane();

			mensagemOk.showMessageDialog(null, "ocorreu um erro inesperado" + ex);
			Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE,null, ex); 
		} finally {
			ClasseDeConexaoBD.closeConnection(con, stmt);

		}

	}

}
