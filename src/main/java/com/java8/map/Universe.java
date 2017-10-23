package com.java8.map;

public class Universe {
    private Galaxy galaxy;
    private String name;

    public Universe(final String name, final Galaxy galaxy) {
        this.name = name;
        this.galaxy = galaxy;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(final Galaxy galaxy) {
        this.galaxy = galaxy;
    }
}
