package example;


import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class BookJsonExampleService {

    @NonNull JdbcTemplate jdbcTemplate;
    @NonNull CommonJsonMapper commonJsonMapper;

    List<BookDTO> getExampleJsonQuery() {

        String s = " SELECT * FROM test.books WHERE data->'published' = 'false'";
        List<BookJson> bookJsons = jdbcTemplate.query(s, new BeanPropertyRowMapper<>(BookJson.class));
        return bookJsons.stream()
                .map(BookJson::getData)
                .map(this::mapToBook)
                .map(BookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }

    private Book mapToBook(String book) {
        return commonJsonMapper.convertToEntity(book, Book.class);
    }

   /* private Book mapToBook(String book) {

        try {
            return objectMapper.readValue(book, Book.class);
        } catch (IOException e) {
            throw new RuntimeException("ok");
        }
    }*/


}