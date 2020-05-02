package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.ClienteDAO;
import com.supervet.dao.FuncionariosDAO;

public class MostrarClientes implements CommandInterface {

    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        try {
            req.setAttribute("clientes", clienteDAO.getAll());
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/clientes/listar.jsp");
            rd.forward(req, res);
        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(MostrarClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
