package com.supervet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJDBCFactory {

    public static Connection getConexao() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3308/db_supervet?useTimezone=true&serverTimezone=UTC", "root", "");
        return conexao;
    }
}
