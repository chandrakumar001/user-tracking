package com.example.ecom.jsonmapper.file;

import com.example.ecom.jsonmapper.exception.JacksonException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Slf4j
public class JacksonFileJsonMapper implements FileJsonMapper {

    private final ObjectMapper objectMapper;

    @Autowired
    public JacksonFileJsonMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> List<T> convertToObject(final String file, final TypeReference<List<T>> t) {

        try {

            final InputStream is = JacksonFileJsonMapper.class.getResourceAsStream(file);
            final List<T> readValue = objectMapper.readValue(is, t);
            if (readValue == null || readValue.isEmpty()) {
                throw new JacksonException("Object is null or empty");
            }
            return readValue;
        } catch (JsonGenerationException | JsonMappingException e) {
            log.error(String.valueOf(e));
            throw new JacksonException("Exception occurs in Json Mapping or Generation ");
        } catch (IOException e) {
            log.error(String.valueOf(e));
            throw new JacksonException("file not found");
        }
    }

    @Override
    public <T> T convertToObject(final String file, final Class<T> t) {

        try {

            final InputStream is = JacksonFileJsonMapper.class.getResourceAsStream(file);
            final T readValue = objectMapper.readValue(is, t);
            if (readValue == null) {
                throw new JacksonException("Object is null or empty");
            }
            return readValue;
        } catch (JsonGenerationException | JsonMappingException e) {
            log.error(String.valueOf(e));
            throw new JacksonException("Exception occurs in Json Mapping or Generation ");
        } catch (IOException e) {
            log.error(String.valueOf(e));
            throw new JacksonException("file not found");
        }
    }
}
