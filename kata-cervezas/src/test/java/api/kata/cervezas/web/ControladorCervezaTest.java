package api.kata.cervezas.web;

import api.kata.cervezas.dto.CervezaDTO;
import api.kata.cervezas.service.ServicioCerveza;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ControladorCerveza.class)
public class ControladorCervezaTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioCerveza servicioCerveza;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenCervezas_whenGetCervezas_thenReturnJsonArray() throws Exception {
        CervezaDTO cervezaDTO = new CervezaDTO();
        cervezaDTO.setNombreProducto("Test Beer");

        given(servicioCerveza.obtenerTodasLasCervezas()).willReturn(Collections.singletonList(cervezaDTO));

        mockMvc.perform(get("/api/v1/beers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreProducto").value("Test Beer"));
    }

    @Test
    public void whenGetCervezaById_thenReturnJson() throws Exception {
        CervezaDTO cervezaDTO = new CervezaDTO();
        cervezaDTO.setNombreProducto("Test Beer");

        given(servicioCerveza.buscarPorIdentificador(1)).willReturn(cervezaDTO);

        mockMvc.perform(get("/api/v1/beers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto").value("Test Beer"));
    }

    @Test
    public void whenPostCerveza_thenReturnJson() throws Exception {
        CervezaDTO cervezaDTO = new CervezaDTO();
        cervezaDTO.setNombreProducto("Test Beer");

        given(servicioCerveza.crearNuevaCerveza(any(CervezaDTO.class))).willReturn(cervezaDTO);

        mockMvc.perform(post("/api/v1/beers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cervezaDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombreProducto").value("Test Beer"));
    }

    @Test
    public void whenPutCerveza_thenReturnJson() throws Exception {
        CervezaDTO cervezaDTO = new CervezaDTO();
        cervezaDTO.setNombreProducto("Test Beer");

        given(servicioCerveza.actualizarCerveza(any(Integer.class), any(CervezaDTO.class))).willReturn(cervezaDTO);

        mockMvc.perform(put("/api/v1/beers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cervezaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto").value("Test Beer"));
    }

    @Test
    public void whenPatchCerveza_thenReturnJson() throws Exception {
        CervezaDTO cervezaDTO = new CervezaDTO();
        cervezaDTO.setNombreProducto("Test Beer");

        given(servicioCerveza.actualizacionParcial(any(Integer.class), any(CervezaDTO.class))).willReturn(cervezaDTO);

        mockMvc.perform(patch("/api/v1/beers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cervezaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto").value("Test Beer"));
    }

    @Test
    public void whenDeleteCerveza_thenReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/beers/1"))
                .andExpect(status().isNoContent());
    }
}
