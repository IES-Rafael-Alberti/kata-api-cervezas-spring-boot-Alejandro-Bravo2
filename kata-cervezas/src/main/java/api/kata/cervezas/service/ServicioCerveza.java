package api.kata.cervezas.service;

import api.kata.cervezas.dto.CervezaDTO;
import api.kata.cervezas.exception.ResourceNotFoundException;
import api.kata.cervezas.mapper.MapeadorCerveza;
import api.kata.cervezas.model.Cerveza;
import api.kata.cervezas.repository.RepositorioCerveza;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para la gestión de operaciones de negocio relacionadas con Cerveza
 * Implementa la lógica de negocio y validaciones
 */
@Service
@Transactional
public class ServicioCerveza {

    private final RepositorioCerveza repositorioCerveza;
    private final MapeadorCerveza mapeadorCerveza;

    public ServicioCerveza(RepositorioCerveza repositorioCerveza, MapeadorCerveza mapeadorCerveza) {
        this.repositorioCerveza = repositorioCerveza;
        this.mapeadorCerveza = mapeadorCerveza;
    }

    /**
     * Obtiene todas las cervezas del sistema
     * @return Lista de DTOs de cerveza
     */
    @Transactional(readOnly = true)
    public List<CervezaDTO> obtenerTodasLasCervezas() {
        return repositorioCerveza.findAll()
                .stream()
                .map(mapeadorCerveza::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca una cerveza por su identificador
     * @param identificador ID de la cerveza
     * @return DTO de la cerveza encontrada
     */
    @Transactional(readOnly = true)
    public CervezaDTO buscarPorIdentificador(Integer identificador) {
        return repositorioCerveza.findById(identificador)
                .map(mapeadorCerveza::convertirADTO)
                .orElseThrow(() -> new ResourceNotFoundException("Cerveza no encontrada con id: " + identificador));
    }

    /**
     * Crea una nueva cerveza en el sistema
     * @param cervezaDTO Datos de la cerveza a crear
     * @return DTO de la cerveza creada
     */
    public CervezaDTO crearNuevaCerveza(CervezaDTO cervezaDTO) {
        Cerveza nuevaCerveza = mapeadorCerveza.convertirAEntidad(cervezaDTO);
        nuevaCerveza.setFechaUltimaModificacion(LocalDateTime.now());
        // TODO: Replace with the real user from security context
        nuevaCerveza.setUsuarioCreador(0);
        Cerveza cervezaGuardada = repositorioCerveza.save(nuevaCerveza);
        return mapeadorCerveza.convertirADTO(cervezaGuardada);
    }

    /**
     * Actualiza completamente una cerveza existente
     * @param identificador ID de la cerveza a actualizar
     * @param cervezaDTO Nuevos datos de la cerveza
     * @return DTO de la cerveza actualizada
     */
    public CervezaDTO actualizarCerveza(Integer identificador, CervezaDTO cervezaDTO) {
        Cerveza cervezaExistente = repositorioCerveza.findById(identificador)
                .orElseThrow(() -> new ResourceNotFoundException("Cerveza no encontrada con id: " + identificador));

        Cerveza cervezaActualizada = mapeadorCerveza.convertirAEntidad(cervezaDTO);
        cervezaActualizada.setIdentificador(cervezaExistente.getIdentificador());
        cervezaActualizada.setFechaUltimaModificacion(LocalDateTime.now());

        Cerveza cervezaGuardada = repositorioCerveza.save(cervezaActualizada);
        return mapeadorCerveza.convertirADTO(cervezaGuardada);
    }

    /**
     * Actualiza parcialmente una cerveza existente
     * @param identificador ID de la cerveza a actualizar
     * @param cervezaDTO Datos parciales de la cerveza
     * @return DTO de la cerveza actualizada
     */
    public CervezaDTO actualizacionParcial(Integer identificador, CervezaDTO cervezaDTO) {
        Cerveza cervezaExistente = repositorioCerveza.findById(identificador)
                .orElseThrow(() -> new ResourceNotFoundException("Cerveza no encontrada con id: " + identificador));

        mapeadorCerveza.actualizarParcialmente(cervezaDTO, cervezaExistente);
        cervezaExistente.setFechaUltimaModificacion(LocalDateTime.now());

        Cerveza cervezaGuardada = repositorioCerveza.save(cervezaExistente);
        return mapeadorCerveza.convertirADTO(cervezaGuardada);
    }

    /**
     * Elimina una cerveza del sistema
     * @param identificador ID de la cerveza a eliminar
     */
    public void eliminarCerveza(Integer identificador) {
        if (!repositorioCerveza.existsById(identificador)) {
            throw new ResourceNotFoundException("Cerveza no encontrada con id: " + identificador);
        }
        repositorioCerveza.deleteById(identificador);
    }

}
