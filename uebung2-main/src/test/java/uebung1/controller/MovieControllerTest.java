package uebung1.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uebung1.model.Movie;
import uebung1.service.MovieService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    private MovieController movieController;


    @BeforeEach
    public void setUp(){
        movieController = new MovieController(movieService);
    }

    @Test
    public void checkGetMovies() {
        when(movieService.getMovies()).thenReturn(Collections.emptyList());
        ResponseEntity<List<Movie>> response = movieController.getMovies();
        verify(movieService).getMovies();

        List<Movie> movieList = response.getBody();
        assert movieList != null;
        assertTrue(movieList.isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void checkGetMovieByValidId() {
        when(movieService.getMovieById(0)).thenReturn(new Movie("Super Film"));
        ResponseEntity<Movie> response = movieController.getMovieById(0);
        verify(movieService).getMovieById(0);

        Movie movie = response.getBody();
        assert movie != null;
        assertEquals("Super Film", movie.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void checkGetMovieByInvalidId() {
        when(movieService.getMovieById(10)).thenReturn(null);
        ResponseEntity<Movie> response = movieController.getMovieById(10);
        verify(movieService).getMovieById(10);

        Movie movie = response.getBody();
        assertNull(movie);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void checkGetMovieByName() {
        String movie = "Nicht Vorhandener Film";
        when(movieService.getMoviesByName(movie)).thenReturn(Collections.emptyList());
        ResponseEntity<List<Movie>> response = movieController.getMoviesByName(movie);
        verify(movieService).getMoviesByName(movie);

        List<Movie> movieList = response.getBody();
        assert movieList != null;
        assertTrue(movieList.isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void checkPostValidMovie() {
        Movie movie = new Movie("Top-Gun");
        ResponseEntity<Movie> response = movieController.postMovie(movie);
        verify(movieService).postMovie(movie);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void checkPostInvalidMovie() {
        Movie movie = new Movie(null);
        ResponseEntity<Movie> response = movieController.postMovie(movie);
        verify(movieService, never()).postMovie(movie);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}