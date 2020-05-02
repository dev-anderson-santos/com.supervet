package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.ConsultaDAO;

public class RemoverConsulta implements CommandInterface {

    private ConsultaDAO consultaDAO = new ConsultaDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
    	
    	RequestDispatcher rd = null;
        try {                
            int id_consulta = Integer.valueOf(req.getParameter("id"));
            
            consultaDAO.delete(id_consulta);
                            
            if(req.getSession().getAttribute("cargo_logado") != null) {
            	rd = req.getRequestDispatcher("/WEB-INF/view/home.jsp");            	
            } else {
            	rd = req.getRequestDispatcher("/WEB-INF/view/home-clientes.jsp");
            }            
            
            rd.forward(req, res);
            
        } catch (SQLException | ClassNotFoundException | ServletException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

