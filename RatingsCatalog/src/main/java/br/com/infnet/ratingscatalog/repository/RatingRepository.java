package br.com.infnet.ratingscatalog.repository;

import br.com.infnet.ratingscatalog.model.Rating;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {
    List<Rating> findByUserId(Long userId);

    List<Rating> findByMovieId(Long movieId);

    List<Rating> findByMovieIdOrderByRatingDesc(Long movieId);

    @Aggregation(pipeline = {
            "{ '$match': { 'movieId': ?0 } }",
            "{ '$group': { '_id': '$movieId', 'averageRating': { '$avg': '$rating' } } }"
    })
    Double getAverageRatingByMovieId(Long movieId);
}
