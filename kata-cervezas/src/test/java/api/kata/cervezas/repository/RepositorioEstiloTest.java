package api.kata.cervezas.repository;

import api.kata.cervezas.model.Categoria;
import api.kata.cervezas.model.Estilo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositorioEstiloTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RepositorioEstilo repositorioEstilo;

    @Test
    public void whenFindById_thenReturnEstilo() {
        // given
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria("Test Categoria");
        categoria.setFechaUltimaModificacion(LocalDateTime.now());
        entityManager.persist(categoria);

        Estilo estilo = new Estilo();
        estilo.setNombreEstilo("Test Estilo");
        estilo.setCategoriaPadre(categoria);
        estilo.setFechaUltimaModificacion(LocalDateTime.now());
        entityManager.persist(estilo);
        entityManager.flush();

        // when
        Optional<Estilo> found = repositorioEstilo.findById(estilo.getIdentificador());

        // then
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getNombreEstilo()).isEqualTo(estilo.getNombreEstilo());
    }
}
