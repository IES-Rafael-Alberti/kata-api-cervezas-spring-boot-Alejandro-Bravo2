package api.kata.cervezas.web;

import api.kata.cervezas.dto.EstiloDTO;
import api.kata.cervezas.service.ServicioEstilo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ControladorEstilo.class)
public class ControladorEstiloTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioEstilo servicioEstilo;

    @Test
    public void givenEstilos_whenGetEstilos_thenReturnJsonArray() throws Exception {
        EstiloDTO estiloDTO = new EstiloDTO();
        estiloDTO.setNombreEstilo("Test Estilo");

        given(servicioEstilo.obtenerTodosLosEstilos()).willReturn(Collections.singletonList(estiloDTO));

        mockMvc.perform(get("/api/v1/styles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreEstilo").value("Test Estilo"));
    }

    @Test
    public void whenGetEstiloById_thenReturnJson() throws Exception {
        EstiloDTO estiloDTO = new EstiloDTO();
        estiloDTO.setNombreEstilo("Test Estilo");

        given(servicioEstilo.buscarPorIdentificador(1)).willReturn(estiloDTO);

        mockMvc.perform(get("/api/v1/styles/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreEstilo").value("Test Estilo"));
    }
}
