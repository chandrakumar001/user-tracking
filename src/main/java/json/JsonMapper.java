package json;

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
public class JsonMapper {

    private final ObjectMapper objectMapper;

    @Autowired
    public JsonMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> List<T> convertToObject(final String file, final TypeReference<List<T>> t) {

        try {

            final InputStream is = JsonMapper.class.getResourceAsStream(file);
            final List<T> readValue = objectMapper.readValue(is, t);
            if (readValue == null || readValue.isEmpty()) {
                throw new RuntimeException("Object is null or empty");
            }
            return readValue;
        } catch (JsonGenerationException | JsonMappingException e) {
            log.error(String.valueOf(e));
            throw new RuntimeException("Exception occurs in Json Mapping or Generation ");
        } catch (IOException e) {
            log.error(String.valueOf(e));
            throw new RuntimeException("file not found");
        }
    }
    public <T> T convertToObject(final String file, final Class<T> t) {

        try {

            final InputStream is = JsonMapper.class.getResourceAsStream(file);
            final T readValue = objectMapper.readValue(is, t);
            if (readValue == null) {
                throw new RuntimeException("Object is null or empty");
            }
            return readValue;
        } catch (JsonGenerationException | JsonMappingException e) {
            log.error(String.valueOf(e));
            throw new RuntimeException("Exception occurs in Json Mapping or Generation ");
        } catch (IOException e) {
            log.error(String.valueOf(e));
            throw new RuntimeException("file not found");
        }
    }
}
