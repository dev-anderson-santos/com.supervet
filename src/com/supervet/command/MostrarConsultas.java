package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supervet.dao.ConsultaDAO;

public class MostrarConsultas implements CommandInterface {

    private ConsultaDAO consultaDAO = new ConsultaDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        
    	RequestDispatcher rd = null;
    	HttpSession session = req.getSession();    	
    	int id = Integer.parseInt(req.getParameter("id") != null ? req.getParameter("id") : "0");
    	try {  
        	if(session.getAttribute("cargo_logado") != null) {
        		if(session.getAttribute("cargo_logado").equals("medico")) {
        			req.setAttribute("consultas", consultaDAO.getConsultasByIdFuncionario(id));
        		} else {
        			req.setAttribute("consultas", consultaDAO.getConsultas());
        		}
        		rd = req.getRequestDispatcher("/WEB-INF/view/consultas/listar.jsp");
        	} else {
        		
        		req.setAttribute("consultas", consultaDAO.getConsultasByIdCliente(id));        		
        		rd = req.getRequestDispatcher("/WEB-INF/view/consultas/listar.jsp");
        	}
                        
            
            rd.forward(req, res);
        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

}
