package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.EnderecoDAO;
import com.supervet.dao.FuncionariosDAO;
import com.supervet.dao.PessoaDAO;
import com.supervet.model.Endereco;
import com.supervet.model.Funcionario;
import com.supervet.model.Pessoa;

public class AlterarFuncionario implements CommandInterface {

	private PessoaDAO pessoaDAO = new PessoaDAO();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private FuncionariosDAO funcionarioDAO = new FuncionariosDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        
        try {
        	Funcionario f = funcionarioDAO.getById(Integer.parseInt(req.getParameter("id")));
            if (req.getParameter("acao") != null && req.getParameter("acao").equals("alterar")) {            	
            	
            	String _nome = req.getParameter("nome");
            	String email = req.getParameter("email");
            	String cpf = req.getParameter("cpf");      
                String telefone = req.getParameter("telefone");                
                
                String cep = req.getParameter("cep");
            	String logradouro = req.getParameter("logradouro");
            	String complemento = req.getParameter("complemento");
                String bairro = req.getParameter("bairro");
                String cidade = req.getParameter("cidade");
                String uf = req.getParameter("uf");
                int numero = Integer.parseInt(req.getParameter("numero"));                
                
                Endereco endereco = enderecoDAO.getById(f.getId_pessoa());
                if(endereco == null) {
                	endereco = new Endereco();
                }
                endereco.setCep(cep);
                endereco.setLogradouro(logradouro);
                endereco.setComplemento(complemento);
                endereco.setBairro(bairro);
                endereco.setCidade(cidade);
                endereco.setUf(uf);
                endereco.setNumero(numero);
                endereco.setId_pessoa(f.getId_pessoa());
                
                System.out.println(endereco);
                Pessoa pessoa = new Pessoa(f.getId_pessoa(), _nome, email, cpf, telefone, endereco);
                pessoaDAO.edit(pessoa);                
                
                enderecoDAO.edit(endereco);
            	
                String cargo = req.getParameter("cargo");
                String senha= req.getParameter("senha");

                f.setCargo(cargo);
                f.setSenha(senha);
                
                funcionarioDAO.edit(f);
                req.setAttribute("mensagem", "Os dados foram alterados");
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/home.jsp");
                rd.forward(req, res);
            } else {                
                req.setAttribute("funcionario", funcionarioDAO.getFuncionarioById(f.getId_funcionario()));                
                req.setAttribute("funcionario_endereco", enderecoDAO.getById(f.getId_pessoa()));
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/funcionarios/alterar.jsp");
                rd.forward(req, res);
            }
        } catch (SQLException | ClassNotFoundException | ServletException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
