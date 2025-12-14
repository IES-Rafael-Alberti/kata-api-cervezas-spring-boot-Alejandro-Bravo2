package api.kata.cervezas.web;

import api.kata.cervezas.dto.CategoriaDTO;
import api.kata.cervezas.service.ServicioCategoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones sobre Categorías
 * Expone endpoints para consultar información de categorías de cerveza
 */
@RestController
@RequestMapping("/api/v1/categories")
public class ControladorCategoria {

    private final ServicioCategoria servicioCategoria;

    public ControladorCategoria(ServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }

    /**
     * Obtiene el listado completo de categorías
     * @return Respuesta con lista de categorías
     */
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> obtenerListadoCategorias() {
        List<CategoriaDTO> listadoCategorias = servicioCategoria.obtenerTodasLasCategorias();
        return ResponseEntity.ok(listadoCategorias);
    }

    /**
     * Obtiene una categoría específica por su ID
     * @param id Identificador de la categoría
     * @return Respuesta con la categoría encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerCategoriaPorId(@PathVariable Integer id) {
        CategoriaDTO categoriaEncontrada = servicioCategoria.buscarPorIdentificador(id);
        return ResponseEntity.ok(categoriaEncontrada);
    }

}
