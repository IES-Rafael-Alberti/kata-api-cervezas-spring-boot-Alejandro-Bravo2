package api.kata.cervezas.web;

import api.kata.cervezas.dto.CategoriaDTO;
import api.kata.cervezas.service.ServicioCategoria;
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

@WebMvcTest(ControladorCategoria.class)
public class ControladorCategoriaTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioCategoria servicioCategoria;

    @Test
    public void givenCategorias_whenGetCategorias_thenReturnJsonArray() throws Exception {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombreCategoria("Test Categoria");

        given(servicioCategoria.obtenerTodasLasCategorias()).willReturn(Collections.singletonList(categoriaDTO));

        mockMvc.perform(get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreCategoria").value("Test Categoria"));
    }

    @Test
    public void whenGetCategoriaById_thenReturnJson() throws Exception {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombreCategoria("Test Categoria");

        given(servicioCategoria.buscarPorIdentificador(1)).willReturn(categoriaDTO);

        mockMvc.perform(get("/api/v1/categories/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreCategoria").value("Test Categoria"));
    }
}
