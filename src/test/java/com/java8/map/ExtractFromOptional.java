package com.java8.map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ExtractFromOptional {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testGetOnNullValue_Error() {
        final IntegerHolder integerHolder = new IntegerHolder(null);

        expectedException.expect(NoSuchElementException.class);

        Optional.of(integerHolder)
                .map(IntegerHolder::getIntValue)
                .get(); //will throw NoSuchElementException
    }

    @Test
    public void testOrElse_nullValue() {
        final Integer defaultValueIfNotPresent = -100;
        final IntegerHolder integerHolder = new IntegerHolder(null);

        final Integer actual = Optional.of(integerHolder)
                .map(IntegerHolder::getIntValue)
                .orElse(defaultValueIfNotPresent);

        assertThat(actual, is(defaultValueIfNotPresent));
    }

    @Test
    public void testOrElse_valuePresent() {
        final Integer value = 100;
        final Integer defaultValueIfNotPresent = -100;
        final IntegerHolder integerHolder = new IntegerHolder(value);

        final Integer actual = Optional.of(integerHolder)
                .map(IntegerHolder::getIntValue)
                .orElse(defaultValueIfNotPresent); //Must not hit this

        assertThat(actual, is(value));
    }

    @Test
    public void testOrElseGet_nullValue() {
        final Integer defaultValueIfNotPresent = -100;
        final IntegerHolder integerHolder = new IntegerHolder(null);

        final Integer actual = Optional.of(integerHolder)
                .map(IntegerHolder::getIntValue)
                .orElseGet(() -> defaultValueIfNotPresent);

        assertThat(actual, is(defaultValueIfNotPresent));
    }

    @Test
    public void testOrElseGet_valuePresent() {
        final Integer value = 100;
        final Integer defaultValueIfNotPresent = -100;
        final IntegerHolder integerHolder = new IntegerHolder(value);

        final Integer actual = Optional.of(integerHolder)
                .map(IntegerHolder::getIntValue)
                .orElseGet(() -> defaultValueIfNotPresent); //Must not hit this

        assertThat(actual, is(value));
    }

    private class IntegerHolder {
        IntegerHolder(final Integer intValue) {
            this.intValue = intValue;
        }

        Integer getIntValue() {
            return intValue;
        }

        Integer intValue;
    }
}
