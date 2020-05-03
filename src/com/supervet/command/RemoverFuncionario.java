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
import com.supervet.model.Funcionario;

public class RemoverFuncionario implements CommandInterface {

    private FuncionariosDAO funcionarioDao = new FuncionariosDAO();
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private ConsultaDAO consultaDAO = new ConsultaDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        try {                
            int id = Integer.valueOf(req.getParameter("id"));
            
            Funcionario f = funcionarioDao.getById(id);
            int idPessoa = f.getId_pessoa();
            
            if(f.getCargo().equalsIgnoreCase("medico")) {
            	List<Consulta> consultas = consultaDAO.getConsultas();
            	for (Consulta consulta : consultas) {
            		if(consulta != null) {
            			if(f.getId_funcionario() == consulta.getFuncionario().getId_funcionario()) {
            				consultaDAO.delete(consulta.getId_consulta());
            			}
            		}
            	}            	
            }
            
            enderecoDAO.delete(f.getId_pessoa());
            funcionarioDao.delete(id);
            pessoaDAO.delete(idPessoa);
                            
            req.setAttribute("count_fun", funcionarioDao.count());
    		req.setAttribute("count_cliente", new ClienteDAO().count());
    		req.setAttribute("count_consulta", new ConsultaDAO().count());
    		req.setAttribute("count_animal", new AnimalDAO().count());
            req.setAttribute("mensagem", "Funcionário removido com sucesso.");                
            
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
            rd.forward(req, res);
            
        } catch (SQLException | ClassNotFoundException | ServletException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

