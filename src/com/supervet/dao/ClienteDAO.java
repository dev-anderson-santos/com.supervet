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

public class ClienteDAO implements GenericDAO<Cliente> {

    public List<Cliente> getClientes() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT ID_CLIENTE, P.NOME, P.EMAIL, P.CPF, P.TELEFONE, A.ID_ANIMAL "
        		+ "FROM TB_CLIENTES C "
        		+ "INNER JOIN TB_PESSOAS P ON P.ID_PESSOA = C.ID_PESSOA "
        		+ "INNER JOIN TB_ANIMAIS A ON A.ID_CLIENTE = C.ID_CLIENTE "
        		);
        ResultSet rs = ps.executeQuery();
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
        	clientes.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        conexao.close();
        return clientes;
    }

    public Cliente getClienteById(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT ID_CLIENTE, NOME, EMAIL, CPF, TELEFONE, MATRICULA, P.ID_PESSOA "
        		+ "FROM TB_CLIENTES C "
        		+ "INNER JOIN TB_PESSOAS P ON P.ID_PESSOA = C.ID_PESSOA "
        		+ "WHERE ID_CLIENTE = ?"
        		);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Cliente cli = null;
        if (rs != null && rs.next()) {
            cli = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
        } else {
            System.out.println("ERRO, VAZIO");
        }
        conexao.close();
        return cli;
    }
    
    public Cliente getClienteByMatricula(String matricula) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT C.ID_CLIENTE, P.NOME, P.EMAIL, P.CPF, P.TELEFONE, C.MATRICULA "
        		+ "FROM TB_CLIENTES C "
        		+ "INNER JOIN TB_PESSOAS P ON P.ID_PESSOA = C.ID_PESSOA "
        		+ "WHERE C.MATRICULA = ?"
        		);
        ps.setString(1, matricula);
        ResultSet rs = ps.executeQuery();
        Cliente cli = null;
        if (rs != null && rs.next()) {
            cli = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
        } else {
            System.out.println("ERRO, VAZIO");
        }
        conexao.close();
        return cli;
    }

    public int adicionar(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        String sql = "INSERT INTO TB_CLIENTES(MATRICULA, ID_PESSOA) VALUES (?,?)";
        PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, cliente.getMatricula());
        ps.setInt(2, cliente.getId_pessoa());
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs != null && rs.next()) {        	
            return rs.getInt(1);
        } else {
            return 0;
        }        
    }

    public void excluir(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_CLIENTES WHERE ID_CLIENTE = ?");
        ps.setInt(1, id);
        ps.executeQuery();
        conexao.close();
        
    }

    public void editar(Cliente cli) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("UPDATE TB_CLIENTES SET MATRICULA = ? WHERE ID_CLIENTE = ?");        
        ps.setString(1, cli.getMatricula());
        ps.execute();
        conexao.close();
    }

    public boolean checaLoginCliente(String matricula) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("SELECT * FROM TB_CLIENTES WHERE MATRICULA = ?");
        ps.setString(1, matricula);
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
        	
        	boolean result = (matricula.equals(rs.getString(2)));
            
        	ps.close();
            conexao.close();
            return result;        	
        } else {
            return false;
        }
    }

	@Override
	public List<Cliente> getAll() throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT C.ID_CLIENTE, P.NOME, P.EMAIL, P.CPF, P.TELEFONE, C.MATRICULA "
        		+ "FROM TB_CLIENTES C "
        		+ "INNER JOIN TB_PESSOAS P ON P.ID_PESSOA = C.ID_PESSOA "
        		+ "INNER JOIN TB_ANIMAIS A ON A.ID_CLIENTE = C.ID_CLIENTE "
        		);
        ResultSet rs = ps.executeQuery();
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
        	clientes.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        
        conexao.close();
        return clientes;
	}

	@Override
	public Cliente getById(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT ID_CLIENTE, NOME, EMAIL, CPF, TELEFONE, MATRICULA, P.ID_PESSOA "
        		+ "FROM TB_CLIENTES C "
        		+ "INNER JOIN TB_PESSOAS P ON P.ID_PESSOA = C.ID_PESSOA "
        		+ "WHERE ID_CLIENTE = ?"
        		);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Cliente cli = null;
        if (rs != null && rs.next()) {
            cli = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
        } else {
            System.out.println("ERRO, VAZIO");
        }
        
        conexao.close();
        return cli;
	}

	@Override
	public void insert(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        String sql = "INSERT INTO TB_CLIENTES(MATRICULA, ID_PESSOA) VALUES (?,?)";
        PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, cliente.getMatricula());
        ps.setInt(2, cliente.getId_pessoa());
        ps.executeUpdate();
//        
//        ResultSet rs = ps.getGeneratedKeys();
//        if (rs != null && rs.next()) {
//            return rs.getInt(1);
//        } else {
//            return 0;
//        }
        
        conexao.close();
		
	}

	@Override
	public void edit(Cliente cli) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("UPDATE TB_CLIENTES SET MATRICULA = ? WHERE ID_CLIENTE = ?");        
        ps.setString(1, cli.getMatricula());
        ps.execute();	
        
        conexao.close();
	}

	@Override
	public void delete(int id) throws SQLException, ClassNotFoundException {

		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_CLIENTES WHERE ID_CLIENTE = ?");
        ps.setInt(1, id);
        ps.execute();	
        
        conexao.close();
	}

	@Override
	public int count() throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("SELECT COUNT(ID_CLIENTE) FROM TB_CLIENTES");        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
          int numberOfRows = rs.getInt(1);
          
          conexao.close();
          return numberOfRows;
        } else {
          return 0;
        }     
        
		
	}

	@Override
	public int lastId() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}
}
