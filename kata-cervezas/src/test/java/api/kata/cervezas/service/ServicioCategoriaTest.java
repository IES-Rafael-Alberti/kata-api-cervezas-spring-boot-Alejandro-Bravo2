package api.kata.cervezas.service;

import api.kata.cervezas.dto.CategoriaDTO;
import api.kata.cervezas.exception.ResourceNotFoundException;
import api.kata.cervezas.mapper.MapeadorCategoria;
import api.kata.cervezas.model.Categoria;
import api.kata.cervezas.repository.RepositorioCategoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServicioCategoriaTest {

    @Mock
    private RepositorioCategoria repositorioCategoria;

    @Mock
    private MapeadorCategoria mapeadorCategoria;

    @InjectMocks
    private ServicioCategoria servicioCategoria;

    private Categoria categoria;
    private CategoriaDTO categoriaDTO;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setIdentificador(1);
        categoria.setNombreCategoria("Test Categoria");

        categoriaDTO = new CategoriaDTO();
        categoriaDTO.setIdentificador(1);
        categoriaDTO.setNombreCategoria("Test Categoria");
    }

    @Test
    void whenObtenerTodasLasCategorias_thenReturnCategoriaList() {
        when(repositorioCategoria.findAll()).thenReturn(Collections.singletonList(categoria));
        when(mapeadorCategoria.convertirADTO(any(Categoria.class))).thenReturn(categoriaDTO);

        List<CategoriaDTO> result = servicioCategoria.obtenerTodasLasCategorias();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombreCategoria()).isEqualTo("Test Categoria");
    }

    @Test
    void whenBuscarPorIdentificador_withValidId_thenReturnCategoria() {
        when(repositorioCategoria.findById(1)).thenReturn(Optional.of(categoria));
        when(mapeadorCategoria.convertirADTO(any(Categoria.class))).thenReturn(categoriaDTO);

        CategoriaDTO result = servicioCategoria.buscarPorIdentificador(1);

        assertThat(result.getNombreCategoria()).isEqualTo("Test Categoria");
    }

    @Test
    void whenBuscarPorIdentificador_withInvalidId_thenThrowException() {
        when(repositorioCategoria.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            servicioCategoria.buscarPorIdentificador(1);
        });
    }
}
