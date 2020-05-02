package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supervet.dao.AnimalDAO;
import com.supervet.dao.ConsultaDAO;

public class MostrarAnimais implements CommandInterface {

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        
    	try {  
        		
    		req.setAttribute("animais", new AnimalDAO().getAll());        		
    		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/animais/listar.jsp");    
            rd.forward(req, res);
        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

}
