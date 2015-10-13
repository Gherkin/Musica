package com.github.gherkin.resources;

import com.github.gherkin.data.Song;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;


public abstract class DAO {
    private static Connection connection;
    static private String url = "jdbc:mysql://localhost/musica";
    static private String user = "user";
    static private String password = "user";

    public static void initConnection() throws SQLException {
        new Driver();
        connection = DriverManager.getConnection(url, user, password);
    }

}
