package example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
public class BookJson implements Serializable {

    private String data;
    private Long bookId;
}
