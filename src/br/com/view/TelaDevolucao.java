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
import br.com.control.BDDevolucao;
import br.com.control.BDEmprestimo;
import br.com.control.BDRemoverLivros;
import br.com.control.EmprestimoLivrosDao;
import br.com.model.CadastroEmprestimoModel;
import br.com.model.IdsImportantesModel;
import br.com.model.LivrosEmprestimoModelTabela;
import br.com.model.PesquisarModel;

public class TelaDevolucao extends JFrame {

	public static TelaDevolucao instance = null;

	static private JTable tabela = new JTable();

	static private JTextField txtPesquisar = new JTextField();

	static private JTextField quantidadeDoLivro = new JTextField();
	static private JTextField generoDoLivro = new JTextField();
	static private JTextField nomeDoAutor = new JTextField();

	static private JButton btnDevover = new JButton("Devolver");
	static private JButton btnVoltar = new JButton("Voltar");
	static private JButton btnPesquisar = new JButton("Voltar");

	static JPopupMenu menuMouser = new JPopupMenu();
	static JMenuItem excluir = new JMenuItem("Excluir");

	public void TelaDevolver() {

		instance = this;

		configuracaoJanela();
		configuracaoComponentes();
		configurarTabela();
		eventosJanela();
		readJtable();

	}// FIM DO CONSTRUTOR

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

		for (LivrosEmprestimoModelTabela l : lDao.readPesquisar("Deus eh bom")) {

			modelo.addRow(new Object[] { l.getIdLivro(), l.getNomeLivro(),
					l.getQtdLivros(), l.getGeneroLivro(), l.getNomeAutor(),
					l.getAlugado() });

		}

	}

	
	//Minha Regra de négócios está aqui porém deve alocada para uma camada de Service ou neste caso a DAO
	private void eventosJanela() {
		this.btnDevover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// declarando objetos das classes

				CadastroEmprestimoModel cadastroModel = new CadastroEmprestimoModel();
				IdsImportantesModel idF = new IdsImportantesModel();

				JOptionPane msgLivroDevolvido = new JOptionPane();
				BDDevolucao Bdev = new BDDevolucao();

				// ID do livro que vamos devolver

				int idNumeroLinha = (int) tabela.getValueAt(
						tabela.getSelectedRow(), 0);

				// setando o model dos IDs importantes com idDevolve

				idF.setIdLivroDevolve(idNumeroLinha);

				// chamando metodo que devolve livros da Classe BDdevolução

				int idFun = idF.getIdFuncionario();


				// O objeto idF irá setar o atributo idLivroDevolve,
				// com o valor txtLivro fazendo uma conversão de string para int

				Bdev.metodoDevolveLivro(idNumeroLinha);

				if (Bdev.metodoVerificaLivro(idNumeroLinha) == true) {

					Bdev.metodoDevolveLivro(idNumeroLinha);

					System.out.println("o metodo Verifica é falso");

				}

				else {

					System.out.println("voce nao pegou esse livro");

				}
			}
		});

		this.btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String action;
				action = e.getActionCommand();

				PesquisarModel pesquisarRemoverModel = new PesquisarModel();

				pesquisarRemoverModel.setPesquisarRemover(txtPesquisar
						.getText());

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
	}

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

	private void configuracaoComponentes() {
		
		// Criando o objeto Painel
		JPanel painelWest = new JPanel();
		painelWest.setLayout(new GridLayout(14, 1));

		JPanel painelDown = new JPanel();
		painelDown.setLayout(new GridLayout(6, 1));

		painelWest.setBorder(BorderFactory.createTitledBorder("Opções"));
		painelDown.setBorder(BorderFactory
				.createTitledBorder("Devolver Livros"));

		// dimensionando tamanho dos componentes

		IdsImportantesModel idf = new IdsImportantesModel();

		painelDown.add(btnDevover);
		btnDevover.setText("Devolver");
		painelDown.add(new JLabel(""));

		painelDown.add(btnVoltar);
		btnVoltar.setText("Voltar");

		painelWest.add(new JLabel(""));
		painelWest.add(txtPesquisar);
		painelWest.add(new JLabel(""));
		painelWest.add(btnPesquisar);

		btnPesquisar.setText("Pesquisar");

		add(BorderLayout.SOUTH, painelDown);
		add(BorderLayout.WEST, painelWest);
		add(BorderLayout.CENTER, new JScrollPane(tabela));
		
		tabela.setComponentPopupMenu(menuMouser);
	} 

	private void configuracaoJanela() {
		setTitle("Biblioteca DATACOM");
		setBounds(450, 200, 650, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
	}
}
