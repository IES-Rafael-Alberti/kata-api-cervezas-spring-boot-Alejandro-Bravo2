package api.kata.cervezas.service;

import api.kata.cervezas.dto.EstiloDTO;
import api.kata.cervezas.exception.ResourceNotFoundException;
import api.kata.cervezas.mapper.MapeadorEstilo;
import api.kata.cervezas.repository.RepositorioEstilo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para la gestión de operaciones de negocio relacionadas con Estilo
 * Implementa la lógica de negocio para estilos de cerveza
 */
@Service
@Transactional
public class ServicioEstilo {

    private final RepositorioEstilo repositorioEstilo;
    private final MapeadorEstilo mapeadorEstilo;

    public ServicioEstilo(RepositorioEstilo repositorioEstilo, MapeadorEstilo mapeadorEstilo) {
        this.repositorioEstilo = repositorioEstilo;
        this.mapeadorEstilo = mapeadorEstilo;
    }

    /**
     * Obtiene todos los estilos del sistema
     * @return Lista de DTOs de estilo
     */
    @Transactional(readOnly = true)
    public List<EstiloDTO> obtenerTodosLosEstilos() {
        return repositorioEstilo.findAll()
                .stream()
                .map(mapeadorEstilo::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un estilo por su identificador
     * @param identificador ID del estilo
     * @return DTO del estilo encontrado
     */
    @Transactional(readOnly = true)
    public EstiloDTO buscarPorIdentificador(Integer identificador) {
        return repositorioEstilo.findById(identificador)
                .map(mapeadorEstilo::convertirADTO)
                .orElseThrow(() -> new ResourceNotFoundException("Estilo no encontrado con id: " + identificador));
    }

}
