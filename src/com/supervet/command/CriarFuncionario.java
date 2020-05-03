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
import com.supervet.dao.ConsultaDAO;
import com.supervet.dao.EnderecoDAO;
import com.supervet.dao.FuncionariosDAO;
import com.supervet.dao.PessoaDAO;
import com.supervet.model.Animal;
import com.supervet.model.Cliente;
import com.supervet.model.Endereco;
import com.supervet.model.Funcionario;
import com.supervet.model.Pessoa;

public class CriarFuncionario implements CommandInterface {

    private PessoaDAO pessoaDAO = new PessoaDAO();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private FuncionariosDAO funcionarioDAO = new FuncionariosDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        try {
        	RequestDispatcher rd = null;
            if (req.getParameter("acao") != null && req.getParameter("acao").equals("cadastrar")) {
                
            	String _nome = req.getParameter("nome");
            	String email = req.getParameter("email");
            	String cpf = req.getParameter("cpf");      
                String telefone = req.getParameter("telefone");
                
                Pessoa pessoa = new Pessoa(_nome, email, cpf, telefone);
                
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
            	
                String cargo = req.getParameter("cargo");
                String senha= req.getParameter("senha");
                
                Funcionario funcionario = new Funcionario();
                funcionario.setCargo(cargo);
                funcionario.setSenha(senha);
                funcionario.setId_pessoa(id_pessoa);
                
                funcionarioDAO.adicionar(funcionario);
                                
                req.setAttribute("mensagem", "Funcionário cadastrado com sucesso.");
                
                if(req.getSession().getAttribute("cargo_logado").equals("admin")) {
            		
            		req.setAttribute("count_fun", funcionarioDAO.count());
            		req.setAttribute("count_cliente", new ClienteDAO().count());
            		req.setAttribute("count_consulta", new ConsultaDAO().count());
            		req.setAttribute("count_animal", new AnimalDAO().count());
            		
            		rd = req.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
            	} else {
            		rd = req.getRequestDispatcher("/WEB-INF/view/home.jsp");
            	}
                
//                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/home.jsp");
                rd.forward(req, res);                
            } else {
                rd = req.getRequestDispatcher("/WEB-INF/view/funcionarios/criar.jsp");
                rd.forward(req, res);
            }
        } catch (SQLException | ClassNotFoundException | ServletException | IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

    }
}
