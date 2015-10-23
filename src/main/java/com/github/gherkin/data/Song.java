package com.github.gherkin.data;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Song {
    private Integer id;
    private String name;

    public Song() {

    }

    public Song(String name) {
        id = null;
        this.name = name;
    }

    public Song(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toJSON() {
        String result = "";

        result += "{";
        result += "\"id\":" + id + ",";
        result += "\"name\":" + name + "}";

        return result;
    }

    public static Song fromJSON(String json) {
        Song song = new Song();

        int lastIndex = json.length() - 1;
        json = json.substring(1, lastIndex);

        String valuePairs[] = json.split(",");

        String id = valuePairs[0].split(":")[1];


        if(id.equals("null")) {
            song.setId(null);
        } else
            song.setId(Integer.parseInt(id));

        String name = valuePairs[1].split(":")[1];
        name = name.substring(1, name.length() - 1);
        song.setName(name);

        return song;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj.getClass() != Song.class)
            return false;

        Song song = (Song) obj;
        
        if(this.getId() == song.getId() && this.getName() == song.getName())
            return true;
        else
            return false;
    }
}
