package api.kata.cervezas.web;

import api.kata.cervezas.dto.EstiloDTO;
import api.kata.cervezas.service.ServicioEstilo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones sobre Estilos
 * Expone endpoints para consultar información de estilos de cerveza
 */
@RestController
@RequestMapping("/api/v1/styles")
public class ControladorEstilo {

    private final ServicioEstilo servicioEstilo;

    public ControladorEstilo(ServicioEstilo servicioEstilo) {
        this.servicioEstilo = servicioEstilo;
    }

    /**
     * Obtiene el listado completo de estilos
     * @return Respuesta con lista de estilos
     */
    @GetMapping
    public ResponseEntity<List<EstiloDTO>> obtenerListadoEstilos() {
        List<EstiloDTO> listadoEstilos = servicioEstilo.obtenerTodosLosEstilos();
        return ResponseEntity.ok(listadoEstilos);
    }

    /**
     * Obtiene un estilo específico por su ID
     * @param id Identificador del estilo
     * @return Respuesta con el estilo encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<EstiloDTO> obtenerEstiloPorId(@PathVariable Integer id) {
        EstiloDTO estiloEncontrado = servicioEstilo.buscarPorIdentificador(id);
        return ResponseEntity.ok(estiloEncontrado);
    }
}
