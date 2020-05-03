package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.SetupDAO;

public class Start implements CommandInterface {
	
    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        try {
        	
    		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/start.jsp");
            rd.forward(req, res);
        } catch (ServletException | IOException ex) {
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
