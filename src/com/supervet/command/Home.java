package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.AnimalDAO;
import com.supervet.dao.ClienteDAO;
import com.supervet.dao.ConsultaDAO;
import com.supervet.dao.FuncionariosDAO;
import com.supervet.dao.SetupDAO;

public class Home implements CommandInterface {
	
	private FuncionariosDAO funDAO = new FuncionariosDAO();
	private ClienteDAO cliDAO = new ClienteDAO();
	private ConsultaDAO conDAO = new ConsultaDAO();
	private AnimalDAO aniDAO = new AnimalDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        
    	RequestDispatcher rd = null;
    	try {
    		boolean checa_cadastro = new SetupDAO().checaDatabase();
    		if(!checa_cadastro) {
    			req.setAttribute("mensagem", "É necessário gerar a base de dados antes de acessar");
    			rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
    		}
        	if(req.getSession().getAttribute("cargo_logado").equals("admin")) {
        		
        		req.setAttribute("count_fun", funDAO.count());
        		req.setAttribute("count_cliente", cliDAO.count());
        		req.setAttribute("count_consulta", conDAO.count());
        		req.setAttribute("count_animal", aniDAO.count());
        		
        		rd = req.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
        	} else {
        		rd = req.getRequestDispatcher("/WEB-INF/view/home.jsp");
        	}
        	
            rd.forward(req, res);
        } catch (ServletException | IOException | ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
