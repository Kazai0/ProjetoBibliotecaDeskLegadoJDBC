package br.com.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CadastroModel {

	static private String nome;

	static private String telefone;
	static private String email;
	static private String usuario;
	static private String senha;

	static private String dia;
	static private String mes;
	static private String ano;

	// data que vale realmente
	static private String dataNascimentoParaMysql;

	public static String getDataNascimentoParaMysql() {
		return dataNascimentoParaMysql;
	}

	public static void setDataNascimentoParaMysql(String dataNascimentoParaMysql) {
		CadastroModel.dataNascimentoParaMysql = dataNascimentoParaMysql;
	}

	public static String getDia() {
		return dia;
	}

	public static void setDia(String dia) {
		CadastroModel.dia = dia;
	}

	public static String getMes() {
		return mes;
	}

	public static void setMes(String mes) {
		CadastroModel.mes = mes;
	}

	public static String getAno() {
		return ano;
	}

	public static void setAno(String ano) {
		CadastroModel.ano = ano;
	}

	static public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public static String getTelefone() {
		return telefone;
	}

	public static void setTelefone(String telefone) {
		CadastroModel.telefone = telefone;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
