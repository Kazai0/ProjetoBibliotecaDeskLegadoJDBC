package br.com.model;

public class CadastroEmprestimoModel {

	static private String data_emprestimo;
	static private String data_devolucao;
	static private int IdFuncinario;
	static private int idLivro;

	public static String getData_emprestimo() {
		return data_emprestimo;
	}

	public static void setData_emprestimo(String data_emprestimo) {
		CadastroEmprestimoModel.data_emprestimo = data_emprestimo;
	}

	public static String getData_devolucao() {
		return data_devolucao;
	}

	public static void setData_devolucao(String data_devolucao) {
		CadastroEmprestimoModel.data_devolucao = data_devolucao;
	}

	public static int getIdFuncinario() {
		return IdFuncinario;
	}

	public static void setIdFuncinario(int idFuncinario) {
		IdFuncinario = idFuncinario;
	}

	public static int getIdLivro() {
		return idLivro;
	}

	public static void setIdLivro(int idLivro) {
		CadastroEmprestimoModel.idLivro = idLivro;
	}

}
