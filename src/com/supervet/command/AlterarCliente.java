package com.supervet.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supervet.dao.AnimalDAO;
import com.supervet.dao.ClienteDAO;
import com.supervet.dao.EnderecoDAO;
import com.supervet.dao.FuncionariosDAO;
import com.supervet.dao.PessoaDAO;
import com.supervet.model.Animal;
import com.supervet.model.Cliente;
import com.supervet.model.Endereco;
import com.supervet.model.Funcionario;
import com.supervet.model.Pessoa;

public class AlterarCliente implements CommandInterface {

	private PessoaDAO pessoaDAO = new PessoaDAO();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private AnimalDAO animalDAO = new AnimalDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse res) {
        
        try {
        	Cliente c = clienteDAO.getById(Integer.parseInt(req.getParameter("id")));
            if (req.getParameter("acao") != null && req.getParameter("acao").equals("alterar")) {            	
            	
            	String _nome_cliente = req.getParameter("nome");
            	String email = req.getParameter("email");
            	String cpf = req.getParameter("cpf");      
                String telefone = req.getParameter("telefone");
                
                String cep = req.getParameter("cep");
            	String logradouro = req.getParameter("logradouro");
            	String complemento = req.getParameter("complemento");
                String bairro = req.getParameter("bairro");
                String cidade = req.getParameter("cidade");
                String uf = req.getParameter("uf");
                int numero = Integer.parseInt(req.getParameter("numero"));
                
                Endereco endereco = enderecoDAO.getById(c.getId_pessoa());                
                endereco.setCep(cep);
                endereco.setLogradouro(logradouro);
                endereco.setComplemento(complemento);
                endereco.setBairro(bairro);
                endereco.setCidade(cidade);
                endereco.setUf(uf);
                endereco.setNumero(numero);
                endereco.setId_pessoa(c.getId_pessoa());

                Pessoa pessoa = new Pessoa(c.getId_pessoa(), _nome_cliente, email, cpf, telefone, endereco);
                pessoaDAO.edit(pessoa);                
                
                enderecoDAO.edit(endereco);
                
            	String raca = req.getParameter("raca");
            	int tipo = Integer.valueOf(req.getParameter("tipo"));
            	String nome = req.getParameter("nome_pet");
                int idade = Integer.parseInt(req.getParameter("idade"));
                String sexo = req.getParameter("sexo");
                int peso = Integer.parseInt(req.getParameter("peso"));                
                
                Animal animail = new Animal(raca.trim(), tipo, nome.trim(), idade, sexo, peso, c.getId_cliente());                
                animalDAO.edit(animail);
                
                req.setAttribute("mensagem", "Dados salvos com sucesso.");
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/home.jsp");
                rd.forward(req, res);
            } else {
                req.setAttribute("cliente", clienteDAO.getClienteById(c.getId_cliente()));                
                req.setAttribute("cliente_endereco", enderecoDAO.getById(c.getId_pessoa()));
                req.setAttribute("cliente_animal", animalDAO.getAnimalByClienteId(c.getId_cliente()));
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/clientes/alterar.jsp");
                rd.forward(req, res);
            }
        } catch (SQLException | ClassNotFoundException | ServletException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
