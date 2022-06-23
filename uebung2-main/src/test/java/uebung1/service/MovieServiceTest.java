package uebung1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uebung1.MovieRepository;
import uebung1.model.Movie;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService(movieRepository);
    }

    @Test
    public void checkGetMovies() {
        movieService.getMovies();
        verify(movieRepository).findAll();
    }

    @Test
    public void checkGetMovieByValidId() {
        when(movieRepository.findById(0)).thenReturn(Optional.of(new Movie("King Kong")));
        Movie movieById = movieService.getMovieById(0);

        verify(movieRepository).findById(0);
        assertEquals("King Kong", movieById.getName());
    }

    @Test
    public void checkGetMovieByInvalidId() {
        when(movieRepository.findById(10)).thenReturn(Optional.empty());
        Movie movieById = movieService.getMovieById(10);

        verify(movieRepository).findById(10);
        assertNull(movieById);
    }

    @Test
    public void checkGetMovieByName() {
        movieService.getMoviesByName("Cars");
        verify(movieRepository).findMoviesByName("Cars");
    }

    @Test
    public void checkPostMovie() {
        Movie movie = new Movie("King Kong");
        movieService.postMovie(movie);
        ArgumentCaptor<Movie> movieArgumentCaptor = ArgumentCaptor.forClass(Movie.class);

        verify(movieRepository).save(movieArgumentCaptor.capture());

        Movie capturedMovie = movieArgumentCaptor.getValue();
        assertEquals(capturedMovie, movie);
    }
}
