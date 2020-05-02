package com.supervet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supervet.model.Funcionario;
import com.supervet.model.Pessoa;

public class FuncionariosDAO implements GenericDAO<Funcionario>{

    public List<Funcionario> getFuncionarios() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT F.ID_funcionario, P.NOME, P.EMAIL, P.CPF, P.TELEFONE, F.CARGO, F.SENHA "
        		+ "FROM TB_FUNCIONARIOS F "
        		+ "INNER JOIN TB_PESSOAS P ON P.ID_PESSOA = F.ID_PESSOA"
        		);
        ResultSet rs = ps.executeQuery();
        List<Funcionario> funcionarios = new ArrayList<>();
        while (rs.next()) {
            funcionarios.add(new Funcionario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
        }
        return funcionarios;
    }

    public Funcionario getFuncionarioById(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT F.ID_funcionario, P.NOME, P.EMAIL, P.CPF, P.TELEFONE, F.CARGO, F.SENHA "
        		+ "FROM TB_FUNCIONARIOS F "
        		+ "INNER JOIN TB_pessoas P ON P.ID_pessoa = F.ID_pessoa "
        		+ "WHERE F.ID_funcionario = ?"
        		);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Funcionario func = null;
        if (rs != null && rs.next()) {
            func = new Funcionario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
        } else {
            System.out.println("ERRO, VAZIO");
        }
        return func;
    }
    
    public Funcionario getFuncionario(String email, String senha) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT F.ID_funcionario, P.NOME, P.EMAIL, P.CPF, P.TELEFONE, F.CARGO, F.SENHA "
        		+ "FROM TB_FUNCIONARIOS F "
        		+ "INNER JOIN TB_pessoas P ON P.ID_pessoa = F.ID_pessoa "
        		+ "WHERE P.EMAIL= ? AND F.SENHA = ?"
        		);
        ps.setString(1, email);
        ps.setString(2, senha);
        ResultSet rs = ps.executeQuery();
        Funcionario func = null;
        if (rs != null && rs.next()) {
            func = new Funcionario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
        } else {
            System.out.println("ERRO, VAZIO");
        }
        return func;
    }

    public void adicionar(Funcionario func) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("INSERT INTO TB_FUNCIONARIOS(SENHA, CARGO, ID_PESSOA) VALUES (?,?,?)");        
        ps.setString(1, func.getSenha());
        ps.setString(2, func.getCargo());
        ps.setInt(3, func.getId_pessoa());
        ps.execute();
    }

    public void excluir(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_FUNCIONARIOS WHERE ID_FUNCIONARIO = ?");
        ps.setInt(1, id);
        ps.execute();
    }

    public void editar(Funcionario func) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("UPDATE TB_FUNCIONARIOS SET SENHA = ?, CARGO = ? WHERE ID_FUNCIONARIO = ?");        
        ps.setString(6, func.getSenha());
        ps.setString(7, func.getCargo());
        ps.setInt(8, func.getId_funcionario());
        ps.execute();
    }

    public boolean validarLogin(String email, String senha) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT F.SENHA, P.EMAIL "
        		+ "FROM TB_FUNCIONARIOS F "
        		+ "INNER JOIN TB_pessoas P ON P.ID_pessoa = F.ID_pessoa "
        		+ "WHERE P.EMAIL= ? AND F.SENHA = ?"
        		);
        ps.setString(1, email);
        ps.setString(2, senha);
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
            return (senha.equals(rs.getString(1)) && email.equalsIgnoreCase(rs.getString(2)));
        } else {
            return false;
        }
    }

	public List<Funcionario> getMedicos() throws ClassNotFoundException, SQLException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT F.ID_funcionario, P.NOME, P.EMAIL, P.CPF, P.TELEFONE, F.CARGO, F.SENHA "
        		+ "FROM TB_FUNCIONARIOS F "
        		+ "INNER JOIN TB_PESSOAS P ON P.ID_PESSOA = F.ID_PESSOA "
        		+ "WHERE F.CARGO = 'medico'"
        		);
        ResultSet rs = ps.executeQuery();
        List<Funcionario> funcionarios = new ArrayList<>();
        while (rs.next()) {
            funcionarios.add(new Funcionario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
        }
        return funcionarios;
	}

	@Override
	public List<Funcionario> getAll() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionario getById(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT F.ID_funcionario, F.CARGO, F.SENHA, P.ID_PESSOA "
        		+ "FROM TB_FUNCIONARIOS F "
        		+ "INNER JOIN TB_pessoas P ON P.ID_pessoa = F.ID_pessoa "
        		+ "WHERE F.ID_funcionario = ?"
        		);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Funcionario f = null;
        while (rs.next()) {
            f = new Funcionario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
        }
        return f;
	}

	@Override
	public void insert(Funcionario value) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(Funcionario f) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("UPDATE TB_FUNCIONARIOS SET SENHA = ?, CARGO = ? WHERE ID_FUNCIONARIO = ?");        
        ps.setString(1, f.getSenha());
        ps.setString(2, f.getCargo());
        ps.setInt(3, f.getId_funcionario());
        ps.execute();
		
	}

	@Override
	public void delete(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_FUNCIONARIOS WHERE ID_FUNCIONARIO = ?");
        ps.setInt(1, id);
        ps.execute();		
	}

	@Override
	public int count() throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("SELECT COUNT(ID_FUNCIONARIO) FROM TB_FUNCIONARIOS");        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
          int numberOfRows = rs.getInt(1);
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
