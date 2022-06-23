package uebung1.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {

    private Movie movie;

    @BeforeEach
    public void setUp(){
        movie = new Movie("Top-Gun");
    }

    @Test
    void checkGetName() {
        assertEquals("Top-Gun", movie.getName());
    }

    @Test
    void checkSetId() {
        movie.setId(10);
        assertEquals(10, movie.getId());
    }


    @Test
    void checkGetId() {
        assertEquals(0, movie.getId());
    }


    @Test
    void checkSetName() {
        movie.setName("Who am I");
        assertEquals("Who am I", movie.getName());
    }
}
