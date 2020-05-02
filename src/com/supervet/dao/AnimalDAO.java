package com.supervet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.supervet.model.Animal;
import com.supervet.model.Cliente;

public class AnimalDAO implements GenericDAO<Animal>{

    public List<Animal> getAnimais() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT A.ID_ANIMAL, A.RACA, A.TIPO, A.NOME, A.IDADE, A.SEXO, A.PESO, C.ID_cliente, P.NOME "
        		+ "FROM TB_ANIMAIS A "
        		+ "INNER JOIN TB_CLIENTES C ON C.ID_CLIENTE = A.ID_CLIENTE "
        		+ "INNER JOIN TB_PESSOAS P ON P.ID_PESSOA = C.ID_PESSOA");
        ResultSet rs = ps.executeQuery();
        List<Animal> animais = new ArrayList<>();
        while (rs.next()) {
            animais.add(new Animal(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
        }
        return animais;
    }

    public Animal getAnimalById(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT A.ID_ANIMAL, A.RACA, A.TIPO, A.NOME, A.IDADE, A.SEXO, A.PESO, A.ID_cliente "
        		+ "FROM TB_ANIMAIS A "
        		+ "WHERE A.ID_ANIMAL = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Animal ani = null;
        if (rs != null && rs.next()) {
            ani = new Animal(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
        } else {
            System.out.println("ERRO, VAZIO");
        }
        return ani;
    }
    
    public Animal getAnimalByClienteId(int idCliente) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement(
        		"SELECT * "
        		+ "FROM TB_ANIMAIS A "
        		+ "INNER JOIN TB_CLIENTES C ON C.ID_CLIENTE = A.ID_CLIENTE "
        		+ "WHERE C.ID_CLIENTE = ?"
        		);
        ps.setInt(1, idCliente);
        
        ResultSet rs = ps.executeQuery();
        Animal animal = null;
        while (rs.next()) {
            animal = new Animal(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
        }
        
        return animal;
    }
    
    public void adicionar(Animal ani) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("INSERT INTO TB_ANIMAIS(RACA, TIPO, NOME, IDADE, SEXO, PESO, ID_cliente) VALUES (?,?,?,?,?,?,?)");
        ps.setString(1, ani.getRaca());
        ps.setInt(2, ani.getTipo());
        ps.setString(3, ani.getNome());
        ps.setInt(4, ani.getIdade());
        ps.setString(5, ani.getSexo());
        ps.setInt(6, ani.getPeso());
        ps.setInt(7, ani.getId_cliente());
        if (ani.getId_cliente() == 0) {
            ps.setNull(7, Types.INTEGER);
        } else {
            ps.setInt(7, ani.getId_cliente());
        }
        ps.execute();
    }

    public void excluir(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_ANIMAIS WHERE ID_ANIMAL = ?");
        ps.setInt(1, id);
        ps.execute();
    }

    public void editar(Animal ani) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("UPDATE TB_ANIMAIS SET RACA = ?, TIPO = ?, NOME = ?, IDADE = ?, SEXO = ?, PESO = ?, ID_cliente = ? WHERE ID_ANIMAL = ?");
        ps.setString(1, ani.getRaca());
        ps.setInt(2, ani.getTipo());
        ps.setString(3, ani.getNome());
        ps.setInt(4, ani.getIdade());
        ps.setString(5, ani.getSexo());
        ps.setInt(6, ani.getPeso());
        ps.setInt(7, ani.getId_cliente());
        if (ani.getId_cliente() == 0) {
            ps.setNull(7, Types.INTEGER);
        } else {
            ps.setInt(7, ani.getId_cliente());
        }
        ps.setInt(7, ani.getId_animal());
        ps.execute();
    }

    public List<Animal> getAnimaisByCliente(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("SELECT * FROM TB_ANIMAIS WHERE ID_cliente = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        List<Animal> animais = new ArrayList<>();
        while (rs.next()) {
            animais.add(new Animal(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
        }
        return animais;
    }

	@Override
	public List<Animal> getAll() throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("SELECT * FROM TB_ANIMAIS");
        
        ResultSet rs = ps.executeQuery();
        List<Animal> animais = new ArrayList<>();
        while (rs.next()) {
            animais.add(new Animal(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
        }
        return animais;
	}

	@Override
	public Animal getById(int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Animal value) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(Animal ani) throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("UPDATE TB_ANIMAIS SET RACA = ?, TIPO = ?, NOME = ?, IDADE = ?, SEXO = ?, PESO = ? WHERE ID_CLIENTE= ?");
        ps.setString(1, ani.getRaca());
        ps.setInt(2, ani.getTipo());
        ps.setString(3, ani.getNome());
        ps.setInt(4, ani.getIdade());
        ps.setString(5, ani.getSexo());
        ps.setInt(6, ani.getPeso());
        ps.setInt(7, ani.getId_cliente());
        
        ps.executeUpdate();		
	}

	@Override
	public void delete(int id) throws SQLException, ClassNotFoundException {

		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM TB_ANIMAIS WHERE ID_ANIMAL = ?");
        ps.setInt(1, id);
        ps.execute();
	}

	@Override
	public int count() throws SQLException, ClassNotFoundException {
		Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("SELECT COUNT(ID_ANIMAL) FROM TB_ANIMAIS");        
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
