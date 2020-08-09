package com.example.ecom.jsonmapper.file;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface FileJsonMapper {


    <T> List<T> convertToObject(final String file, final TypeReference<List<T>> t);

    <T> T convertToObject(final String file, final Class<T> t);
}
