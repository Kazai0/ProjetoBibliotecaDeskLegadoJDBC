package br.com.view;

import javax.swing.*;

import br.com.control.BDLogin;
import br.com.model.LoginModel;
import br.com.model.LoginModel;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class TelaLogin implements ActionListener {

	// criação dos objetos
	static JFrame janelaLogin = new JFrame("Sistema Biblioteca Datacom");
	static JPanel painelLogin = new JPanel();

	// criação dos objetos botoes
	static JButton btnEntrar = new JButton();
	static JButton btnCadastrar = new JButton();

	// criação do objetos de texto
	static public JTextField txtUsuario = new JTextField("");
	static JPasswordField txtSenha = new JPasswordField();

	// criação dos label

	static JLabel lblUsuario = new JLabel();
	static JLabel lblSenha = new JLabel();
	

	// caixa de texto

	static JOptionPane caixaTexto = new JOptionPane();

	// banco de dados

	static BDLogin bdLogin = new BDLogin();

	static LoginModel modeloLogin = new LoginModel();

	public void metodoJanelaLogin() {
		// setando os tamanhos da janela de login

		janelaLogin.setBounds(680, 400, 420, 250);
	

		janelaLogin.setVisible(true);

		// setando o tamanho do layout default
		painelLogin.setLayout(null);

		// setando txt's e lbl's

		lblUsuario.setBounds(80, 10, 100, 100);
		lblUsuario.setText("Usuario:");

		txtUsuario.setBounds(160, 52, 150, 25);

		lblSenha.setBounds(80, 60, 100, 100);
		lblSenha.setText("Senha:");

		txtSenha.setBounds(160, 100, 150, 25);

		// setando botao Entrar
		btnEntrar.addActionListener(this);
		btnEntrar.setActionCommand("Entrar");
		btnEntrar.setBounds(85, 160, 120, 20);
		btnEntrar.setText("Entrar");

		// setando botao Cadastrar

		btnCadastrar.addActionListener(this);
		btnCadastrar.setActionCommand("Cadastrar");
		btnCadastrar.setBounds(210, 160, 120, 20);
		btnCadastrar.setText("Cadastrar");
		
		janelaLogin.add(painelLogin);

		painelLogin.add(lblSenha);
		painelLogin.add(lblUsuario);
		painelLogin.add(txtUsuario);
		painelLogin.add(txtSenha);
		painelLogin.add(btnEntrar);
		painelLogin.add(btnCadastrar);

		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String action;

		action = e.getActionCommand();

		switch (action) {
		case "Entrar":

			modeloLogin.setNome(txtUsuario.getText());
			modeloLogin.setSenha(txtSenha.getText());

			if (bdLogin.metodoVerificarNome(modeloLogin.getNome(),
					modeloLogin.getSenha())) {

				bdLogin.listaContatos();

				TelaMenu telaMenuu = new TelaMenu();

				telaMenuu.metodoJanelaMenu();

				janelaLogin.setDefaultCloseOperation(janelaLogin.EXIT_ON_CLOSE);
				janelaLogin.dispose();

			} else {

				caixaTexto.showMessageDialog(null,
						"sua senha ou usuario invalido");

			}

			break;

		case "Cadastrar":

			if (TelaCadastro.instance != null) {
				
				TelaCadastro.instance.janelaCadastroUsuario.setVisible(true);
				
				
			}
			
			else {
				
				TelaCadastro tC = new  TelaCadastro();
				
				tC.menu();

			}
			
			janelaLogin.dispose();
	
		}

	}

	public void setaroscampos() {
		txtUsuario.setText("");
		txtSenha.setText("");

	}

}
