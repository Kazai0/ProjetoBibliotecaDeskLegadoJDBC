package br.com.model;

public class UsuariosModel {

	private  static String PesquisarUsuarios;

	public static String getPesquisarUsuarios() {
		return PesquisarUsuarios;
	}

	public static void setPesquisarUsuarios(String pesquisarUsuarios) {
		PesquisarUsuarios = pesquisarUsuarios;
	}

	private int id_funcionario;
	private String nome;
	private String email;
	private String telefone;



	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



}
