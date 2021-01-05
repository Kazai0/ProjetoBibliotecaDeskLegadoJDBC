package br.com.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.control.BDConsultaUsuarios;
import br.com.model.IdsImportantesModel;
import br.com.model.PesquisarModel;
import br.com.model.UsuariosModel;

public class TelaConsultaUsuarios extends JFrame {

	public static TelaConsultaUsuarios instance = null;

	// instanciando objetos

	static private JTable tabela = new JTable();

	static private JTextField txtUsuarioPesquisar = new JTextField();

	static private JButton btnPesquisar = new JButton("pesquisar");
	static private JButton voltar = new JButton("Voltar");
	static private JButton btnExcluirUsuario = new JButton("Excluir");

	// método Construtor

	public void telaConsultarUsuarios() {
		instance = this;

		configuracaoJanela();
		configuracaoComponentes();
		configurarTabela();
		eventosJanela();

		readJtable();

	}

	// método para fazer leitura da tabela

	public void readJtable() {
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);

		BDConsultaUsuarios consultaUsuariosDB = new BDConsultaUsuarios();

		for (UsuariosModel l : consultaUsuariosDB.read()) {

			modelo.addRow(new Object[] { l.getId_funcionario(), l.getNome(),
					l.getEmail(), l.getTelefone() });

		}

	}

	public void readJtablePesquisar() {
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);

		BDConsultaUsuarios consultarUsuariosBD = new BDConsultaUsuarios();

		for (UsuariosModel l : consultarUsuariosBD.ReadPesquisar("Deus é bom")) {
			modelo.addRow(new Object[] { l.getId_funcionario(), l.getNome(),
					l.getEmail(), l.getTelefone() });
		}

	}

	private void eventosJanela() {
		this.btnPesquisar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				UsuariosModel usuariosModelPesquisa = new UsuariosModel();

				usuariosModelPesquisa.setPesquisarUsuarios(txtUsuarioPesquisar
						.getText());

				String teste = usuariosModelPesquisa.getPesquisarUsuarios();
				System.out.println(teste);

				readJtablePesquisar();

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

		this.btnExcluirUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String action;
				action = e.getActionCommand();
				switch (action) {
				case "Excluir":

					BDConsultaUsuarios bdConsultaExclui = new BDConsultaUsuarios();
					IdsImportantesModel iDI = new IdsImportantesModel();

					System.out.println("btn exluir");

					int idNumeroLinha = (int) tabela.getValueAt(
							tabela.getSelectedRow(), 0);

					System.out.println(idNumeroLinha);
					iDI.setIdFuncionario(idNumeroLinha);

					bdConsultaExclui.metodoRemoverUsuarios();

					break;
				}
			}
		});

	}

	private void configurarTabela() {
		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Id Usuario");
		modelo.addColumn("Nome Usuario");
		modelo.addColumn("Email Usuario");
		modelo.addColumn("Telefone Usuario");

		tabela.setModel(modelo);
	}

	private void configuracaoComponentes() {
		JPanel painelWest = new JPanel();
		painelWest.setLayout(new GridLayout(14, 1));

		painelWest.setBorder(BorderFactory.createTitledBorder("Opções"));

		painelWest.add(new JLabel("Pesquisar"));
		painelWest.add(txtUsuarioPesquisar);

		painelWest.add(new JLabel(""));

		painelWest.add(btnPesquisar);
		painelWest.add(new JLabel(""));

		painelWest.add(btnExcluirUsuario);
		painelWest.add(new JLabel(""));

		painelWest.add(voltar);
		voltar.setText("Voltar");

		
		add(BorderLayout.WEST, painelWest);
		add(BorderLayout.CENTER, new JScrollPane(tabela));

		
	}

	private void configuracaoJanela() {
		setTitle("Consulta Usuarios");
		setBounds(450, 200, 650, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));

	}

}
