package example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class Book {

    private String title;
    private List<String> genres;
    private boolean published = true;
}