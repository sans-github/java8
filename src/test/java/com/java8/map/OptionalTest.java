package com.java8.map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OptionalTest {

    private Universe universe;
    private Galaxy galaxy;
    private SolarSystem solarSystem;
    private Earth earth;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        earth = new Earth("earth");
        solarSystem = new SolarSystem("solarSystem", earth);
        galaxy = new Galaxy("galaxy", solarSystem);
        universe = new Universe("universe", galaxy);
    }

    @Test
    public void universeIsNull() {
        expectedException.expect(NullPointerException.class);
        createOptional(null);
    }

    @Test
    public void galaxyIsNull() {
        universe.setGalaxy(null);

        final Optional<Earth> optional = createOptional(universe);

        final String actualCoreName = optional
                .map(Earth::getName)
                .orElse(null);

        assertThat(actualCoreName, is(nullValue()));
    }

    @Test
    public void solarSystemIsNull() {
        galaxy.setSolarSystem(null);

        final Optional<Earth> optional = createOptional(universe);

        final String actualCoreName = optional
                .map(Earth::getName)
                .orElse(null);

        assertThat(actualCoreName, is(nullValue()));
    }

    @Test
    public void earthIsNull() {
        solarSystem.setEarth(null);

        final Optional<Earth> optional = createOptional(universe);

        final String actualCoreName = optional
                .map(Earth::getName)
                .orElse(null);

        assertThat(actualCoreName, is(nullValue()));
    }

    @Test
    public void earthIsNotNull() {
        final Optional<Earth> optional = createOptional(universe);

        final String actualCoreName = optional
                .map(Earth::getName)
                .orElse(null);

        assertThat(actualCoreName, is("earth"));
    }

    private Optional<Earth> createOptional(final Universe universe) {
        return Optional
                .of(universe)
                .map(Universe::getGalaxy)
                .map(Galaxy::getSolarSystem)
                .map(SolarSystem::getEarth);
    }
}
