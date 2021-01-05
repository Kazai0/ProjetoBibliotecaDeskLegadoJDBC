package br.com.model;

public class IdsImportantesModel {
	static public int idLivroRemove;

	static public int IdFuncionario;

	static public int IdLivros;
	
	
	static public int idLivroDevolve;
	
	
	
	
	

	public static int getIdLivroDevolve() {
		return idLivroDevolve;
	}

	public static void setIdLivroDevolve(int idLivroDevolve) {
		IdsImportantesModel.idLivroDevolve = idLivroDevolve;
	}

	public static int getIdLivroRemove() {
		return idLivroRemove;
	}

	public static void setIdLivroRemove(int idLivroRemove) {
		IdsImportantesModel.idLivroRemove = idLivroRemove;
	}

	public int getIdCliente() {
		return IdFuncionario;
	}

	public void setIdCliente(int idCliente) {
		IdFuncionario = idCliente;
	}

	public static int getIdFuncionario() {
		return IdFuncionario;
	}

	public static void setIdFuncionario(int idFuncionario) {
		IdFuncionario = idFuncionario;
	}

	public static int getIdLivros() {
		return IdLivros;
	}

	public static void setIdLivros(int idLivros) {
		IdLivros = idLivros;
	}

}
