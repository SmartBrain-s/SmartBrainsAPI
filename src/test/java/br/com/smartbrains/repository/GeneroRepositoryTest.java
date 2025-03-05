package br.com.smartbrains.repository;

import br.com.smartbrains.config.AbstractTest;
import br.com.smartbrains.model.entity.Genero;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GeneroRepositoryTest extends AbstractTest {

    private String genero;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    GeneroRepository generoRepository;

    @BeforeEach
    void setUp() throws IOException {
        if (genero == null) {
            genero = new String(Files.readAllBytes(Paths.get("src/test/resources/genero/genero.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var generos = generoRepository.findAll();
        assertThat(generos).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        int id = 1;
        var genero = generoRepository.getReferenceById(id);

        assertThat(genero).isNotNull();
        assertThat(genero.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var generoEntity = objectMapper.readValue(genero, Genero.class);

            var saveGenero = generoRepository.save(generoEntity);
            var findGenero = generoRepository.getReferenceById(saveGenero.getId());

            assertThat(saveGenero).isNotNull();
            assertThat(findGenero).isNotNull();
            assertThat(findGenero.getId()).isEqualTo(saveGenero.getId());
        });
    }

    @Test
    @Order(4)
    void findByIdException() {
        int id = 999;
        var findGenero = generoRepository.findById(id);

        assertThrows(EntityNotFoundException.class, () -> findGenero.orElseThrow(EntityNotFoundException::new));
    }
}