package com.supervet.model;

public class Cliente extends Pessoa {

	private int id_cliente;
	private String matricula;
	private int id_pessoa;
	
	private Animal animal;
	
	public Cliente() {
		animal = new Animal();
	}

	public Cliente(int id_cliente, String matricula) {
		super();
		this.id_cliente = id_cliente;
		this.matricula = matricula;
	}
	
	public Cliente(String matricula, int id_pessoa) {
		super();
		this.matricula = matricula;
		this.id_pessoa = id_pessoa;
	}

	public Cliente(int id_cliente, String matricula, int id_pessoa) {		
		this.id_cliente = id_cliente;
		this.matricula = matricula;
		this.id_pessoa = id_pessoa;
	}
	
	public Cliente(int id_cliente, String nome, String email, String cpf, String telefone, Animal animal,
			String matricula) {
		super(nome, email, cpf, telefone);
		this.id_cliente = id_cliente;
		this.matricula = matricula;
		this.animal = animal;
	}
	
	public Cliente(int id_cliente, String nome, String email, String cpf, String telefone, Endereco endereco,
			String matricula) {
		super(nome, email, cpf, telefone, endereco);
		this.id_cliente = id_cliente;
		this.matricula = matricula;
	}
	
	public Cliente(int id_cliente, String nome, String email, String cpf, String telefone, String matricula) {
		super(nome, email, cpf, telefone);
		this.id_cliente = id_cliente;
		this.matricula = matricula;
	}
	
	public Cliente(int id_cliente, String nome, String email, String cpf, String telefone, String matricula, int id_pessoa) {
		super(nome, email, cpf, telefone);
		this.id_cliente = id_cliente;
		this.matricula = matricula;
		this.id_pessoa = id_pessoa;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	
}
