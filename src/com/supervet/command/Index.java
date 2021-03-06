package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.SetupDAO;

public class Index implements CommandInterface {
	
    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        try {
        	RequestDispatcher rd = null;
        	boolean checa_cadastro = new SetupDAO().checaDatabase();
    		if(!checa_cadastro) {
    			req.setAttribute("mensagem", "� necess�rio gerar a base de dados antes de acessar");
    			req.getRequestDispatcher("/WEB-INF/start.jsp");
    		} else {
    			rd = req.getRequestDispatcher("/WEB-INF/index.jsp");    			
    		}
            
            rd.forward(req, res);
        } catch (ServletException | IOException | ClassNotFoundException | SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("Ocorreu um erro Index: " + ex.getMessage());
        }
    }

    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }
}
