package com.supervet.model;

public class Consulta {

	private int id_consulta;
	private String data;
	private String hora;
	private String tipo;
	private int status; // ATENDIDO | NÃO ATENDIDO
	
	private Animal animal;
	private Funcionario funcionario;
	
	public Consulta() {
		
		this.data = new StringBuilder(data).reverse().toString();
		
		animal = new Animal();
		funcionario = new Funcionario();
	}

	public Consulta(String data, String hora, String tipo, int status) {
		super();
		
		this.data = new StringBuilder(data).reverse().toString();
		
		this.hora = hora;
		this.tipo = tipo;
		this.status = status;

		animal = new Animal();
		funcionario = new Funcionario();
	}
	
	public Consulta(String data, String hora, String tipo, int status, Animal animal) {
		super();		

		this.data = new StringBuilder(data).reverse().toString();
		this.hora = hora;
		this.tipo = tipo;
		this.status = status;
		this.animal = animal;
		
		funcionario = new Funcionario();
	}
	
	public Consulta(int id_consulta, String data, String hora, String tipo, int status) {
		super();
		this.id_consulta = id_consulta;
		this.data = new StringBuilder(data).reverse().toString();
		this.hora = hora;
		this.tipo = tipo;
		this.status = status;
	}

	public Consulta(int id_consulta, String data, String hora, String tipo, int status, Animal animal) {
		super();
		this.id_consulta = id_consulta;
		this.data = new StringBuilder(data).reverse().toString();
		this.hora = hora;
		this.tipo = tipo;
		this.status = status;
		this.animal = animal;
	}
	
	public Consulta(int id_consulta, String data, String hora, String tipo, int status, Animal animal, Funcionario funcionario) {
		super();
		this.id_consulta = id_consulta;
		this.data = new StringBuilder(data).reverse().toString();
		this.hora = hora;
		this.tipo = tipo;
		this.status = status;
		this.animal = animal;
		this.funcionario = funcionario;
	}

	public int getId_consulta() {
		return id_consulta;
	}

	public void setId_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}	
	
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Consulta [id_consulta=" + id_consulta + ", data=" + data + ", hora=" + hora + ", tipo=" + tipo
				+ ", status=" + status + ", Animal=" + animal + ", funcionario=" + funcionario + "]";
	}
	
	
}
