package com.supervet.model;

public class Administrador extends Funcionario {

	
	public Administrador() {

	}
	
	public Administrador(int id_funcionario, String nome, String email, String cpf, String telefone, Endereco endereco, String senha, String cargo) {		
		super(id_funcionario, nome, email, cpf, telefone, endereco, senha, cargo);
	}
}
