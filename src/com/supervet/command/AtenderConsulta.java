package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.ConsultaDAO;
import com.supervet.dao.EnderecoDAO;
import com.supervet.dao.FuncionariosDAO;
import com.supervet.dao.PessoaDAO;
import com.supervet.model.Consulta;
import com.supervet.model.Endereco;
import com.supervet.model.Funcionario;
import com.supervet.model.Pessoa;

public class AtenderConsulta implements CommandInterface {
	
    private ConsultaDAO consultaDAO = new ConsultaDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        
        try {
        	int id_consulta = Integer.valueOf(req.getParameter("id"));
        	Consulta c = consultaDAO.getById(id_consulta);
        	c.setStatus(1);
            
            consultaDAO.edit(c);
            
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/home.jsp");
            rd.forward(req, res);
        } catch (SQLException | ClassNotFoundException | ServletException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
