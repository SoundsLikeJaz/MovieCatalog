package br.com.infnet.catalogofilmes.client;

import br.com.infnet.catalogofilmes.dto.RatingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RatingsCatalog")
public interface RatingsCatalogClient {
    @GetMapping("/ratings/movie/{movieId}")
    List<RatingDTO> getMovieRatings(@PathVariable Long movieId);

    @GetMapping("/ratings/movie/{movieId}/average")
    Double getAverageRating(@PathVariable Long movieId);

    @GetMapping("/ratings/movie/{movieId}/ordered")
    List<RatingDTO> getOrderedRatings(@PathVariable Long movieId);
}
