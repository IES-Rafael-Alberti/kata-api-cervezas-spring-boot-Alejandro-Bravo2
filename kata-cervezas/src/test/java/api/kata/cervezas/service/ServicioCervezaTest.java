package api.kata.cervezas.service;

import api.kata.cervezas.dto.CervezaDTO;
import api.kata.cervezas.exception.ResourceNotFoundException;
import api.kata.cervezas.mapper.MapeadorCerveza;
import api.kata.cervezas.model.Cerveza;
import api.kata.cervezas.repository.RepositorioCerveza;
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
public class ServicioCervezaTest {

    @Mock
    private RepositorioCerveza repositorioCerveza;

    @Mock
    private MapeadorCerveza mapeadorCerveza;

    @InjectMocks
    private ServicioCerveza servicioCerveza;

    private Cerveza cerveza;
    private CervezaDTO cervezaDTO;

    @BeforeEach
    void setUp() {
        cerveza = new Cerveza();
        cerveza.setIdentificador(1);
        cerveza.setNombreProducto("Test Beer");

        cervezaDTO = new CervezaDTO();
        cervezaDTO.setIdentificador(1);
        cervezaDTO.setNombreProducto("Test Beer");
    }

    @Test
    void whenObtenerTodasLasCervezas_thenReturnCervezaList() {
        when(repositorioCerveza.findAll()).thenReturn(Collections.singletonList(cerveza));
        when(mapeadorCerveza.convertirADTO(any(Cerveza.class))).thenReturn(cervezaDTO);

        List<CervezaDTO> result = servicioCerveza.obtenerTodasLasCervezas();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombreProducto()).isEqualTo("Test Beer");
    }

    @Test
    void whenBuscarPorIdentificador_withValidId_thenReturnCerveza() {
        when(repositorioCerveza.findById(1)).thenReturn(Optional.of(cerveza));
        when(mapeadorCerveza.convertirADTO(any(Cerveza.class))).thenReturn(cervezaDTO);

        CervezaDTO result = servicioCerveza.buscarPorIdentificador(1);

        assertThat(result.getNombreProducto()).isEqualTo("Test Beer");
    }

    @Test
    void whenBuscarPorIdentificador_withInvalidId_thenThrowException() {
        when(repositorioCerveza.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            servicioCerveza.buscarPorIdentificador(1);
        });
    }

    @Test
    void whenCrearNuevaCerveza_thenReturnCerveza() {
        when(repositorioCerveza.save(any(Cerveza.class))).thenReturn(cerveza);
        when(mapeadorCerveza.convertirAEntidad(any(CervezaDTO.class))).thenReturn(cerveza);
        when(mapeadorCerveza.convertirADTO(any(Cerveza.class))).thenReturn(cervezaDTO);

        CervezaDTO result = servicioCerveza.crearNuevaCerveza(new CervezaDTO());

        assertThat(result.getNombreProducto()).isEqualTo("Test Beer");
    }

    @Test
    void whenActualizarCerveza_withValidId_thenReturnCerveza() {
        when(repositorioCerveza.findById(1)).thenReturn(Optional.of(cerveza));
        when(repositorioCerveza.save(any(Cerveza.class))).thenReturn(cerveza);
        when(mapeadorCerveza.convertirAEntidad(any(CervezaDTO.class))).thenReturn(cerveza);
        when(mapeadorCerveza.convertirADTO(any(Cerveza.class))).thenReturn(cervezaDTO);

        CervezaDTO result = servicioCerveza.actualizarCerveza(1, new CervezaDTO());

        assertThat(result.getNombreProducto()).isEqualTo("Test Beer");
    }

    @Test
    void whenActualizarCerveza_withInvalidId_thenThrowException() {
        when(repositorioCerveza.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            servicioCerveza.actualizarCerveza(1, new CervezaDTO());
        });
    }

    @Test
    void whenEliminarCerveza_withValidId_thenSuccess() {
        when(repositorioCerveza.existsById(1)).thenReturn(true);
        servicioCerveza.eliminarCerveza(1);
    }

    @Test
    void whenEliminarCerveza_withInvalidId_thenThrowException() {
        when(repositorioCerveza.existsById(1)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> {
            servicioCerveza.eliminarCerveza(1);
        });
    }
}
