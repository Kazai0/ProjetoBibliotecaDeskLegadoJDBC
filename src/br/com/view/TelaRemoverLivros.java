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
import javax.swing.table.TableRowSorter;

import br.com.control.BDAlugar;
import br.com.control.BDCadastro;
import br.com.control.BDEmprestimo;
import br.com.control.BDPesquisar;
import br.com.control.BDRemoverLivros;
import br.com.control.EmprestimoLivrosDao;

import br.com.model.CadastroEmprestimoModel;
import br.com.model.IdsImportantesModel;
import br.com.model.LivrosCadastroModel;
import br.com.model.LivrosEmprestimoModelTabela;
import br.com.model.PesquisarModel;

public class TelaRemoverLivros extends JFrame {

	public static TelaRemoverLivros instance = null;

	static private JTable tabela = new JTable();
	private TableRowSorter t = new TableRowSorter<>();

	static private JTextField txtPesquisar = new JTextField();

	static private JTextField txtQuantidadeDoLivro = new JTextField();
	static private JTextField txtGeneroDoLivro = new JTextField();
	static private JTextField nomeDoAutor = new JTextField();

	static private JButton btnRemover = new JButton("remover");
	static private JButton btnVoltar = new JButton("Voltar");

	static private JButton btnPequisar = new JButton("Pesquisar");

	public void TelaRemover() {

		instance = this;

		configuracaoJanela();
		configuracaoComponentes();
		configurarTabela();
		eventosJanela();

		readJtable();

	}

	// método ira ler a tabela do banco de dados, através da classe de conexão e
	// model e mostra-la no view
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

		for (LivrosEmprestimoModelTabela l : lDao.readPesquisar("Deus � bom")) {

			modelo.addRow(new Object[] { l.getIdLivro(), l.getNomeLivro(),
					l.getQtdLivros(), l.getGeneroLivro(), l.getNomeAutor(),
					l.getAlugado() });

		}

	}

	private void eventosJanela() {
		this.btnRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// declarando objetos das classes

				CadastroEmprestimoModel cadastroModel = new CadastroEmprestimoModel();
				IdsImportantesModel idF = new IdsImportantesModel();
				BDEmprestimo emprestimo = new BDEmprestimo();
				CadastroEmprestimoModel caE = new CadastroEmprestimoModel();
				BDAlugar bA = new BDAlugar();
				IdsImportantesModel idf = new IdsImportantesModel();
				BDRemoverLivros rLivros = new BDRemoverLivros();

				// Quebrando as Strings

				// este métdo getValeuAt obtera a linha que selecionei e o
				// metodo getSelect pegará o valor da coluna e .

				int idNumeroLinha = (int) tabela.getValueAt(
						tabela.getSelectedRow(), 0);

				int idLivro = caE.getIdLivro();

				emprestimo.metodoVericaTbEmprestimo(idLivro);

				idF.setIdLivroRemove(idNumeroLinha);

				rLivros.metodoRemoverLivro(idLivro);

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

		this.btnPequisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String action;
				action = e.getActionCommand();

				PesquisarModel pesquisarRemoverModel = new PesquisarModel();

				pesquisarRemoverModel.setPesquisarRemover(txtPesquisar
						.getText());

				System.out.println(pesquisarRemoverModel.getPesquisarRemover());

				String teste = pesquisarRemoverModel.getPesquisarRemover();

				readJtablePesquisar();

			}
		});

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

		// Setando as definições de layout dos Paineis West & Down
		painelWest.setLayout(new GridLayout(14, 1));
		JPanel painelDown = new JPanel();
		painelDown.setLayout(new GridLayout(6, 1));

		// Escrevendo Strings na borda

		painelWest.setBorder(BorderFactory.createTitledBorder("Opções"));
		painelDown
				.setBorder(BorderFactory.createTitledBorder("Remover Livros"));

		// dimensionando tamanho dos componentes

		txtPesquisar.setSize(100, 110);

		painelWest.add(new JLabel("Pesquisar Livro"));
		painelWest.add(txtPesquisar);
		painelWest.add(new JLabel(""));

		painelWest.add(btnPequisar);

		painelDown.add(btnRemover);
		painelDown.add(new JLabel(""));

		painelDown.add(btnVoltar);
		btnVoltar.setText("Voltar");

		add(BorderLayout.SOUTH, painelDown);
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
