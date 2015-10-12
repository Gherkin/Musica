package com.github.gherkin.data;

public class Song {
    private Integer id;
    private String name;

    public Song(String name) {
        id = null;
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
}
