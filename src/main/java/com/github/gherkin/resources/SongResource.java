package com.github.gherkin.resources;

import com.github.gherkin.data.Song;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SongResource extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = Logger.getLogger("Musica.Resource.Song.Post");

        if(!request.getContentType().equalsIgnoreCase("application/json")) {
            response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }
        BufferedReader reader = request.getReader();
        String line;

        if((line = reader.readLine()) == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        logger.log(Level.SEVERE, line);
        Song song;
        try {
            song = Song.fromJSON(line);
        } catch(NumberFormatException e) {
            response.setStatus(422); // 422 Unprocessable Entity
            logger.log(Level.SEVERE, e.getMessage(), e);
            return;
        }

        try {
            DAO.insert(song);
        } catch(SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(song.toJSON().getBytes());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger logger = Logger.getLogger("Musica.Resource.Song.Get");
        String path = request.getPathInfo();
        if(path.endsWith("/"))
            path = path.substring(1, path.length() - 1);
        else
            path = path.substring(1);

        String[] pathParams = path.split("/");

        int id;
        try {
            id = Integer.parseInt(pathParams[0]);

        } catch(NumberFormatException e) {
            response.setStatus(422); // 422 Unprocessable Entity
            logger.log(Level.FINE, e.getMessage(), e);
            return;
        }

        Song song;
        try {
            song = DAO.retrieve(id);
            response.getOutputStream().write(song.toJSON().getBytes());

        } catch(NullPointerException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            logger.log(Level.FINE, e.getMessage(), e);
            return;

        } catch(SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.log(Level.SEVERE, e.getMessage(), e);
            return;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
