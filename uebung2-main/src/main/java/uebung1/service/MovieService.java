package uebung1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uebung1.MovieRepository;
import uebung1.model.Movie;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieById(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()){
            return null;
        }
        return movie.get();
    }

    public List<Movie> getMoviesByName(String name) {
        return movieRepository.findMoviesByName(name);
    }

    public void postMovie(Movie movie){
        movieRepository.save(movie);
    }


}
