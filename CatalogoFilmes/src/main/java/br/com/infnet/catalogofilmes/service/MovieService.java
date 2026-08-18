package br.com.infnet.catalogofilmes.service;

import br.com.infnet.catalogofilmes.model.Movie;
import br.com.infnet.catalogofilmes.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie saveMovie(Movie movie) {
        return this.movieRepository.save(movie);
    }

    public List<Movie> getMovies() {
        return this.movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return this.movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Movie not found with id %d", id)));
    }

    public Movie updateMovie(Movie movie) {
        getMovieById(movie.getId());

        return this.movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        getMovieById(id);

        this.movieRepository.deleteById(id);
    }
}
