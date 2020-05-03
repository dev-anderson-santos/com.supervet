package com.supervet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supervet.model.Consulta;

public class ConsultaDAO implements GenericDAO<Consulta>{

    public List<Consulta> getConsultas() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT C.ID_CONSULTA, C.DATA, C.HORA, C.TIPO, C.STATUS, A.ID_ANIMAL, F.ID_FUNCIONARIO "
        		+ "FROM TB_CONSULTAS C "
        		+ "INNER JOIN TB_ANIMAIS A ON A.ID_ANIMAL = C.ID_ANIMAL "
        		+ "INNER JOIN TB_CLIENTES CLI ON CLI.ID_CLIENTE = A.ID_CLIENTE "
        		+ "INNER JOIN TB_FUNCIONARIOS F ON F.ID_FUNCIONARIO = C.ID_FUNCIONARIO "
        		);
        ResultSet rs = ps.executeQuery();
        List<Consulta> consultas = new ArrayList<>();
        while (rs.next()) {
            consultas.add(new Consulta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), new AnimalDAO().getAnimalById(rs.getInt(6)), new FuncionariosDAO().getFuncionarioById(rs.getInt(7))));
        }
        conexao.close();
        return consultas;
    }
    
    public List<Consulta> getConsultasByIdCliente(int idCliente) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT C.ID_CONSULTA, C.DATA, C.HORA, C.TIPO, C.STATUS, A.ID_animal, F.ID_FUNCIONARIO "
        		+ "FROM TB_CONSULTAS C "
        		+ "INNER JOIN TB_ANIMAIS A ON A.ID_ANIMAL = C.ID_ANIMAL "
        		+ "INNER JOIN TB_CLIENTES CLI ON CLI.ID_CLIENTE = A.ID_CLIENTE "
        		+ "INNER JOIN TB_FUNCIONARIOS F ON F.ID_FUNCIONARIO = C.ID_FUNCIONARIO "
        		+ "WHERE CLI.ID_CLIENTE = ?"
        		);
        ps.setInt(1, idCliente);
        ResultSet rs = ps.executeQuery();
        List<Consulta> consultas = new ArrayList<>();
        while (rs.next()) {
            consultas.add(
        		new Consulta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), new AnimalDAO().getAnimalById(rs.getInt(6)), new FuncionariosDAO().getFuncionarioById(rs.getInt(7)))
            );
        }
        conexao.close();
        return consultas;
    }
    
    public Consulta getConsultaByIdAnimal(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT C.ID_CONSULTA, C.DATA, C.HORA, C.TIPO, C.STATUS, A.ID_animal, F.ID_FUNCIONARIO "
        		+ "FROM TB_CONSULTAS C "
        		+ "INNER JOIN TB_ANIMAIS A ON A.ID_ANIMAL = C.ID_ANIMAL "        		
        		+ "INNER JOIN TB_FUNCIONARIOS F ON F.ID_FUNCIONARIO = C.ID_FUNCIONARIO "
        		+ "WHERE A.ID_ANIMAL = ?"
        		);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Consulta consulta = null;
        while (rs.next()) {            
    		new Consulta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), new AnimalDAO().getAnimalById(rs.getInt(6)), new FuncionariosDAO().getFuncionarioById(rs.getInt(7)));
        }
        conexao.close();
        return consulta;
    }
    
    public List<Consulta> getConsultasByIdFuncionario(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT C.ID_CONSULTA, C.DATA, C.HORA, C.TIPO, C.STATUS, A.ID_animal, F.ID_FUNCIONARIO "
        		+ "FROM TB_CONSULTAS C "
        		+ "INNER JOIN TB_ANIMAIS A ON A.ID_ANIMAL = C.ID_ANIMAL "
        		+ "INNER JOIN TB_CLIENTES CLI ON CLI.ID_CLIENTE = A.ID_CLIENTE "
        		+ "INNER JOIN TB_FUNCIONARIOS F ON F.ID_FUNCIONARIO = C.ID_FUNCIONARIO "
        		+ "WHERE F.ID_FUNCIONARIO = ?"
        		);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        List<Consulta> consultas = new ArrayList<>();
        while (rs.next()) {
            consultas.add(
        		new Consulta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), new AnimalDAO().getAnimalById(rs.getInt(6)), new FuncionariosDAO().getFuncionarioById(rs.getInt(7)))
            );
        }
        conexao.close();
        return consultas;
    }

    public Consulta getConsultaById(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT C.ID_CONSULTA, C.DATA, C.TIPO, C.STATUS, C.ID_ANIMAL "
        		+ "FROM TB_CONSULTAS C "
				+ "WHERE C.ID_CONSULTA = ?"
        		);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Consulta consulta = null;
        if (rs != null && rs.next()) {
            consulta = new Consulta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
        } else {
            System.out.println("ERRO, Não há dados a retornar");
        }
        conexao.close();
        return consulta;
    }
    
    public Consulta getConsulta(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT C.ID_CONSULTA, C.DATA, C.HORA, C.TIPO, C.STATUS, C.ID_CLIENTE "
        		+ "FROM TB_CONSULTAS C "
				+ "WHERE C.ID_CONSULTA = ?"
        		);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Consulta consulta = null;
        if (rs != null && rs.next()) {
            consulta = new Consulta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
        } else {
            System.out.println("ERRO, VAZIO");
        }
        conexao.close();
        return consulta;
    }

    public void adicionar(Consulta consulta) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(""
        		+ "INSERT INTO TB_CONSULTAS(DATA, HORA, TIPO, STATUS, ID_ANIMAL, ID_FUNCIONARIO) "
        		+ "VALUES (?,?,?,?,?,?)");        
        ps.setString(1, consulta.getData());
        ps.setString(2, consulta.getHora());
        ps.setString(3, consulta.getTipo());
        ps.setInt(4, consulta.getStatus());
        ps.setInt(5, consulta.getAnimal().getId_animal());
        ps.setInt(6, consulta.getFuncionario().getId_funcionario());
        ps.execute();
        conexao.close();
    }

    public void excluir(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_CONSULTAS WHERE ID_CONSULTA = ?");
        ps.setInt(1, id);
        ps.execute();
    }

    public void editar(Consulta consulta) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("UPDATE TB_FUNCIONARIOS SET DATA = ?, HORA = ?, TIPO = ?, STATUS = ? WHERE ID_CLIENTE = ?");        
        ps.setString(1, consulta.getData());
        ps.setString(2, consulta.getHora());
        ps.setString(3, consulta.getTipo());
        ps.setInt(4, consulta.getStatus());
        ps.setInt(5, consulta.getAnimal().getId_animal());
        ps.execute();
        conexao.close();
    }

	@Override
	public List<Consulta> getAll() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consulta getById(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT C.ID_CONSULTA, C.DATA, C.TIPO, C.STATUS, C.ID_ANIMAL "
        		+ "FROM TB_CONSULTAS C "
				+ "WHERE C.ID_CONSULTA = ?"
        		);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Consulta consulta = null;
        if (rs != null && rs.next()) {
            consulta = new Consulta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
        } else {
            System.out.println("ERRO, VAZIO");
        }
        conexao.close();
        return consulta;
	}

	@Override
	public void insert(Consulta value) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(Consulta consulta) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("UPDATE TB_CONSULTAS SET STATUS = ? WHERE ID_CONSULTA = ?");        
        
        ps.setInt(1, consulta.getStatus());
        ps.setInt(2, consulta.getId_consulta());
        ps.executeUpdate();
        conexao.close();
		
	}

	@Override
	public void delete(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_CONSULTAS WHERE ID_CONSULTA = ?");
        ps.setInt(1, id);
        ps.execute();
        conexao.close();
	}

	@Override
	public int count() throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("SELECT COUNT(ID_CONSULTA) FROM TB_CONSULTAS");        
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
