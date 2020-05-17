package example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommonJsonMapper implements JsonMapper {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public <T> T convertToEntity(final String object, final Class<T> aClass) {

        try {
            return objectMapper.readValue(object, aClass);
        } catch (IOException e) {
            throw new RuntimeException("ok");
        }
    }
}
