package br.com.infnet.catalogofilmes.controller;

import br.com.infnet.catalogofilmes.client.RatingsCatalogClient;
import br.com.infnet.catalogofilmes.dto.RatingDTO;
import br.com.infnet.catalogofilmes.model.Movie;
import br.com.infnet.catalogofilmes.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class MovieController {

    private final MovieService movieService;
    private final RatingsCatalogClient ratingsCatalogClient;

    public MovieController(MovieService movieService, RatingsCatalogClient ratingsCatalogClient) {
        this.movieService = movieService;
        this.ratingsCatalogClient = ratingsCatalogClient;
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(this.movieService.saveMovie(movie));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(this.movieService.getMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(this.movieService.getMovieById(id));
    }

    @GetMapping("/{id}/ratings")
    public ResponseEntity<List<RatingDTO>> getMovieRatings(@PathVariable Long id) {
        return ResponseEntity.ok(
                this.ratingsCatalogClient.getMovieRatings(id)
        );
    }

    @GetMapping("/{id}/ratings/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long id) {
        return ResponseEntity.ok(
                this.ratingsCatalogClient.getAverageRating(id)
        );
    }

    @GetMapping("/{id}/ratings/ordered")
    public ResponseEntity<List<RatingDTO>> getOrderedRatings(@PathVariable Long id) {
        return ResponseEntity.ok(
                this.ratingsCatalogClient.getOrderedRatings(id)
        );
    }

    @PutMapping
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(this.movieService.updateMovie(movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        this.movieService.deleteMovie(id);

        return ResponseEntity.noContent().build();
    }
}
