package com.supervet.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.AnimalDAO;
import com.supervet.dao.ClienteDAO;
import com.supervet.dao.EnderecoDAO;
import com.supervet.dao.FuncionariosDAO;
import com.supervet.dao.PessoaDAO;
import com.supervet.dao.StartupDAO;
import com.supervet.model.Animal;
import com.supervet.model.Cliente;
import com.supervet.model.Endereco;
import com.supervet.model.Funcionario;
import com.supervet.model.Pessoa;


public class Startup implements CommandInterface {
    
    private FuncionariosDAO funcDAO = new FuncionariosDAO();
    private PessoaDAO pessoaDao = new PessoaDAO();
    private EnderecoDAO enderecoDao = new EnderecoDAO();
    private StartupDAO stDAO = new StartupDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        try {
            
            stDAO.dropDatabase();
            stDAO.createDatabase();
            
            req.setAttribute("mensagem", "Base de dados criada.");
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            rd.forward(req, res);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public void gerarFuncionarios() {
    	Endereco endereco = new Endereco("22251070", "Rua Teste", "apto", "Bairro teste", "Rio de Janeiro", "RJ", 01);
    	Pessoa p = new Pessoa(1, "admin", "admin@gmail.com", "752.346.600-10", "3333-6985", endereco);    	
        Funcionario f = new Funcionario(1, "admin", "admin", p.getId_pessoa());
        
        try {
        	enderecoDao.insert(endereco);
        	pessoaDao.insert(p);
            funcDAO.adicionar(f);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
