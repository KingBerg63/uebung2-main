package uebung1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uebung1.model.Movie;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @AfterEach
    public void tearDown() {
        movieRepository.deleteAll();
    }

    @Test
    public void checkFindMoviesByValidName() {
        String name = "Shrek";
        Movie movie = new Movie(name);
        movieRepository.save(movie);
        List<Movie> moviesByName = movieRepository.findMoviesByName(name);

        assertEquals(1, moviesByName.size());
        assertEquals(name, moviesByName.get(0).getName());

        movie = new Movie(name);
        movieRepository.save(movie);
        moviesByName = movieRepository.findMoviesByName(name);
        assertEquals(2, moviesByName.size());
        assertEquals(name, moviesByName.get(1).getName());
    }

    @Test
    public void checkFindMoviesByInvalidName() {
        Movie movie = new Movie("Shrek");
        movieRepository.save(movie);
        List<Movie> moviesByName = movieRepository.findMoviesByName("Sherk");

        assertTrue(moviesByName.isEmpty());
    }
}