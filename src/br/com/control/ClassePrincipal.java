package br.com.control;

import br.com.conexao.ClasseDeConexaoBD;
import br.com.view.TelaLogin;

//Deus obrigado por todas as coisas

//classe principal para a chamada do ínico do código
public class ClassePrincipal extends TelaLogin {

	// método principal
	public static void main(String args[]) {

		BDLogin loginBd = new BDLogin();
		ClasseDeConexaoBD classeConexao = new ClasseDeConexaoBD();

		classeConexao.getConnection();

		// condição que classeConexão deve ser diferente de null para executar
		// tela de login
		// Ou seja a conexão com o banco de dados deve ser estar funcionando
		// atráves da classe de Conexão

		if (classeConexao.getConnection() != null) {

			loginBd.listaContatos();

			TelaLogin telaLogin = new TelaLogin();

			telaLogin.metodoJanelaLogin();
		}

		else {

		}

	}

}
