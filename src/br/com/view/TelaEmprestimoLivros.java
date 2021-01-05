package br.com.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

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
import br.com.control.EmprestimoLivrosDao;

import br.com.model.CadastroEmprestimoModel;
import br.com.model.IdsImportantesModel;
import br.com.model.LivrosCadastroModel;
import br.com.model.LivrosEmprestimoModelTabela;
import br.com.model.PesquisarModel;

public class TelaEmprestimoLivros extends JFrame {

	public static TelaEmprestimoLivros instance = null;

	private JTable tabela = new JTable();

	private JTextField txtPesquisar = new JTextField();
	private JTextField txtdataEntrada = new JTextField("##/##/20##");
	private JTextField txtdataSaida = new JTextField("##/##/20##");
	private JTextField quantidadeDoLivro = new JTextField();
	private JTextField generoDoLivro = new JTextField();
	private JTextField nomeDoAutor = new JTextField();

	private JButton btnAlugar = new JButton("Pegar o livro");
	private JButton btnVoltar = new JButton("Voltar");
	private JButton btnPesquisar = new JButton("Pesquisar");

	JPopupMenu menuMouser = new JPopupMenu();
	JMenuItem excluir = new JMenuItem("Excluir");

	// --Criação do Método Construtor--//

	public void TelaEmprestimo() {

		instance = this;

		configuracaoJanela();
		configuracaoComponentes();
		configurarTabela();
		eventosJanela();

		readJtable();

	}

	/*
	 * Criação do objeto do DefaultTableModel-- -- Chamada do método,
	 * SetNumRows para as linhas,-- -- Chamado do objeto lDao da classe
	 * EmprestimoLivrosDao-- -- Seguindo criação de um laço para mostrar as os
	 * Atributos do banco de dados através de cada linha-- -- chamada do objeto
	 * lDao e seus atributos--
	 */

	public void readJtable() {

		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		EmprestimoLivrosDao lDao = new EmprestimoLivrosDao();

		for (LivrosEmprestimoModelTabela l : lDao.read()) {

			modelo.addRow(new Object[] { l.getIdLivro(), l.getNomeLivro(),
					l.getQtdLivros(), l.getGeneroLivro(), l.getNomeAutor(),
					l.getAlugado() });

		}
	}
	
	
	public void readJtablePesquisar() {
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		EmprestimoLivrosDao lDao = new EmprestimoLivrosDao();

		for (LivrosEmprestimoModelTabela l : lDao.readPesquisar("oi")) {

			modelo.addRow(new Object[] { l.getIdLivro(), l.getNomeLivro(),
					l.getQtdLivros(), l.getGeneroLivro(), l.getNomeAutor(),
					l.getAlugado() });

		}
		
	}

