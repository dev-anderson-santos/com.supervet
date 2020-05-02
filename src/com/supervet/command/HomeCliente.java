package com.supervet.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class HomeCliente implements CommandInterface {

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        try {
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/home-clientes.jsp");         
            rd.forward(req, res);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(MostrarFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
