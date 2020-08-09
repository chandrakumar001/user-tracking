package com.example.ecom.jsonmapper;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface JsonMapper {

    <T> T stringToObject(String jsonString, Class<T> clazz);

    <T> List<T> stringToObjects(String jsonString, final TypeReference<List<T>> t);

}
