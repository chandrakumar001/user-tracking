package json.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class BookJson implements Serializable {

    private String data;
    @Id
    private Long bookId;
}
