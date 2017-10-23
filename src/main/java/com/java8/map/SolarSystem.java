package com.java8.map;

public class SolarSystem {
    private String name;
    private Earth earth;

    public SolarSystem(final String name, final Earth earth) {
        this.name = name;
        this.earth = earth;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Earth getEarth() {
        return earth;
    }

    public void setEarth(final Earth earth) {
        this.earth = earth;
    }
}
