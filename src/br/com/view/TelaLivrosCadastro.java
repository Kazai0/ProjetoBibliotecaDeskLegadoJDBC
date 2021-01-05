package br.com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.control.BDCadastro;
import br.com.control.BDLivrosCadastro;
import br.com.control.BDLogin;
import br.com.model.CadastroModel;
import br.com.model.LivrosCadastroModel;

public class TelaLivrosCadastro implements ActionListener {
	
	public static TelaLivrosCadastro instance = null;


	static JFrame janelaCadastroLivros = new JFrame("Menu de Cadastro");
	static JPanel painelCadastroLivros = new JPanel();

	static JLabel label = new JLabel();

	static JTextField txtNomeLivro = new JTextField();
	static JTextField txtDataPublicao = new JTextField("##/##/####");
	static JTextField txtGenero = new JTextField();
	static JTextField txtAutor = new JTextField();
	static JTextField txtQuantidade = new JTextField();

	static JFormattedTextField vDate = new JFormattedTextField(
			DateFormat.getDateInstance(DateFormat.MEDIUM));
	static GregorianCalendar data = new GregorianCalendar();

	static JLabel lblNomeLivros = new JLabel();
	static JLabel lblDataPublicao = new JLabel();
	static JLabel lblGenero = new JLabel();
	static JLabel lblAutor = new JLabel();
	static JLabel lblQuanttdade = new JLabel();

	static JButton btnVoltar = new JButton();
	static JButton btnCadastrar = new JButton();

	static JOptionPane caixaMenu = new JOptionPane();

	static CadastroModel modeloCadastroView = new CadastroModel();

	BDLogin cadastrarFuncionarioBD = new BDLogin();

	public void cadastroLivros() {
		
		
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

		lblNomeLivros.setBounds(10, 10, 120, 20);
		lblNomeLivros.setText("Nome do livro:");

		lblDataPublicao.setBounds(10, 40, 120, 20);
		lblDataPublicao.setText("Data Publicação");

		lblGenero.setBounds(10, 70, 120, 20);
		lblGenero.setText("Genero");

		lblAutor.setBounds(10, 100, 120, 20);
		lblAutor.setText("Autor");

		lblQuanttdade.setBounds(10, 130, 120, 20);
		lblQuanttdade.setText("Quantidade");

		txtNomeLivro.setBounds(130, 10, 250, 25);

		

		txtDataPublicao.setBounds(130, 40, 250, 25);
		txtGenero.setBounds(130, 70, 250, 25);
		txtAutor.setBounds(130, 100, 250, 25);
		txtQuantidade.setBounds(130, 130, 250, 25);

		janelaCadastroLivros.setBounds(680, 400, 420, 250);
		painelCadastroLivros.setLayout(null);

		// adiconando componentes nos paineis;
		janelaCadastroLivros.add(painelCadastroLivros);

		painelCadastroLivros.add(lblNomeLivros);
		painelCadastroLivros.add(lblDataPublicao);
		painelCadastroLivros.add(lblGenero);
		painelCadastroLivros.add(lblAutor);
		painelCadastroLivros.add(lblQuanttdade);

		painelCadastroLivros.add(txtNomeLivro);
		painelCadastroLivros.add(txtDataPublicao);
		painelCadastroLivros.add(txtGenero);
		painelCadastroLivros.add(txtAutor);
		painelCadastroLivros.add(txtQuantidade);

		painelCadastroLivros.add(btnVoltar);
		painelCadastroLivros.add(btnCadastrar);

		janelaCadastroLivros.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		switch (action) {
		case "Cadastrar":
			
			System.out.println("cadastro");

			LivrosCadastroModel modelCadastro = new LivrosCadastroModel();

			String dia = txtDataPublicao.getText().substring(0, 2);
			String mes = txtDataPublicao.getText().substring(3, 5);
			String ano = txtDataPublicao.getText().substring(6);

			modelCadastro.setNomeLivro(txtNomeLivro.getText());
			
			modelCadastro.setGeneroLivro(txtGenero.getText());
			modelCadastro.setNomeAutor(txtAutor.getText());
			
			modelCadastro.setQtdLivros(Integer.parseInt(txtQuantidade.getText()));

			BDLivrosCadastro cadastroDatabase = new BDLivrosCadastro();
			cadastroDatabase.inserindoLivros();

			
		case "Voltar":
			
			System.out.println("cadastro");
			
			if (TelaMenu.instance != null) {
				
				TelaMenu.instance.janelaMenu.setVisible(true);
	
			}
			
			else {
				System.out.println("else?");
				
				TelaMenu tM = new  TelaMenu();
				tM.metodoJanelaMenu();
				
				
				
			}
			janelaCadastroLivros.dispose();
			

		}
	}

}
