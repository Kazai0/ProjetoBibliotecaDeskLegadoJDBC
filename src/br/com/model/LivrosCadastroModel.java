package br.com.model;

public class LivrosCadastroModel {

	static private String nomeLivro;
	static private String dataPublicao;
	static private int qtdLivros;
	static private String nomeAutor;
	static private String generoLivro;

	public static String getNomeLivro() {
		return nomeLivro;
	}

	public static void setNomeLivro(String nomeLivro) {
		LivrosCadastroModel.nomeLivro = nomeLivro;
	}

	public static int getQtdLivros() {
		return qtdLivros;
	}

	public static void setQtdLivros(int qtdLivros) {
		LivrosCadastroModel.qtdLivros = qtdLivros;
	}

	public static String getDataPublicao() {
		return dataPublicao;
	}

	public static void setDataPublicao(String dataPublicao) {
		LivrosCadastroModel.dataPublicao = dataPublicao;
	}

	public static String getNomeAutor() {
		return nomeAutor;
	}

	public static void setNomeAutor(String nomeAutor) {
		LivrosCadastroModel.nomeAutor = nomeAutor;
	}

	public static String getGeneroLivro() {
		return generoLivro;
	}

	public static void setGeneroLivro(String generoLivro) {
		LivrosCadastroModel.generoLivro = generoLivro;
	}

}
