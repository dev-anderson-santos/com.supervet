package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.FuncionariosDAO;

public class MostrarFuncionarios implements CommandInterface {

    private FuncionariosDAO funcDAO = new FuncionariosDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        try {
            req.setAttribute("funcionarios", funcDAO.getFuncionarios());
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/funcionarios/listar.jsp");
            rd.forward(req, res);
        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(MostrarFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
