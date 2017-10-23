package com.java8.map;

public class Galaxy {
    private String name;
    private SolarSystem solarSystem;

    public Galaxy(final String name, final SolarSystem solarSystem) {
        this.name = name;
        this.solarSystem = solarSystem;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(final SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }
}
