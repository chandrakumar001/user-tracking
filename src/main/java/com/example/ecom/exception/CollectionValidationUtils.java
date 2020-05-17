package com.example.ecom.exception;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Map;

public class CollectionValidationUtils {

    private static final String ERROR_MUST_NOT_BE_EMPTY_OR_NULL = "'error' must not be empty or null";
    private static final String MAP_MUST_NOT_BE_EMPTY_OR_NULL = "'map' must not be empty or null";

    private CollectionValidationUtils() {
        throw new UnsupportedOperationException("CollectionValidationUtils class");
    }

    public static void notEmpty(final Object object,
                                final String message) {

        if (ObjectUtils.isEmpty(object)) {
            throw new FieldValidationException(message);
        }
    }

    public static void notEmpty(final Collection<?> collection,
                                final String message) {

        if (CollectionUtils.isEmpty(collection)) {
            throw new FieldValidationException(message);
        }
    }

    public static void notEmpty(final Map<String, String> map,
                                final String message) {

        if (CollectionUtils.isEmpty(map)) {
            throw new FieldValidationException(message);
        }
    }
}