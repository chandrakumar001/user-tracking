package com.example.ecom.jsonmapper;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface JsonObject {

    <T> T convertToObject(final String file, final Class<T> t);

    <T> List<T> convertToObject(final String file, final TypeReference<List<T>> t);

    <T> void saveObject(final String file, final Object t);
}
