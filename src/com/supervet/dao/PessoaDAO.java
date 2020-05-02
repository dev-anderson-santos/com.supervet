package com.supervet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.supervet.model.Cliente;
import com.supervet.model.Funcionario;
import com.supervet.model.Pessoa;

public class PessoaDAO implements GenericDAO<Pessoa> {

    public List<Cliente> getClientes() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT ID_FUNCIONARIO, NOME, EMAIL, CPF, TELEFONE, SENHA, CARGO "
        		+ "FROM TB_FUNCIONARIOS "
        		+ "INNER JOIN TB_PESSOAS P ON P.ID_PESSOA = TB_FUNCIONARIOS.ID_PESSOA"
        		);
        ResultSet rs = ps.executeQuery();
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
        	clientes.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        return clientes;
    }

    public Cliente getClienteById(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT ID_CLIENTE, NOME, EMAIL, CPF, TELEFONE, MATRICULA "
        		+ "FROM TB_CLIENTES C "
        		+ "INNER JOIN TB_PESSOAS P ON P.ID_PESSOA = C.ID_PESSOA "
        		+ "WHERE ID_CLIENTE = ?"
        		);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Cliente cli = null;
        if (rs != null && rs.next()) {
            cli = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
        } else {
            System.out.println("ERRO, VAZIO");
        }
        return cli;
    }

    public int adicionar(Pessoa pessoa) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        int lastId = 0;
        String sql = "INSERT INTO TB_PESSOAS(NOME, EMAIL, CPF, TELEFONE) VALUES (?,?,?,?)";
        PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, pessoa.getNome());
        ps.setString(2, pessoa.getEmail());
        ps.setString(3, pessoa.getCpf());
        ps.setString(4, pessoa.getTelefone());
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs != null && rs.next()) {
            lastId = rs.getInt(1);
        }
        
        return lastId;
    }

    public void excluir(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_CLIENTES WHERE ID_CLIENTE = ?");
        ps.setInt(1, id);
        ps.execute();
    }

    public void editar(Cliente cli) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("UPDATE TB_CLIENTES SET MATRICULA = ? WHERE ID_CLIENTE = ?");        
        ps.setString(1, cli.getMatricula());
        ps.execute();
    }

    public boolean checaLoginCliente(int usuario, String matricula) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("SELECT MATRICULA FROM TB_CLIENTES WHERE ID_CLIENTE = ?");
        ps.setInt(1, usuario);
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
            return (matricula.equals(rs.getString(1)));
        } else {
            return false;
        }
    }

	@Override
	public List<Pessoa> getAll() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa getById(int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        int lastId = 0;
        String sql = "INSERT INTO TB_PESSOAS(NOME, EMAIL, CPF, TELEFONE) VALUES (?,?,?,?)";
        PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, pessoa.getNome());
        ps.setString(2, pessoa.getEmail());
        ps.setString(3, pessoa.getCpf());
        ps.setString(4, pessoa.getTelefone());
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs != null && rs.next()) {
            lastId = rs.getInt(1);
        }        
        		
	}

	@Override
	public void edit(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("UPDATE TB_PESSOAS SET NOME = ?, EMAIL = ?, CPF = ?, TELEFONE = ? WHERE ID_PESSOA = ?");        
        ps.setString(1, pessoa.getNome());
        ps.setString(2, pessoa.getEmail());
        ps.setString(3, pessoa.getCpf());
        ps.setString(4, pessoa.getTelefone());
        ps.setInt(5, pessoa.getId_pessoa());
        ps.executeUpdate();		
	}

	@Override
	public void delete(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_PESSOAS WHERE ID_PESSOA = ?");
        ps.setInt(1, id);
        ps.execute();		
	}

	@Override
	public int count() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastId() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

}
