package com.supervet.model;

public class Funcionario extends Pessoa {

	private int id_funcionario;
	private String cargo;
	private String senha;
	private int id_pessoa;
	
	public Funcionario() {
		// TODO Auto-generated constructor stub
	}
	
	public Funcionario(int id_funcionario, String cargo, String senha, int id_pessoa) {		
		this.id_funcionario = id_funcionario;
		this.cargo = cargo;
		this.senha = senha;
		this.id_pessoa = id_pessoa;
	}

	public Funcionario(int id_funcionario, String nome, String email, String cpf, String telefone, String cargo,
			String senha) {
		super(nome, email, cpf, telefone);
		this.id_funcionario = id_funcionario;
		this.cargo = cargo;
		this.senha = senha;
	}

	public Funcionario(int id_funcionario, String nome, String email, String cpf, String telefone, Endereco endereco, String senha, String cargo) {
		super(nome, email, cpf, telefone, endereco);
		this.id_funcionario = id_funcionario;		
		this.senha = senha;
		this.cargo = cargo;
	}

	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
	
	
	
}
