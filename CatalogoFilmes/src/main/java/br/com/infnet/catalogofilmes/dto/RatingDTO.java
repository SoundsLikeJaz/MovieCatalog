package br.com.infnet.catalogofilmes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
    private Long userId;
    private Long movieId;
    private Integer rating;
    private String comment;
    private Instant createdAt;
}
