package uebung1.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;


@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @TableGenerator(
            name = "movie_sequence",
            allocationSize = 1,
            initialValue = -1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "movie_sequence"
    )
    @Column(
            updatable = false
    )
    private int id;

    @Column(
            nullable = false
    )
    private String name;

    public Movie() {}

    public Movie(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
