package com.java8.mapreduce;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MapReduceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testMapReduce_NullList() {
        expectedException.expect(NullPointerException.class);

        final List<ValidationError> validationErrors = null;
        validationErrors
                .stream()
                .map(validationError -> String.format("{attribute=%s, code=%s, message=%s} ", validationError.getAttribute(), validationError.getCode(), validationError.getMessage()))
                .reduce("Validation errors are ", (firstMessage, secondMessage) -> firstMessage + secondMessage);
    }

    @Test
    public void testMapReduce_EmptyList() {
        final List<ValidationError> validationErrors = emptyList();

        final String validationErrorMessage = validationErrors
                .stream()
                .map(validationError -> String.format("{attribute=%s, code=%s, message=%s} ", validationError.getAttribute(), validationError.getCode(), validationError.getMessage()))
                .reduce("Validation errors are ", (firstMessage, secondMessage) -> firstMessage + secondMessage);
        assertThat(validationErrorMessage, is("Validation errors are "));
    }

    @Test
    public void testMapReduce_severalObjectsInTheList() {
        final List<ValidationError> validationErrors = new LinkedList<ValidationError>() {{
            add(new ValidationError("phone_number", "TOO_LONG", "The phone number is too long"));
            add(new ValidationError("phone_number", "WRONG_AREA_CODE", "The area code is wrong"));
            add(new ValidationError("phone_number", "INVALID_FORMAT", "The phone number is not correctly formatted"));
        }};

        final String validationErrorMessage = validationErrors
                .stream()
                .map(validationError -> String.format("{attribute=%s, code=%s, message=%s} ", validationError.getAttribute(), validationError.getCode(), validationError.getMessage()))
                .reduce("Validation errors are ", (firstMessage, secondMessage) -> firstMessage + secondMessage);
        System.out.println(validationErrorMessage);
    }

    private class ValidationError {
        String attribute;
        String code;
        String message;

        ValidationError(final String attribute, final String code, final String message) {
            this.attribute = attribute;
            this.code = code;
            this.message = message;
        }

        String getAttribute() {
            return attribute;
        }
        String getCode() {
            return code;
        }
        String getMessage() {
            return message;
        }
    }
}
