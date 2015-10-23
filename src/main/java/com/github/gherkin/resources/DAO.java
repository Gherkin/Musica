package com.github.gherkin.resources;

import com.github.gherkin.data.Song;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Driver;


public abstract class DAO {
    static private String url = "jdbc:mysql://localhost/musica";
    static private String user = "user";
    static private String password = "user";

    private static Connection connection;
    private static Logger logger;

    public static void initConnection() throws SQLException {
        new Driver();
        connection = DriverManager.getConnection(url, user, password);
        logger = Logger.getLogger("Musica.DAO");
    }

    public static Song retrieve(int id) throws SQLException {

        try {
            String query = "SELECT * FROM songs WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, String.valueOf(id));
            statement.execute();
            ResultSet results = statement.getResultSet();

            results.absolute(1);
            Song song = new Song(id, results.getString("name"));

            return song;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public static void insert(Song song) throws SQLException {

        try {
            String query = "INSERT INTO songs (id, name) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            if (song.getId() == null)
                statement.setNull(1, Types.NULL);
            else
                statement.setInt(1, song.getId());

            statement.setString(2, song.getName());
            statement.execute();

        } catch(SQLException e) {
            logger.log(Level.SEVERE, "Exception thrown", e);
        }

    }

    public static void delete(int id) throws SQLException {

        try {
            String query = "DELETE FROM songs WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            statement.execute();

        } catch(SQLException e) {
            logger.log(Level.SEVERE, "Exception thrown", e);
        }
    }
}
