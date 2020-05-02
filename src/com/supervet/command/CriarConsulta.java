package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.AnimalDAO;
import com.supervet.dao.ClienteDAO;
import com.supervet.dao.ConsultaDAO;
import com.supervet.dao.FuncionariosDAO;
import com.supervet.model.Cliente;
import com.supervet.model.Consulta;

public class CriarConsulta implements CommandInterface {

    private ConsultaDAO consultaDAO = new ConsultaDAO();
    private FuncionariosDAO funcDAO = new FuncionariosDAO();
    private AnimalDAO animalDAO = new AnimalDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        try {
            if (req.getParameter("acao") != null && req.getParameter("acao").equals("cadastrar")) {
                String data = req.getParameter("data");
                String hora = req.getParameter("hora");
                String tipo = req.getParameter("tipo");
                int status  = Integer.valueOf(req.getParameter("status"));
                int idAnimal = Integer.valueOf(req.getParameter("id_animal"));
                int id_funcionario = Integer.valueOf(req.getParameter("id_funcionario"));
                
                Consulta consulta = new Consulta(data, hora, tipo, status);
                consulta.getAnimal().setId_animal(idAnimal);
                consulta.getFuncionario().setId_funcionario(id_funcionario);
                
                consultaDAO.adicionar(consulta);
                                
                req.setAttribute("mensagem", "Consulta marcada com sucesso.");                
                req.setAttribute("consultas", consultaDAO.getConsultas());
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/home-clientes.jsp");
                rd.forward(req, res);
            } else {
            	
            	Cliente c = (Cliente) req.getSession().getAttribute("cliente_logado"); 
            	
            	req.setAttribute("animal_cliente", animalDAO.getAnimalByClienteId(c.getId_cliente()));
            	req.setAttribute("funcionarios_medicos", funcDAO.getMedicos());
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/consultas/criar.jsp");
                rd.forward(req, res);
            }
        } catch (SQLException | ClassNotFoundException | ServletException | IOException ex) {
            System.out.println(ex.getMessage() + " onde? ");
        }
    }

}