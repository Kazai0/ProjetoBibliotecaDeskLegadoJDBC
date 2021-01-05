package br.com.view;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.print.attribute.IntegerSyntax;
import javax.swing.*;
//import javax.xml.bind.ParseConversionEvent;

import br.com.control.BDCadastro;
import br.com.control.BDLogin;
import br.com.model.CadastroModel;

public class TelaCadastro implements ActionListener {

	public static TelaCadastro instance = null;

	GregorianCalendar data = new GregorianCalendar();

	JFrame janelaCadastroUsuario = new JFrame("Menu de Cadastro");
	JPanel painelCadastroUsuario = new JPanel();

	JLabel label = new JLabel();

	JTextField txtNome = new JTextField();
	JTextField txtNascimento = new JTextField();
	JTextField txtTelefone = new JTextField();
	JTextField txtEmail = new JTextField();
	JTextField txtUsuario = new JTextField();
	JPasswordField txtSenha = new JPasswordField();

	JLabel lblNome = new JLabel();
	JLabel lblNascimento = new JLabel();
	JLabel lblTelefone = new JLabel();
	JLabel lblEmail = new JLabel();
	JLabel lblUsuario = new JLabel();
	JLabel lblSenha = new JLabel();
	JButton btnVoltar = new JButton();
	JButton btnCadastrar = new JButton();

	JOptionPane caixaMenu = new JOptionPane();

	static CadastroModel modeloCadastroView = new CadastroModel();

	BDLogin cadastrarFuncionarioBD = new BDLogin();

	public void menu() {

		instance = this;

		// setando componente da janela
		btnVoltar.addActionListener(this);
		btnVoltar.setActionCommand("Voltar");
		btnVoltar.setBounds(250, 195, 105, 20);
		btnVoltar.setText("Voltar");

		btnCadastrar.addActionListener(this);
		btnCadastrar.setActionCommand("Cadastrar");
		btnCadastrar.setBounds(102, 195, 105, 20);
		btnCadastrar.setText("Cadastrar");

		lblNome.setBounds(10, 10, 100, 20);
		lblNome.setText("Nome:");

		lblNascimento.setBounds(10, 40, 100, 20);
		lblNascimento.setText("Nascimento:");

		lblTelefone.setBounds(10, 70, 100, 20);
		lblTelefone.setText("Telefone:");

		lblEmail.setBounds(10, 100, 100, 20);
		lblEmail.setText("Email:");

		lblUsuario.setBounds(10, 130, 100, 20);
		lblUsuario.setText("Usuario:");

		lblSenha.setBounds(10, 160, 100, 20);
		lblSenha.setText("Senha:");

		txtNome.setBounds(102, 10, 250, 25);

		txtNascimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		
		txtNascimento.setBounds(102, 40, 250, 25);
		txtTelefone.setBounds(102, 70, 250, 25);
		txtEmail.setBounds(102, 100, 250, 25);
		txtUsuario.setBounds(102, 130, 250, 25);
		txtSenha.setBounds(102, 160, 250, 25);

		janelaCadastroUsuario.setBounds(680, 400, 420, 250);
		painelCadastroUsuario.setLayout(null);

		
		janelaCadastroUsuario.add(painelCadastroUsuario);

		painelCadastroUsuario.add(lblNome);
		painelCadastroUsuario.add(lblNascimento);
		painelCadastroUsuario.add(lblTelefone);
		painelCadastroUsuario.add(lblEmail);
		painelCadastroUsuario.add(lblUsuario);
		painelCadastroUsuario.add(lblSenha);

		painelCadastroUsuario.add(txtNome);
		painelCadastroUsuario.add(txtNascimento);
		painelCadastroUsuario.add(txtTelefone);
		painelCadastroUsuario.add(txtEmail);
		painelCadastroUsuario.add(txtUsuario);
		painelCadastroUsuario.add(txtSenha);

		painelCadastroUsuario.add(btnVoltar);
		painelCadastroUsuario.add(btnCadastrar);

		janelaCadastroUsuario.setVisible(true);

	}

	public void metodoFechado() {
		TelaCadastro.this.janelaCadastroUsuario.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		switch (action) {
		case "Cadastrar":

			try {

				System.out.println("que isso");

				String dia = txtNascimento.getText().substring(0, 2);
				String mes = txtNascimento.getText().substring(3, 5);
				String ano = txtNascimento.getText().substring(6);

				modeloCadastroView.setDataNascimentoParaMysql(ano + "-" + mes+ "-" + dia);

				modeloCadastroView.setNome(txtNome.getText());
				modeloCadastroView.setEmail(txtEmail.getText());

				modeloCadastroView.setTelefone(txtTelefone.getText());
				modeloCadastroView.setUsuario(txtUsuario.getText());
				modeloCadastroView.setSenha(txtSenha.getText());

				BDCadastro cadastroDatabase = new BDCadastro();
				
				cadastroDatabase.metodoInserindoDados(modeloCadastroView);

			} catch (Exception e2) {

			}

			break;

		case "Voltar":
			TelaLogin telaLogin = new TelaLogin();
			telaLogin.metodoJanelaLogin();
			janelaCadastroUsuario.setVisible(false);
		}
	}

}
