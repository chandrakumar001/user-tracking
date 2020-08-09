package com.example.ecom.jsonmapper;

import com.example.ecom.jsonmapper.exception.JacksonException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class JacksonJsonMapper implements JsonMapper {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public <T> T stringToObject(String jsonString, Class<T> clazz) {

        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (JsonGenerationException | JsonMappingException e) {
            log.error(String.valueOf(e));
            throw new JacksonException("Exception occurs in Json Mapping or Generation ");
        } catch (IOException e) {
            log.error(String.valueOf(e));
            throw new JacksonException("IO not found");
        }
    }

    @Override
    public <T> List<T> stringToObjects(String jsonString, TypeReference<List<T>> t) {

        try {

            final List<T> readValue = objectMapper.readValue(jsonString, t);
            if (readValue == null || readValue.isEmpty()) {
                throw new JacksonException("Object is null or empty");
            }
            return readValue;
        } catch (JsonGenerationException | JsonMappingException e) {
            log.error(String.valueOf(e));
            throw new JacksonException("Exception occurs in Json Mapping or Generation ");
        } catch (IOException e) {
            log.error(String.valueOf(e));
            throw new JacksonException("IO not found");
        }
    }
}
