package com.example.ecom.jsonmapper.string;

import com.example.ecom.jsonmapper.exception.JacksonException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class JacksonStringJsonMapper implements StringJsonMapper {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public <T> String stringToObject(Object jsonString) {

        try {
            return objectMapper.writeValueAsString(jsonString);
        } catch (JsonGenerationException | JsonMappingException e) {
            log.error(String.valueOf(e));
            throw new JacksonException("Exception occurs in Json Mapping or Generation ");
        } catch (IOException e) {
            log.error(String.valueOf(e));
            throw new JacksonException("file not found");
        }
    }
}
