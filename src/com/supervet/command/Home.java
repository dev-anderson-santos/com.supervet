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

public class Home implements CommandInterface {
	
	private FuncionariosDAO funDAO = new FuncionariosDAO();
	private ClienteDAO cliDAO = new ClienteDAO();
	private ConsultaDAO conDAO = new ConsultaDAO();
	private AnimalDAO aniDAO = new AnimalDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        
    	RequestDispatcher rd = null;
    	try {
        	
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
            Logger.getLogger(MostrarFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
