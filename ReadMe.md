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

  ► Utilização da Arquitetura MVC
  ► Desenvolvimento de sistemas para Web
  ► Desenvolvimento usando o padrão de projeto FrontController e Command
  ► Utilização do controle de sessão
  ► Utilização de Middleware para acesso ao banco de dados
  ► Execução de consultas SQL através de uma linguagem de programação


## Desenvolvimento:

<ul>
  <li>Tema será definido com o professor</li>
  <ul>
    <li>Tema Livre</li>
    <li>Arquitetura é obrigatória</li>
  </ul>
  <li>Definição do domínio do negócio.</li>
  <li>modelo deverá possuir no mínimo:</li>
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
  <li>programa deverá ser acompanhado da descrição da aplicação implementada</li>
</ul>

# Sobre o Projeto

O projeto foi desenvolvido utilizando:

 - [Eclipse IDE](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2020-03/R/eclipse-inst-win64.exe)
 - [SQLite](https://www.sqlite.org/download.html)
 - [Apache Tomcat7](https://tomcat.apache.org/download-70.cgi)
 - [Bootstrap 4.1](https://getbootstrap.com/docs/4.1/getting-started/introduction/)
 - [Datatable.net](https://datatables.net/)

Foi usado o padrão MVC `(Model-View-Controller)` no desenvolvimento do projeto. Os arquivos que representam as páginas WEB foram desenvolvidas utilizando a tecnologia de `JSP (JavaServer Pages)`, que fazem parte da camada View, sendo as páginas acessadas pelo usuário.

Para implementação do `Front-Controller` foi utilizada a tecnologia de `SERVLETS`, criando assim um controlador central capaz de gerir todas as requisições dos usuários e direcionalas as classes `COMMAND` estas que por sua vez implementam uma intergace padrão que fornece o contrato de implementação das mesmas.

Foram utilizadas `classes BEANS` para definição dos modelos, que contém as classes e arquivos relacionados ao banco de dados, estes são utilizados para a implementaçao e utilização das classes DAO qu também implementam interfaces padrão para o controle e manipulação das requisições do sistema as tabelas do banco de dados.

Para implementação da `interface` padrão das `classes DAO`, foi utilizada a tecnologia de `generics` para estender o sistema de tipos permitindo assim que a interface opere em objetos de vários tipos, fornecendo segurança do tipo em tempo de compilação.

A interface padrão dos `COMMANDS` segue o modelo:

```
package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FrontCommandInterface {
	public void execute(HttpServletRequest request, HttpServletResponse response);
}
```
A interface padrão as `Classes DAO` segue o modelo:

Tabelas

```
package dao;

import java.sql.SQLException;
import java.util.List;

public interface BasicTableDAO<T> {

	public List<T> getAll() throws SQLException, ClassNotFoundException;

	public T getById(int id) throws SQLException, ClassNotFoundException;

	public void insert(T value) throws SQLException, ClassNotFoundException;

	public void edit(T value) throws SQLException, ClassNotFoundException;

	public void delete(int id) throws SQLException, ClassNotFoundException;
	
	public int count() throws SQLException, ClassNotFoundException;
	
	public int lastId() throws SQLException, ClassNotFoundException;

}

```

Views

```
package dao;

import java.sql.SQLException;
import java.util.List;

public interface BasicViewDAO<T> {

	public List<T> getAll() throws SQLException, ClassNotFoundException;

	public T getById(int id) throws SQLException, ClassNotFoundException;

	public int count() throws SQLException, ClassNotFoundException;
	
	public int lastId() throws SQLException, ClassNotFoundException;

}
```

# Como Executar o Projeto

## Download do projeto

Faça o CLONE deste repositório para seu ambiente de preferência para a estrutura raiz dda unidade Ex: D:\ evitando assim uma hierarquia de pastas muito extensa.

Faça download do Eclipse IDE:

 - [Link de Download](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2020-03/R/eclipse-inst-win64.exe")
 
Faça Download das bibliotecas do SQLite: 
 
 - [Link de Download](https://www.sqlite.org/download.html)

Faça download do driver de conexão JDBC do SQLite

 - [Link de Download](https://bitbucket.org/xerial/sqlite-jdbc/downloads/)

## Configuração do Banco de Dados

Para iniciar o projeto, configure o arquivo `ConnectionFactory.java` apontando para o local correto do banco de dados.<br/><br/>
<i>Obs: Você pode alterar o driver JDBC para qualquer outro tipo de conexão, porém neste trabalho será utilizado o SQLite</i><br/>

```
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
    public static Connection getConexao() throws SQLException, ClassNotFoundException {
    	Class.forName("org.sqlite.JDBC");
        // Altere AQUI!
    	Connection sqliteConnection = DriverManager.getConnection("jdbc:sqlite:{CAMINHO PARA O BANCO DE DADOS}");
    	return (Connection) sqliteConnection;
    }
	
}
```