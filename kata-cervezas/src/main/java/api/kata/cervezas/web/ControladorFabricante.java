package api.kata.cervezas.web;

import api.kata.cervezas.dto.FabricanteDTO;
import api.kata.cervezas.service.ServicioFabricante;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones sobre Fabricantes
 * Expone endpoints para consultar información de cervecerías
 */
@RestController
@RequestMapping("/api/v1/breweries")
public class ControladorFabricante {

    private final ServicioFabricante servicioFabricante;

    public ControladorFabricante(ServicioFabricante servicioFabricante) {
        this.servicioFabricante = servicioFabricante;
    }

    /**
     * Obtiene el listado completo de fabricantes
     * @return Respuesta con lista de fabricantes
     */
    @GetMapping
    public ResponseEntity<List<FabricanteDTO>> obtenerListadoFabricantes() {
        List<FabricanteDTO> listadoFabricantes = servicioFabricante.obtenerTodosLosFabricantes();
        return ResponseEntity.ok(listadoFabricantes);
    }

    /**
     * Obtiene un fabricante específico por su ID
     * @param id Identificador del fabricante
     * @return Respuesta con el fabricante encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<FabricanteDTO> obtenerFabricantePorId(@PathVariable Integer id) {
        FabricanteDTO fabricanteEncontrado = servicioFabricante.buscarPorIdentificador(id);
        return ResponseEntity.ok(fabricanteEncontrado);
    }

}
