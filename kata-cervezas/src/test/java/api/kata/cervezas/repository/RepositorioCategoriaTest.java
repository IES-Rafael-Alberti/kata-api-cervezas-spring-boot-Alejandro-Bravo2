package api.kata.cervezas.repository;

import api.kata.cervezas.model.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositorioCategoriaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RepositorioCategoria repositorioCategoria;

    @Test
    public void whenFindById_thenReturnCategoria() {
        // given
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria("Test Categoria");
        categoria.setFechaUltimaModificacion(LocalDateTime.now());
        entityManager.persist(categoria);
        entityManager.flush();

        // when
        Optional<Categoria> found = repositorioCategoria.findById(categoria.getIdentificador());

        // then
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getNombreCategoria()).isEqualTo(categoria.getNombreCategoria());
    }
}
