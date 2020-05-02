package com.supervet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supervet.model.Cliente;
import com.supervet.model.Endereco;
import com.supervet.model.Funcionario;
import com.supervet.model.Pessoa;

public class EnderecoDAO implements GenericDAO<Endereco>{

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

    public void adicionar(Endereco endereco) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("INSERT INTO TB_ENDERECOS(CEP, LOGRADOURO, COMPLEMENTO, BAIRRO, CIDADE, UF, NUMERO, ID_PESSOA) VALUES (?,?,?,?,?,?,?,?)");
        ps.setString(1, endereco.getCep());
        ps.setString(2, endereco.getLogradouro());
        ps.setString(3, endereco.getComplemento());
        ps.setString(4, endereco.getBairro());
        ps.setString(5, endereco.getCidade());
        ps.setString(6, endereco.getUf());
        ps.setInt(7, endereco.getNumero());
        ps.setInt(8, endereco.getId_pessoa());
        
        ps.execute();
    }

    public void excluir(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_CLIENTES WHERE ID_CLIENTE = ?");
        ps.setInt(1, id);
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
	public List<Endereco> getAll() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Endereco getById(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT * "
        		+ "FROM TB_ENDERECOS "        		
        		+ "WHERE ID_PESSOA = ?"
        		);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Endereco e = null;
        while (rs.next()) {
            e = new Endereco(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9));
        } 
        return e;
	}

	@Override
	public void insert(Endereco endereco) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("INSERT INTO TB_ENDERECOS(CEP, LOGRADOURO, COMPLEMENTO, BAIRRO, CIDADE, UF, NUMERO, ID_PESSOA) VALUES (?,?,?,?,?,?,?,?)");
        ps.setString(1, endereco.getCep());
        ps.setString(2, endereco.getLogradouro());
        ps.setString(3, endereco.getComplemento());
        ps.setString(4, endereco.getBairro());
        ps.setString(5, endereco.getCidade());
        ps.setString(6, endereco.getUf());
        ps.setInt(7, endereco.getNumero());
        ps.setInt(8, endereco.getId_pessoa());
        
        ps.execute();		
	}

	@Override
	public void edit(Endereco endereco) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("UPDATE TB_ENDERECOS SET CEP = ?, LOGRADOURO = ?, COMPLEMENTO = ?, BAIRRO = ?, CIDADE = ?, UF = ?, NUMERO = ? WHERE ID_PESSOA = ?");
        ps.setString(1, endereco.getCep());
        ps.setString(2, endereco.getLogradouro());
        ps.setString(3, endereco.getComplemento());
        ps.setString(4, endereco.getBairro());
        ps.setString(5, endereco.getCidade());
        ps.setString(6, endereco.getUf());
        ps.setInt(7, endereco.getNumero());
        ps.setInt(8, endereco.getId_pessoa());
        
        ps.executeUpdate();
		
	}

	@Override
	public void delete(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_ENDERECOS WHERE ID_PESSOA = ?");
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
