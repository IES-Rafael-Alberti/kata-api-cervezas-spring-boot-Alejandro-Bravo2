package api.kata.cervezas.service;

import api.kata.cervezas.dto.FabricanteDTO;
import api.kata.cervezas.exception.ResourceNotFoundException;
import api.kata.cervezas.mapper.MapeadorFabricante;
import api.kata.cervezas.model.Fabricante;
import api.kata.cervezas.repository.RepositorioFabricante;
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
public class ServicioFabricanteTest {

    @Mock
    private RepositorioFabricante repositorioFabricante;

    @Mock
    private MapeadorFabricante mapeadorFabricante;

    @InjectMocks
    private ServicioFabricante servicioFabricante;

    private Fabricante fabricante;
    private FabricanteDTO fabricanteDTO;

    @BeforeEach
    void setUp() {
        fabricante = new Fabricante();
        fabricante.setIdentificador(1);
        fabricante.setNombreFabricante("Test Fabricante");

        fabricanteDTO = new FabricanteDTO();
        fabricanteDTO.setIdentificador(1);
        fabricanteDTO.setNombreFabricante("Test Fabricante");
    }

    @Test
    void whenObtenerTodosLosFabricantes_thenReturnFabricanteList() {
        when(repositorioFabricante.findAll()).thenReturn(Collections.singletonList(fabricante));
        when(mapeadorFabricante.convertirADTO(any(Fabricante.class))).thenReturn(fabricanteDTO);

        List<FabricanteDTO> result = servicioFabricante.obtenerTodosLosFabricantes();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombreFabricante()).isEqualTo("Test Fabricante");
    }

    @Test
    void whenBuscarPorIdentificador_withValidId_thenReturnFabricante() {
        when(repositorioFabricante.findById(1)).thenReturn(Optional.of(fabricante));
        when(mapeadorFabricante.convertirADTO(any(Fabricante.class))).thenReturn(fabricanteDTO);

        FabricanteDTO result = servicioFabricante.buscarPorIdentificador(1);

        assertThat(result.getNombreFabricante()).isEqualTo("Test Fabricante");
    }

    @Test
    void whenBuscarPorIdentificador_withInvalidId_thenThrowException() {
        when(repositorioFabricante.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            servicioFabricante.buscarPorIdentificador(1);
        });
    }
}
