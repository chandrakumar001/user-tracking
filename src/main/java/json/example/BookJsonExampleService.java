package json.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookJsonExampleService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    BookRepository bookRepository;

    public List<BookDTO> getJsons() {

        List<BookDTO> json = getExampleJsonQuery();
        //Sql Injection
        //SELECT * FROM User where userId='ramki'
        //SELECT * FROM User where userId =’sdfssd’ or ‘1’=’1‘
        //Prevent SQL injection
        preventSQLInjection();

        return json;
    }

    public void preventSQLInjection() {
        //PreparedStatement preparedStatement=conn.
        //      prepareStatement('SELECT * FROM  usercheck where username=?') ;
        //preparedStatement.setString(1, user);
    }

    List<BookDTO> getExampleJsonQuery() {
        String s = " SELECT * FROM test.books WHERE data->'published' = 'false'";
        List<BookJson> bookJsons = jdbcTemplate.query(s, new BeanPropertyRowMapper<>(BookJson.class));
        return bookJsons.stream()
                .map(BookJson::getData)
                .map(this::getBookDTO)
                .collect(Collectors.toList());
    }

    private BookDTO getBookDTO(String book) {

        try {
            return objectMapper.readValue(book, BookDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("ok");
        }
    }
}