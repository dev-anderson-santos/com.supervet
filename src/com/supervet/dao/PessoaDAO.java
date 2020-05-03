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

	public PessoaDAO() {
		
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
        
        boolean checa_cadastro = new SetupDAO().checaDatabase();
		if(checa_cadastro) {
			PreparedStatement pss = conexao.prepareStatement("USE `db_supervet`;");
	        pss.execute(); 
		}
        
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs != null && rs.next()) {
            lastId = rs.getInt(1);
        }
        conexao.close();
        return lastId;
    }

    public void editar(Cliente cli) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("UPDATE TB_CLIENTES SET MATRICULA = ? WHERE ID_CLIENTE = ?");        
        ps.setString(1, cli.getMatricula());
        ps.execute();
        conexao.close();
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
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, pessoa.getNome());
        ps.setString(2, pessoa.getEmail());
        ps.setString(3, pessoa.getCpf());
        ps.setString(4, pessoa.getTelefone());
        
        boolean checa_cadastro = new SetupDAO().checaDatabase();
		if(checa_cadastro) {
			PreparedStatement pss = conexao.prepareStatement("USE `db_supervet`;");
	        pss.execute(); 
		}
        
        ps.executeUpdate();   
        conexao.close();
        		
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
        conexao.close();
	}

	@Override
	public void delete(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_PESSOAS WHERE ID_PESSOA = ?");
        ps.setInt(1, id);
        ps.execute();	
        conexao.close();
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
