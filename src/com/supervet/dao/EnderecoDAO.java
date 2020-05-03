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

	public EnderecoDAO() {
		
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
        conexao.close();
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
        conexao.close();
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
        
        boolean checa_cadastro = new SetupDAO().checaDatabase();
		if(checa_cadastro) {
			PreparedStatement pss = conexao.prepareStatement("USE `db_supervet`;");
	        pss.execute(); 
		}
        
        ps.execute();
        conexao.close();
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
        conexao.close();
		
	}

	@Override
	public void delete(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_ENDERECOS WHERE ID_PESSOA = ?");
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
