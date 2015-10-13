package com.github.gherkin.resources;

import com.github.gherkin.data.Song;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

public class SongResource extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getContentType() != "application/json") {
            response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        BufferedReader reader = request.getReader();
        String line;

        if((line = reader.readLine()) == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Song song;
        try {
            song = Song.fromJSON(line);
        } catch(NumberFormatException exception) {
            response.setStatus(422); // 422 Unprocessable Entity
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(song.toJSON().getBytes());
    }
}
