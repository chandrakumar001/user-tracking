package com.example.ecom.jsonmapper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Component
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class GenericJsonObject implements JsonObject {

    private static final String JSON = ".json";
    private static final String PATH_JSON = "/json/";
    private static final String OBJECT_IS_NULL = "Object is null";
    private static final String EXCEPTION_OCCURS_IN_JSON_MAPPING_OR_GENERATION = "Exception occurs in Json Mapping or Generation ";
    private static final String FILE_NOT_FOUND = "file not found";

    @NonNull ObjectMapper objectMapper;

    @Override
    public <T> T convertToObject(final String file, final Class<T> t) {

        try {

            final InputStream is = GenericJsonObject.class.getResourceAsStream(PATH_JSON + file + JSON);
            final T readValue = objectMapper.readValue(is, t);
            if (readValue == null) {
                throw new JsonExceptipon(OBJECT_IS_NULL);
            }
            return readValue;
        } catch (JsonGenerationException | JsonMappingException e) {
            throw new JsonExceptipon(EXCEPTION_OCCURS_IN_JSON_MAPPING_OR_GENERATION);
        } catch (IOException e) {
            throw new JsonExceptipon(FILE_NOT_FOUND);
        }
    }

    @Override
    public <T> List<T> convertToObject(final String file, final TypeReference<List<T>> t) {

        try {

            final InputStream is = GenericJsonObject.class.getResourceAsStream(PATH_JSON + file + JSON);
            final List<T> readValue = objectMapper.readValue(is, t);
            if (readValue == null || readValue.isEmpty()) {
                throw new JsonExceptipon(OBJECT_IS_NULL);
            }
            return readValue;
        } catch (JsonGenerationException | JsonMappingException e) {
            throw new JsonExceptipon(EXCEPTION_OCCURS_IN_JSON_MAPPING_OR_GENERATION);
        } catch (IOException e) {
            throw new JsonExceptipon(FILE_NOT_FOUND);
        }
    }

    @Override
    public <T> void saveObject(final String file, final Object t) {

        try {

            objectMapper.writeValue(new File(file), t);
        } catch (IOException e) {

            throw new JsonExceptipon("IOException");
        }
    }
}