	//Regra de négócios está aqui, porém devo aloca-la para uma classe Service ou DAO neste caso.
	private void eventosJanela() {
		this.btnAlugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				/*
				 * Criação o objetos das classes: BDEmprestimos,
				 * CadastroEmprestimoModel, BDAlugar, IDsImportantes
				 */

				BDEmprestimo emprestimo = new BDEmprestimo();
				CadastroEmprestimoModel caE = new CadastroEmprestimoModel();
				BDAlugar bA = new BDAlugar();
				IdsImportantesModel idf = new IdsImportantesModel();

				CadastroEmprestimoModel cadastroModel = new CadastroEmprestimoModel();
				IdsImportantesModel idF = new IdsImportantesModel();

				// este métdo getValeuAt obtera a linha que selecionei e o
				// metodo getSelect irá pega a o valor da coluna.

				int idNumeroLinha = (int) tabela.getValueAt(
						tabela.getSelectedRow(), 0);

				// declarando objetos das classes

				// Quebrando as Strings

				String diaEntrada = txtdataEntrada.getText().substring(0, 2);
				String mesEntrada = txtdataEntrada.getText().substring(3, 5);
				String anoEntrada = txtdataEntrada.getText().substring(6);

				String diaSaida = txtdataSaida.getText().substring(0, 2);
				String mesSaida = txtdataSaida.getText().substring(3, 5);
				String anoSaida = txtdataSaida.getText().substring(6);

				// setando os atributos da classe CadastroEmprestimo model para
				// utilizar elas dentro do banco de dados

				cadastroModel.setData_emprestimo(anoSaida + "-" + mesSaida
						+ "-" + diaSaida);
				cadastroModel.setData_devolucao(anoEntrada + "-" + mesEntrada
						+ "-" + diaEntrada);

				// Utilizando o objeto do cadastroModel, para indentificar o
				// #idFuncionario e converter o texto do #txtIdLivro em inteiro

				cadastroModel.setIdFuncinario(idF.getIdCliente());

				cadastroModel.setIdLivro(idNumeroLinha);

				int idLivro = caE.getIdLivro();

				emprestimo.metodoVericaTbEmprestimo(idLivro);

				System.out.println(emprestimo.isVerificaIdsUso());

				if (emprestimo.metodoVericaTbEmprestimo(idLivro) == false) {

					emprestimo.fazendoEmprestimo();

					int idFuncionario = idf.getIdFuncionario();

					bA.metodoAtulizandoAlugar(idFuncionario);

				} else {
					JOptionPane mensagem = new JOptionPane();
					mensagem.showMessageDialog(null,
							"esse livro já foi alugado por alguém");
				}

				System.out.println("oi");

			}
		});

		this.btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PesquisarModel pesquisarRemoverModel = new PesquisarModel();

				pesquisarRemoverModel.setPesquisarRemover(txtPesquisar.getText());

				System.out.println(pesquisarRemoverModel.getPesquisarRemover());
				
				String teste = pesquisarRemoverModel.getPesquisarRemover();
			
				readJtablePesquisar();

			}

		});

		this.btnVoltar.addActionListener(new ActionListener() {
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
	}// FIM EVENTOSJANELA

	private void inserirElemento() {
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.addRow(new Object[] { txtPesquisar.getText(),
				quantidadeDoLivro.getText(), generoDoLivro.getText(),
				nomeDoAutor.getText() });
	}

	private void configurarTabela() {
		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Id do livro");
		modelo.addColumn("Nome do livro");
		modelo.addColumn("Quantidade");
		modelo.addColumn("Genero do livro");
		modelo.addColumn("Nome do Autor");
		modelo.addColumn("Alugado");
		tabela.setModel(modelo);
	}

	/*
	 * configuracaoComponentes contenpla: Criação do Painel Setar definições de
	 * layout dos paineis West & Down Método format do objeto SimpleDate irá
	 * Setar data de entrada & saída data atual com o método currentTimeMillis
	 * Método BorderLayout ira ser resposável por dimensionar a posição dos
	 * paineis no Jframe
	 */

	private void configuracaoComponentes() {

		JPanel painelWest = new JPanel();
		JPanel painelDown = new JPanel();

		painelWest.setLayout(new GridLayout(14, 14));
		painelDown.setLayout(new GridLayout(8, 1));

		painelWest.setBorder(BorderFactory.createTitledBorder("Opções"));
		painelDown.setBorder(BorderFactory.createTitledBorder("alugarLivros"));

		txtdataEntrada.setText(new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date(System.currentTimeMillis())));

		txtdataSaida.setText(new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date(System.currentTimeMillis())));

		painelWest.add(new JLabel("Pesquisar"));
		painelWest.add(txtPesquisar);
		painelWest.add(new JLabel(""));
		painelWest.add(btnPesquisar);

		painelDown.add(new JLabel("data de hoje"));
		painelDown.add(txtdataEntrada);
		painelDown.add(new JLabel("data de entrega"));
		painelDown.add(txtdataSaida);

		painelDown.add(btnAlugar);
		painelDown.add(new JLabel(""));

		painelDown.add(btnVoltar);
		btnVoltar.setText("Voltar");

		add(BorderLayout.SOUTH, painelDown);
		add(BorderLayout.WEST, painelWest);
		add(BorderLayout.CENTER, new JScrollPane(tabela));

		tabela.setComponentPopupMenu(menuMouser);
	}

	/*
	 * Configurando as dimensões e funcionalidades do Jframe
	 */

	private void configuracaoJanela() {
		setTitle("Biblioteca DATACOM");
		setBounds(450, 200, 720, 580);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
	}
}
