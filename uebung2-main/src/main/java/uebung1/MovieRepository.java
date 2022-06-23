package uebung1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uebung1.model.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    List<Movie> findMoviesByName(String name);

}
