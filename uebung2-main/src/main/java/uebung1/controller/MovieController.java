package uebung1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uebung1.model.Movie;
import uebung1.service.MovieService;

import java.util.List;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(){
        return new ResponseEntity<>(movieService.getMovies(), HttpStatus.OK);
    }


    @GetMapping(value = "/{id:\\d+}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") int id){
        Movie movie = movieService.getMovieById(id);
        if (movie == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }


    @RequestMapping(value = "/{name}")
    public ResponseEntity<List<Movie>> getMoviesByName(@PathVariable("name") String name){
        return new ResponseEntity<>(movieService.getMoviesByName(name), HttpStatus.OK);
    }
    

    @PostMapping
    public ResponseEntity<Movie> postMovie(@RequestBody Movie movie){
        if (movie.getName() == null || movie.getName().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        movieService.postMovie(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

