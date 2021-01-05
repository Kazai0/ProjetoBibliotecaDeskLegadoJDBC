package br.com.model;

public class PesquisarModel {

	
	private static String pesquisarRemover ;


	public String getPesquisarRemover() {
		return pesquisarRemover;
	}

	public void setPesquisarRemover(String pesquisarRemover) {
		this.pesquisarRemover = pesquisarRemover;
	}

	
	private int idLivro;

	private String nomeLivroE;
	private String dataPublicaoE;
	private int qtdLivrosE;
	private String nomeAutorE;
	private String generoLivroR;
	private String alugado;

	public String getAlugado() {
		return alugado;
	}

	public void setAlugado(String alugado) {
		this.alugado = alugado;
	}

	public String getNomeLivro()

	{
		return nomeLivroE;
	}

	public int getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivroE = nomeLivro;
	}

	public String getDataPublicao() {
		return dataPublicaoE;
	}

	public void setDataPublicao(String dataPublicao) {
		this.dataPublicaoE = dataPublicao;
	}

	public int getQtdLivros() {
		return qtdLivrosE;
	}

	public void setQtdLivros(int qtdLivros) {
		this.qtdLivrosE = qtdLivros;
	}

	public String getNomeAutor() {
		return nomeAutorE;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutorE = nomeAutor;
	}

	public String getGeneroLivro() {
		return generoLivroR;
	}

	public void setGeneroLivro(String generoLivro) {
		this.generoLivroR = generoLivro;
	}
	
	
	
}
