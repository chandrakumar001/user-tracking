package json;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@ApiIgnore
public class JsonExampleRestController {


    @Autowired
    JsonMapper jsonMapper;


    @GetMapping("/example")
    public ResponseEntity<List<ExampleJson>> getJsons() {
        List<ExampleJson> json = getAvailablePstoNumbers();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/example/{id}")
    public ResponseEntity<ExampleJson> getJsons(@PathVariable("id") int id) {

        ExampleJson json = getAvailablePstoNumber(id);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    public ExampleJson getAvailablePstoNumber(int id) {

        List<ExampleJson> json = getAvailablePstoNumbers();

        return json.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found Id"));
    }

    public List<ExampleJson> getAvailablePstoNumbers() {

        final String path = "/json/example/example.json";
        final TypeReference<List<ExampleJson>> typeRef = new TypeReference<>() {
        };
        return jsonMapper.convertToObject(path, typeRef);
    }
}

@Data
class ExampleJson {

    private int id;
    private String name;
}
