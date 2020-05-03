package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.AnimalDAO;
import com.supervet.dao.ClienteDAO;
import com.supervet.dao.ConsultaDAO;
import com.supervet.dao.EnderecoDAO;
import com.supervet.dao.FuncionariosDAO;
import com.supervet.dao.PessoaDAO;
import com.supervet.model.Animal;
import com.supervet.model.Cliente;
import com.supervet.model.Consulta;

public class RemoverCliente implements CommandInterface {

    private ClienteDAO clienteDAO = new ClienteDAO();
    private AnimalDAO animalDAO = new AnimalDAO();
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private ConsultaDAO consultaDAO = new ConsultaDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        try {       
        	RequestDispatcher rd = null;
            int id_cliente = Integer.valueOf(req.getParameter("id"));
            
            Cliente cli = clienteDAO.getClienteById(id_cliente);            
            
            Animal animal = animalDAO.getAnimalByClienteId(id_cliente);            
            
            List<Consulta> consultas = consultaDAO.getConsultas();
            for (Consulta consulta : consultas) {
            	if(consulta != null) {
            		if(cli.getId_cliente() == animal.getId_cliente()) {
            			consultaDAO.delete(consulta.getId_consulta());
            		}
                }
			}
            
            animalDAO.delete(animal.getId_animal());
            clienteDAO.delete(id_cliente);
            enderecoDAO.delete(cli.getId_pessoa());
            pessoaDAO.delete(cli.getId_pessoa());
            
            req.setAttribute("mensagem", "Cliente removido com sucesso.");                
            
            if(req.getSession().getAttribute("cargo_logado").equals("admin")) {
        		
        		req.setAttribute("count_fun", new FuncionariosDAO().count());
        		req.setAttribute("count_cliente", new ClienteDAO().count());
        		req.setAttribute("count_consulta", new ConsultaDAO().count());
        		req.setAttribute("count_animal", new AnimalDAO().count());
        		
        		rd = req.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
        	} else {
        		rd = req.getRequestDispatcher("/WEB-INF/view/home.jsp");
        	}
            rd.forward(req, res);
            
        } catch (SQLException | ClassNotFoundException | ServletException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

