package api.kata.cervezas.web;

import api.kata.cervezas.dto.FabricanteDTO;
import api.kata.cervezas.service.ServicioFabricante;
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

@WebMvcTest(ControladorFabricante.class)
public class ControladorFabricanteTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioFabricante servicioFabricante;

    @Test
    public void givenFabricantes_whenGetFabricantes_thenReturnJsonArray() throws Exception {
        FabricanteDTO fabricanteDTO = new FabricanteDTO();
        fabricanteDTO.setNombreFabricante("Test Fabricante");

        given(servicioFabricante.obtenerTodosLosFabricantes()).willReturn(Collections.singletonList(fabricanteDTO));

        mockMvc.perform(get("/api/v1/breweries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreFabricante").value("Test Fabricante"));
    }

    @Test
    public void whenGetFabricanteById_thenReturnJson() throws Exception {
        FabricanteDTO fabricanteDTO = new FabricanteDTO();
        fabricanteDTO.setNombreFabricante("Test Fabricante");

        given(servicioFabricante.buscarPorIdentificador(1)).willReturn(fabricanteDTO);

        mockMvc.perform(get("/api/v1/breweries/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreFabricante").value("Test Fabricante"));
    }
}
