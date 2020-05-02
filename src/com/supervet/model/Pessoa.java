package com.supervet.model;

public class Pessoa {

	private int id_pessoa;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	private Endereco endereco;
	
	public Pessoa() {

	}	
	
	public Pessoa(int id_pessoa, String nome, String email, String cpf, String telefone, Endereco endereco) {
		super();
		this.id_pessoa = id_pessoa;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Pessoa(String nome, String email, String cpf, String telefone) {	
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
	}

	public Pessoa(String nome, String email, String cpf, String telefone, Endereco endereco) {		
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
	}	
	
	public int getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
