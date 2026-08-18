package br.com.infnet.catalogofilmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CatalogoFilmesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogoFilmesApplication.class, args);
    }

}
