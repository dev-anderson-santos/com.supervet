package com.supervet.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index implements CommandInterface {

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        try {        	
            
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
            
            rd.forward(req, res);
        } catch (ServletException | IOException ex) {
        	System.out.println("Ocorreu um erro: " + ex.getMessage());
        }
    }

    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }
}
