package api.kata.cervezas.repository;

import api.kata.cervezas.model.Fabricante;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositorioFabricanteTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RepositorioFabricante repositorioFabricante;

    @Test
    public void whenFindById_thenReturnFabricante() {
        // given
        Fabricante fabricante = new Fabricante();
        fabricante.setNombreFabricante("Test Fabricante");
        fabricante.setDireccionPrincipal("Direccion Principal");
        fabricante.setDireccionSecundaria("Direccion Secundaria");
        fabricante.setCiudad("Ciudad");
        fabricante.setProvincia("Provincia");
        fabricante.setCodigoPostal("12345");
        fabricante.setPais("Pais");
        fabricante.setNumeroTelefono("123456789");
        fabricante.setSitioWeb("http://test.com");
        fabricante.setRutaImagen("images/test.jpg");
        fabricante.setDescripcionFabricante("Descripcion");
        fabricante.setUsuarioCreador(0);
        fabricante.setFechaUltimaModificacion(LocalDateTime.now());
        entityManager.persist(fabricante);
        entityManager.flush();

        // when
        Optional<Fabricante> found = repositorioFabricante.findById(fabricante.getIdentificador());

        // then
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getNombreFabricante()).isEqualTo(fabricante.getNombreFabricante());
    }
}
