package api.kata.cervezas.web;

import api.kata.cervezas.dto.CervezaDTO;
import api.kata.cervezas.service.ServicioCerveza;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones sobre Cervezas
 * Expone endpoints para CRUD completo de cervezas
 */
@RestController
@RequestMapping("/api/v1/beers")
public class ControladorCerveza {

    private final ServicioCerveza servicioCerveza;

    public ControladorCerveza(ServicioCerveza servicioCerveza) {
        this.servicioCerveza = servicioCerveza;
    }

    /**
     * Obtiene el listado completo de cervezas
     * @return Respuesta con lista de cervezas
     */
    @GetMapping
    public ResponseEntity<List<CervezaDTO>> obtenerListadoCervezas(){
        List<CervezaDTO> listadoCervezas = servicioCerveza.obtenerTodasLasCervezas();
        return ResponseEntity.ok(listadoCervezas);
    }

    /**
     * Obtiene una cerveza específica por su ID
     * @param id Identificador de la cerveza
     * @return Respuesta con la cerveza encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<CervezaDTO> obtenerCervezaPorId(@PathVariable Integer id) {
        CervezaDTO cervezaEncontrada = servicioCerveza.buscarPorIdentificador(id);
        return ResponseEntity.ok(cervezaEncontrada);
    }

    /**
     * Crea una nueva cerveza en el sistema
     * @param cervezaDTO Datos de la cerveza a crear
     * @return Respuesta con la cerveza creada
     */
    @PostMapping
    public ResponseEntity<CervezaDTO> registrarNuevaCerveza(@RequestBody CervezaDTO cervezaDTO) {
        CervezaDTO cervezaCreada = servicioCerveza.crearNuevaCerveza(cervezaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cervezaCreada);
    }

    /**
     * Actualiza completamente una cerveza existente
     * @param id Identificador de la cerveza
     * @param cervezaDTO Nuevos datos de la cerveza
     * @return Respuesta con la cerveza actualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<CervezaDTO> modificarCerveza(@PathVariable Integer id, @RequestBody CervezaDTO cervezaDTO) {
        CervezaDTO cervezaActualizada = servicioCerveza.actualizarCerveza(id, cervezaDTO);
        return ResponseEntity.ok(cervezaActualizada);
    }

    /**
     * Actualiza parcialmente una cerveza existente
     * @param id Identificador de la cerveza
     * @param cervezaDTO Datos parciales de la cerveza
     * @return Respuesta con la cerveza actualizada
     */
    @PatchMapping("/{id}")
    public ResponseEntity<CervezaDTO> actualizacionParcialCerveza(@PathVariable Integer id, @RequestBody CervezaDTO cervezaDTO) {
        CervezaDTO cervezaModificada = servicioCerveza.actualizacionParcial(id, cervezaDTO);
        return ResponseEntity.ok(cervezaModificada);
    }

    /**
     * Elimina una cerveza del sistema
     * @param id Identificador de la cerveza
     * @return Respuesta sin contenido
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCerveza(@PathVariable Integer id) {
        servicioCerveza.eliminarCerveza(id);
        return ResponseEntity.noContent().build();
    }
}
