package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.AnimalDAO;
import com.supervet.dao.ClienteDAO;
import com.supervet.dao.EnderecoDAO;
import com.supervet.dao.PessoaDAO;
import com.supervet.model.Animal;
import com.supervet.model.Cliente;
import com.supervet.model.Endereco;
import com.supervet.model.Pessoa;

public class CriarCliente implements CommandInterface {

    private AnimalDAO animalDAO = new AnimalDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        try {
            if (req.getParameter("acao") != null && req.getParameter("acao").equals("cadastrar")) {
                
            	String nome_cliente = req.getParameter("nome");
            	String email = req.getParameter("email");
            	String cpf = req.getParameter("cpf");      
                String telefone = req.getParameter("telefone");
                
                Pessoa pessoa = new Pessoa(nome_cliente, email, cpf, telefone);
                
                String cep = req.getParameter("cep");
            	String logradouro = req.getParameter("logradouro");
            	String complemento = req.getParameter("complemento");
                String bairro = req.getParameter("bairro");
                String cidade = req.getParameter("cidade");
                String uf = req.getParameter("uf");
                int numero = Integer.parseInt(req.getParameter("numero"));
                
                Endereco endereco = new Endereco(cep, logradouro, complemento, bairro, cidade, uf, numero);
                pessoa.setEndereco(endereco);
                
                int id_pessoa = pessoaDAO.adicionar(pessoa);
                endereco.setId_pessoa(id_pessoa);
                
                enderecoDAO.adicionar(endereco);            	              
                
                Cliente cliente = new Cliente(String.valueOf("1010" + id_pessoa), id_pessoa); 
                int id_cliente = clienteDAO.adicionar(cliente);
                
            	String raca = req.getParameter("raca");
            	int tipo = Integer.valueOf(req.getParameter("tipo"));
            	String nome = req.getParameter("nome_pet");
                int idade = Integer.parseInt(req.getParameter("idade"));
                String sexo = req.getParameter("sexo");
                int peso = Integer.parseInt(req.getParameter("peso"));                
                
                Animal animail = new Animal(raca.trim(), tipo, nome.trim(), idade, sexo, peso, id_cliente);                
                animalDAO.adicionar(animail);
                
                req.setAttribute("mensagem", "Cliente cadastrado com sucesso.");
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/home.jsp");
                rd.forward(req, res);                
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/clientes/criar.jsp");
                rd.forward(req, res);
            }
        } catch (SQLException | ClassNotFoundException | ServletException | IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

    }
}
