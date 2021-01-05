package br.com.view;

import javax.swing.*;

import br.com.control.BDAlugar;
import br.com.control.BDDevolucao;
import br.com.control.BDLogin;
import br.com.model.IdsImportantesModel;
import br.com.model.LoginModel;
import br.com.model.LoginModel;

import java.awt.Desktop;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class TelaMenu implements ActionListener {

	public static TelaMenu instance = null;

	IdsImportantesModel idf2 = new IdsImportantesModel();

	// criação dos objetos3
	static JFrame janelaMenu = new JFrame("Menu Biblioteca Datacom");
	static JPanel painelMenu = new JPanel();

	// criação dos objetos botoes
	static JButton btnEmprestimo = new JButton();
	static JButton btnDevolucao = new JButton();
	static JButton btnAdicionarlivros = new JButton();
	static JButton btnRemoverlivros = new JButton();
	static JButton btnRastreamento = new JButton();
	static JButton btnUsuarios = new JButton();

	// criação dos label
	static JLabel lblEmprestimo = new JLabel();
	static JLabel lblDevolucao = new JLabel();
	static JLabel lblAdicionarlivros = new JLabel();
	static JLabel lblRemoverlivros = new JLabel();

	// caixa de texto
	static JOptionPane caixaTexto = new JOptionPane();

	// banco de dados
	static BDLogin bdLogin = new BDLogin();
	static LoginModel modeloLogin = new LoginModel();

	public void metodoJanelaMenu() {

		instance = this;

		// setando os tamanhos da janela de login
		janelaMenu.setBounds(450, 200, 460, 330);
		janelaMenu.setVisible(true);

		// setando o tamanho do layout default
		painelMenu.setLayout(null);

		// setando txt's e lbl's
		lblEmprestimo.setBounds(85, 80, 120, 20);
		lblEmprestimo.setText("Emprestimo");

		lblDevolucao.setBounds(240, 80, 120, 20);
		lblDevolucao.setText("Devolução");

		lblAdicionarlivros.setBounds(73, 175, 120, 20);
		lblAdicionarlivros.setText("Adicionar Livros");

		lblRemoverlivros.setBounds(226, 175, 120, 20);
		lblRemoverlivros.setText("Remover Livros");

		// setando botão de Usuarios

		btnUsuarios.addActionListener(this);
		btnUsuarios.setActionCommand("Usuarios");
		btnUsuarios.setBounds(230, 200, 100, 30);
		btnUsuarios.setText("usuarios");

		// setando botão de RastreamentoLivros

		btnRastreamento.addActionListener(this);
		btnRastreamento.setActionCommand("Rastreamento");
		btnRastreamento.setBounds(80, 200, 100, 30);
		btnRastreamento.setText("Rastro");

		// setando botao Emprestimo
		btnEmprestimo.addActionListener(this);
		btnEmprestimo.setActionCommand("Emprestimo");
		btnEmprestimo.setBounds(80, 30, 100, 50);

		// setando botao Devolucao
		btnDevolucao.addActionListener(this);
		btnDevolucao.setActionCommand("Devolucao");
		btnDevolucao.setBounds(230, 30, 100, 50);

		// setando botao Adicionarlivros
		btnAdicionarlivros.addActionListener(this);
		btnAdicionarlivros.setActionCommand("Adicionar Livros");
		btnAdicionarlivros.setBounds(80, 125, 100, 50);

		// setando botao RemoverLivros
		btnRemoverlivros.addActionListener(this);
		btnRemoverlivros.setActionCommand("Remover Livros");
		btnRemoverlivros.setBounds(230, 125, 100, 50);

		// setando os componentes da tabela
		janelaMenu.add(painelMenu);

		painelMenu.add(lblDevolucao);
		painelMenu.add(lblEmprestimo);

		// Está regra de negócios está horrivel e precisa ser melhorada e tirada
		// desta camada

		int id = 0;

		id = idf2.IdFuncionario;

		if (id == 20 || id == 3 || id == 347) {

			painelMenu.add(btnAdicionarlivros);
			painelMenu.add(btnRemoverlivros);
			painelMenu.add(lblAdicionarlivros);
			painelMenu.add(lblRemoverlivros);
			painelMenu.add(btnRastreamento);
			painelMenu.add(btnUsuarios);

		}

		painelMenu.add(btnEmprestimo);
		painelMenu.add(btnDevolucao);

	}

	public void actionPerformed(ActionEvent e) {
		String action;
		action = e.getActionCommand();

		switch (action) {
		case "Emprestimo":

			System.out.println("ate aquuiv ai");

			if (TelaEmprestimoLivros.instance != null) {

				TelaEmprestimoLivros.instance.setVisible(true);

			}

			else {
				System.out.println("else?");

				TelaEmprestimoLivros tE = new TelaEmprestimoLivros();
				tE.TelaEmprestimo();

			}

			janelaMenu.dispose();

			break;
		case "Devolucao":

			if (TelaDevolucao.instance != null) {

				TelaDevolucao.instance.setVisible(true);

			}

			else {
				System.out.println("else?");

				TelaDevolucao tD = new TelaDevolucao();
				tD.TelaDevolver();

			}

			janelaMenu.dispose();

			break;

		case "Adicionar Livros":

			if (TelaLivrosCadastro.instance != null) {

				TelaLivrosCadastro.instance.janelaCadastroLivros
						.setVisible(true);

			}

			else {
				System.out.println("else?");

				TelaLivrosCadastro tLC = new TelaLivrosCadastro();
				tLC.cadastroLivros();

			}

			janelaMenu.dispose();
			break;

		case "Remover Livros":

			if (TelaRemoverLivros.instance != null) {

				TelaRemoverLivros.instance.setVisible(true);

			}

			else {
				System.out.println("else?");

				TelaRemoverLivros tR = new TelaRemoverLivros();
				tR.TelaRemover();

			}

			janelaMenu.dispose();

			break;

		case "Rastreamento":
			if (TelaRastreamentoLivros.instance != null) {

				TelaRastreamentoLivros.instance.setVisible(true);

			}

			else {
				System.out.println("else?");

				TelaRastreamentoLivros tR = new TelaRastreamentoLivros();
				tR.telaRastreamentoLivros();

			}

			break;

		case "Usuarios":
			if (TelaConsultaUsuarios.instance != null) {
				TelaConsultaUsuarios.instance.setVisible(true);
			}

			else {
				TelaConsultaUsuarios tCt = new TelaConsultaUsuarios();
				tCt.telaConsultarUsuarios();
			}

			break;

		}

	}

}