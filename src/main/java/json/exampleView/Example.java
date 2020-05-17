package json.exampleView;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the example database table.
 */
@Data
@Entity
@Table(name = "example", schema = "views")
//@NamedQuery(name = "Example.findAll", query = "SELECT e FROM Example e")
public class Example implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "book_id")
    private Integer bookId;

    private String  data;

    @Id
    private Integer id;

    private String info;

}