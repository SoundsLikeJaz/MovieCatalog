package br.com.infnet.ratingscatalog.service;

import br.com.infnet.ratingscatalog.model.Rating;
import br.com.infnet.ratingscatalog.repository.RatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> getAllRatingsByUser(Long userId) {
        return this.ratingRepository.findByUserId(userId);
    }

    public List<Rating> getRatingsByMovie(Long movieId) {
        return this.ratingRepository.findByMovieId(movieId);
    }

    public List<Rating> getByMovieIdOrderByRatingDesc(Long movieId) {
        return this.ratingRepository.findByMovieIdOrderByRatingDesc(movieId);
    }

    public Double getMovieAverageRating(Long movieId) {
        return this.ratingRepository.getAverageRatingByMovieId(movieId);
    }

    public Rating getRatingById(String ratingId) {
        return this.ratingRepository.findById(ratingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Rating not found with id %s", ratingId)));
    }

    public Rating updateRating(Rating rating) {
        getRatingById(rating.getId());

        return this.ratingRepository.save(rating);
    }

    public void deleteRating(String ratingId) {
        getRatingById(ratingId);

        this.ratingRepository.deleteById(ratingId);
    }
}
