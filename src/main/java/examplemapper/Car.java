package examplemapper;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Car {

    private long id;
    private String make;
    private int numOfSeats;
    private Date releaseDate;
    private Engine engine;

}