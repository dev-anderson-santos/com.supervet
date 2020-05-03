package com.supervet.command;

import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.EnderecoDAO;
import com.supervet.dao.FuncionariosDAO;
import com.supervet.dao.PessoaDAO;
import com.supervet.dao.SetupDAO;
import com.supervet.model.Endereco;
import com.supervet.model.Funcionario;
import com.supervet.model.Pessoa;


public class Setup implements CommandInterface {
 
    private SetupDAO stDAO = new SetupDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        try {
            
//            stDAO.dropTables();
            stDAO.createDatabase();
            
            this.popularBanco();
            
            req.setAttribute("mensagem", "Base de dados criada. Login: admin@email.com | Senha: admin");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
            rd.forward(req, res);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public void popularBanco() {
    	Endereco endereco = new Endereco("22251070", "Rua Teste", "apto", "Bairro teste", "Rio de Janeiro", "RJ", 01);
    	Pessoa p = new Pessoa("Administrador", "admin@email.com", "752.346.600-10", "3333-6985", endereco);    	
        Funcionario f = new Funcionario();
        f.setCargo("admin");
        f.setSenha("admin");        
        
        try {
        	int id_pessoa = new PessoaDAO().adicionar(p);
        	
        	endereco.setId_pessoa(id_pessoa);
        	new EnderecoDAO().insert(endereco);
        	
        	f.setId_pessoa(id_pessoa);
        	new FuncionariosDAO().insert(f);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        

    }
}
