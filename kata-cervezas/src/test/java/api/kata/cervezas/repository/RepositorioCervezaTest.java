package api.kata.cervezas.repository;

import api.kata.cervezas.model.Cerveza;
import api.kata.cervezas.model.Fabricante;
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
public class RepositorioCervezaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RepositorioCerveza repositorioCerveza;
    
    @Autowired
    private RepositorioFabricante repositorioFabricante;
    
    @Autowired
    private RepositorioCategoria repositorioCategoria;
    
    @Autowired
    private RepositorioEstilo repositorioEstilo;

    @Test
    public void whenFindById_thenReturnCerveza() {
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

        Categoria categoria = new Categoria();
        categoria.setNombreCategoria("Test Categoria");
        categoria.setFechaUltimaModificacion(LocalDateTime.now());
        entityManager.persist(categoria);

        Estilo estilo = new Estilo();
        estilo.setNombreEstilo("Test Estilo");
        estilo.setCategoriaPadre(categoria);
        estilo.setFechaUltimaModificacion(LocalDateTime.now());
        entityManager.persist(estilo);

        Cerveza cerveza = new Cerveza();
        cerveza.setNombreProducto("Test Beer");
        cerveza.setFabricante(fabricante);
        cerveza.setCategoriaCerveza(categoria);
        cerveza.setEstiloProducto(estilo);
        cerveza.setGraduacionAlcoholica(5.0f);
        cerveza.setAmargorIbu(50.0f);
        cerveza.setColorSrm(10.0f);
        cerveza.setCodigoBarras(123456789);
        cerveza.setRutaImagen("images/test.jpg");
        cerveza.setDescripcionProducto("Test Description");
        cerveza.setUsuarioCreador(0);
        cerveza.setFechaUltimaModificacion(LocalDateTime.now());
        entityManager.persist(cerveza);
        entityManager.flush();

        // when
        Optional<Cerveza> found = repositorioCerveza.findById(cerveza.getIdentificador());

        // then
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getNombreProducto()).isEqualTo(cerveza.getNombreProducto());
    }
}
