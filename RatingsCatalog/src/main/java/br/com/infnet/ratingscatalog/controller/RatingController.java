package br.com.infnet.ratingscatalog.controller;

import br.com.infnet.ratingscatalog.model.Rating;
import br.com.infnet.ratingscatalog.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        return ResponseEntity.ok(this.ratingService.saveRating(rating));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(this.ratingService.getAllRatingsByUser(userId));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Rating>> getRatingsByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(this.ratingService.getRatingsByMovie(movieId));
    }

    @GetMapping("/movie/{movieId}/average")
    public ResponseEntity<Double> getAverageRatingByMovieId(@PathVariable Long movieId) {
        return ResponseEntity.ok(this.ratingService.getMovieAverageRating(movieId));
    }

    @GetMapping("/movie/{movieId}/ordered")
    public ResponseEntity<List<Rating>> getRatingsByMovieOrdered(@PathVariable Long movieId) {
        return ResponseEntity.ok(this.ratingService.getByMovieIdOrderByRatingDesc(movieId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable String id) {
        return ResponseEntity.ok(this.ratingService.getRatingById(id));
    }

    @PutMapping
    public ResponseEntity<Rating> updateRating(@RequestBody Rating rating) {
        return ResponseEntity.ok(this.ratingService.updateRating(rating));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable String id) {
        this.ratingService.deleteRating(id);

        return ResponseEntity.noContent().build();
    }
}
