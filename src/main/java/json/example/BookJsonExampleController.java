package json.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookJsonExampleController {
    @Autowired
    BookJsonExampleService jsonMapper;

    @GetMapping("/example1")
    public ResponseEntity<List<BookDTO>> getJsons() {
        List<BookDTO> json =jsonMapper. getExampleJsonQuery();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

}