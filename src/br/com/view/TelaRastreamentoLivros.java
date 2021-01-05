package br.com.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.control.BDAlugar;
import br.com.control.BDCadastro;
import br.com.control.BDEmprestimo;
import br.com.control.BDLivrosFuncionariosRastreamento;
import br.com.control.BDRemoverLivros;
import br.com.control.EmprestimoLivrosDao;

import br.com.model.CadastroEmprestimoModel;
import br.com.model.IdsImportantesModel;
import br.com.model.LivrosCadastroModel;
import br.com.model.LivrosEmprestimoModelTabela;
import br.com.model.LivrosFuncionariosRastreamentoModel;

public class TelaRastreamentoLivros extends JFrame {

	public static TelaRastreamentoLivros instance = null;

	static private JTable tabela = new JTable();
	static private JTextField txtNomeUsuario = new JTextField();

	static private JTextField txtTitutoLivro = new JTextField();
	static private JTextField txtDataEmprestimo = new JTextField();
	static private JTextField txtDataEntrega = new JTextField();

	static private JButton btnPesquisar = new JButton("pesquisar");
	static private JButton voltar = new JButton("Voltar");

	public void telaRastreamentoLivros() {

		instance = this;

		configuracaoJanela();
		configuracaoComponentes();
		configurarTabela();
		eventosJanela();

		readJtable();

	}

	public void readJtable() {
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		BDLivrosFuncionariosRastreamento rastreamentoDao = new BDLivrosFuncionariosRastreamento();

		for (LivrosFuncionariosRastreamentoModel l : rastreamentoDao.read()) {

			modelo.addRow(new Object[] { l.getNome(), l.getLivro(),
					l.getDataEmprestimo(), l.getDataDevolucao() });

		}
	}
	//Regras de negócios não devem estar na classe View
	private void eventosJanela() {
		this.btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				/*
				 * Declarando Objetos
				 */

				CadastroEmprestimoModel cadastroModel = new CadastroEmprestimoModel();
				IdsImportantesModel idF = new IdsImportantesModel();

				BDEmprestimo emprestimo = new BDEmprestimo();
				CadastroEmprestimoModel caE = new CadastroEmprestimoModel();
				BDAlugar bA = new BDAlugar();
				IdsImportantesModel idf = new IdsImportantesModel();

				BDRemoverLivros rLivros = new BDRemoverLivros();

				int idLivro = caE.getIdLivro();

				emprestimo.metodoVericaTbEmprestimo(idLivro);

				int idlivrosRemover;

				idlivrosRemover = Integer.parseInt(txtNomeUsuario.getText());

				idF.setIdLivroRemove(idlivrosRemover);

				rLivros.metodoRemoverLivro(idLivro);

				System.out.println("oi");

			}
		});

		this.voltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String action;
				action = e.getActionCommand();
				switch (action) {
				case "Voltar":

					dispose();

					TelaMenu telaMenu = new TelaMenu();
					telaMenu.metodoJanelaMenu();

					break;
				}
			}
		});
	}

	private void configurarTabela() {
		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Nome Usuario");
		modelo.addColumn("Titulo Livro");
		modelo.addColumn("Data de Emprestimo");
		modelo.addColumn("Data devolu��o");

		tabela.setModel(modelo);
	}

	private void configuracaoComponentes() {
		JPanel painelWest = new JPanel();
		painelWest.setLayout(new GridLayout(14, 1));

		painelWest.setBorder(BorderFactory.createTitledBorder("Opções"));

		painelWest.add(new JLabel("Pesquisar"));
		painelWest.add(txtNomeUsuario);

		painelWest.add(new JLabel(""));

		painelWest.add(btnPesquisar);
		painelWest.add(new JLabel(""));

		painelWest.add(voltar);
		voltar.setText("Voltar");

		
		add(BorderLayout.WEST, painelWest);
		add(BorderLayout.CENTER, new JScrollPane(tabela));

	}

	private void configuracaoJanela() {
		setTitle("Biblioteca DATACOM");
		setBounds(450, 200, 650, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
	}
}
