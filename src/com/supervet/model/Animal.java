package com.supervet.model;

public class Animal {

	private int id_animal;
	private String raca;
	private int tipo;
	private String nome;
	private int idade;
	private String sexo;
	private int peso;
	private int id_cliente;

	public Animal() {
		// TODO Auto-generated constructor stub
	}	
	
	public Animal(String raca, int tipo, String nome, int idade, String sexo, int peso, int id_cliente) {
		super();		
		this.raca = raca;
		this.tipo = tipo;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.peso = peso;
		this.id_cliente = id_cliente;
	}

	public Animal(int id_animal, String raca, int tipo, String nome, int idade, String sexo, int peso, int id_cliente) {
		super();
		this.id_animal = id_animal;
		this.raca = raca;
		this.tipo = tipo;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.peso = peso;
		this.id_cliente = id_cliente;
	}

	public int getId_animal() {
		return id_animal;
	}

	public void setId_animal(int id_animal) {
		this.id_animal = id_animal;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	
}
