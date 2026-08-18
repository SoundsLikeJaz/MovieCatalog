package br.com.infnet.catalogofilmes.repository;

import br.com.infnet.catalogofilmes.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
