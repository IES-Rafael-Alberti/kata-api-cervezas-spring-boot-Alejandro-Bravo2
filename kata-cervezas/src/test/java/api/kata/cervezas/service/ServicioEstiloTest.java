package api.kata.cervezas.service;

import api.kata.cervezas.dto.EstiloDTO;
import api.kata.cervezas.exception.ResourceNotFoundException;
import api.kata.cervezas.mapper.MapeadorEstilo;
import api.kata.cervezas.model.Estilo;
import api.kata.cervezas.repository.RepositorioEstilo;
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
public class ServicioEstiloTest {

    @Mock
    private RepositorioEstilo repositorioEstilo;

    @Mock
    private MapeadorEstilo mapeadorEstilo;

    @InjectMocks
    private ServicioEstilo servicioEstilo;

    private Estilo estilo;
    private EstiloDTO estiloDTO;

    @BeforeEach
    void setUp() {
        estilo = new Estilo();
        estilo.setIdentificador(1);
        estilo.setNombreEstilo("Test Estilo");

        estiloDTO = new EstiloDTO();
        estiloDTO.setIdentificador(1);
        estiloDTO.setNombreEstilo("Test Estilo");
    }

    @Test
    void whenObtenerTodosLosEstilos_thenReturnEstiloList() {
        when(repositorioEstilo.findAll()).thenReturn(Collections.singletonList(estilo));
        when(mapeadorEstilo.convertirADTO(any(Estilo.class))).thenReturn(estiloDTO);

        List<EstiloDTO> result = servicioEstilo.obtenerTodosLosEstilos();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombreEstilo()).isEqualTo("Test Estilo");
    }

    @Test
    void whenBuscarPorIdentificador_withValidId_thenReturnEstilo() {
        when(repositorioEstilo.findById(1)).thenReturn(Optional.of(estilo));
        when(mapeadorEstilo.convertirADTO(any(Estilo.class))).thenReturn(estiloDTO);

        EstiloDTO result = servicioEstilo.buscarPorIdentificador(1);

        assertThat(result.getNombreEstilo()).isEqualTo("Test Estilo");
    }

    @Test
    void whenBuscarPorIdentificador_withInvalidId_thenThrowException() {
        when(repositorioEstilo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            servicioEstilo.buscarPorIdentificador(1);
        });
    }
}
