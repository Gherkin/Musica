package com.github.gherkin.data;

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

        song.setId(Integer.parseInt(id));
        song.setName(valuePairs[1].split(":")[1]);

        return song;
    }
}
