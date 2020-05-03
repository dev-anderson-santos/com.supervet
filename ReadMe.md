<img src="https://github.com/dev-anderson-santos/com.supervet/blob/master/WebContent/img/logo_estacio.png">

# Projeto e Implementação Orientado a Objetos (NPG1401)

Estácio de Sá<br/>
Pós-Graduação em Engenharia de Software<br/>
Prof: Denis Gonçalves Cople<br/>
Aluno: Anderson Barbosa dos Santos<br/>
Matrícula: 202001547509

## Objetivo do Trabalho

O aluno deverá ser capaz de desenvolver um sistema que realize as operações CRUD e controle sessão de usuário para Web seguindo os padrões MVC II, Front Controller e Command.

## Competências / Habilidades :

  ► Utilização da Arquitetura MVC<br>
  ► Desenvolvimento de sistemas para Web<br>
  ► Desenvolvimento usando o padrão de projeto FrontController e Command<br>
  ► Utilização do controle de sessão<br>
  ► Utilização de Middleware para acesso ao banco de dados<br>
  ► Execução de consultas SQL através de uma linguagem de programação<br>


## Desenvolvimento:

<ul>
  <li>Tema será definido com o professor</li>
  <ul>
    <li>Tema Livre</li>
    <li>Arquitetura é obrigatória</li>
  </ul>
  <li>Definição do domínio do negócio.</li>
  <li>Modelo deverá possuir no mínimo:</li>
  <ul>
    <li>Uma herança de 3 níveis;</li>
    <li>Uma associação;</li>
  </ul>
  <li>Criação do Banco de Dados</li>
  <li>Criação das classes Beans</li>
  <li>Criação da camada de persistência do modelo MVCII</li>
  <li>Criar as classes DAO Genérico referentes à manipulação e consulta dos dados de cada entidade envolvida.</li>
  <li>É facultado o uso de JPA/Hibernate.</li>
  <li>Implemente os códigos para gerenciamento de sessão do sistema.</li>
  <li>Implemente os códigos para gerenciamento de login ao sistema.</li>
  <li>Criação do Servlet para a administração e para a para a exibição na Web (Front Controller) em conjunto com o padrão Command</li>
  <li>Criação dos JSPs necessários para a camada de visualização do Site e da administração.</li>
</ul>
 
## Produto / Resultado

<ul>
  <li>Aluno deverá implementar um CRUD utilizando os conhecimentos adquiridos no conteúdo da disciplina, aplicando as tecnologias ministradas durante o curso.</li>
  <li>Programa deverá ser acompanhado da descrição da aplicação implementada</li>
</ul>

# O projeto

Foi desenvolvido um pequeno controle de cadastro de funcionários, clientes e consultas para uma clínica veterinária.
Nele é possível que o cliente marque a consulta, assim como o médico marcá-la como atendida ou cancelar a mesma.

O padrão MVC foi utilizado no desenvolvimento como pode ser visto na disposição dos arquivos na imagem abaixo:

<img src="https://github.com/dev-anderson-santos/com.supervet/blob/master/WebContent/img/mvc.PNG">

Na imagem abaixo, é possível ver o desenvolvimento do Front-Controller utilizando um Servlet responsável por receber os comandos e direcionar para a rota correspondente. A interface CommandInterface fornece um contrato onde todas as rotas deverão tê-lo para concluir a ação implementada em cada uma.

<img src="https://github.com/dev-anderson-santos/com.supervet/blob/master/WebContent/img/command_front_servlet.PNG">


## Interface `COMMANDS`:

```
package com.supervet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandInterface {
    public void executar(HttpServletRequest req, HttpServletResponse res);
}
```
## `DAO Genérico` comum

```
package com.supervet.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

	public List<T> getAll() throws SQLException, ClassNotFoundException;
	public T getById(int id) throws SQLException, ClassNotFoundException;
	public void insert(T value) throws SQLException, ClassNotFoundException;
	public void edit(T value) throws SQLException, ClassNotFoundException;
	public void delete(int id) throws SQLException, ClassNotFoundException;	
	public int count() throws SQLException, ClassNotFoundException;	
	public int lastId() throws SQLException, ClassNotFoundException;
}

```
## Foi necessário criar outros métodos nas classes DAO devido a necessidade.

## Configuração do Banco de Dados - Middleware JDBC

Foi utilizaado o JDBC como middleware e é necessário configurar o arquivo `ConexaoJDBCFactory.java` apontando para o local correpondente ao banco de dados.<br/><br/>

```
package com.supervet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJDBCFactory {

    public static Connection getConexao() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3308/db_supervet?useTimezone=true&serverTimezone=UTC", "root", "");
        return conexao;
    }
    
    public static Connection getConexaoAux() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3308?useTimezone=true&serverTimezone=UTC", "root", "");
        return conexao;
    }
}

```

# Como Executar o Projeto

Ferramentass utilizadas:

 - [MySQL](https://dev.mysql.com/get/Downloads/MySQLInstaller/mysql-installer-community-5.7.30.0.msi)
 - [Apache Tomcat7](https://tomcat.apache.org/download-70.cgi)
 - [Eclipse IDE](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2020-03/R/eclipse-inst-win64.exe)
 - [Bootstrap](https://getbootstrap.com/docs/4.1/getting-started/introduction/)

## Download do projeto

<ol>
  <li>Faça o CLONE deste repositório.</li>
  <li>Faça download e instalação do Eclipse IDE no link sugerido em Ferramentass utilizadas.</li>
  <li>Faça download e instalação do MySQL no link sugerido em Ferramentass utilizadas.</li>
  <li>O conector JDBC (jar) já está adicionado neste projeto.</li>
  <li>Com o projeto aberto no Eclipse, clique com o botão direito do mouse se selecione: Run As -> Run on Server.</li>
  <li>Realize as configurações recomendadas.</li>
  <li>Após configurar as ferramentas acima, acesse: <http://localhost:8080/SuperVet/controlador?xpto=Start></li>
</ol>

