package api.kata.cervezas.service;

import api.kata.cervezas.dto.FabricanteDTO;
import api.kata.cervezas.exception.ResourceNotFoundException;
import api.kata.cervezas.mapper.MapeadorFabricante;
import api.kata.cervezas.repository.RepositorioFabricante;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para la gestión de operaciones de negocio relacionadas con Fabricante
 * Implementa la lógica de negocio para cervecerías
 */
@Service
@Transactional
public class ServicioFabricante {

    private final RepositorioFabricante repositorioFabricante;
    private final MapeadorFabricante mapeadorFabricante;

    public ServicioFabricante(RepositorioFabricante repositorioFabricante, MapeadorFabricante mapeadorFabricante) {
        this.repositorioFabricante = repositorioFabricante;
        this.mapeadorFabricante = mapeadorFabricante;
    }

    /**
     * Obtiene todos los fabricantes del sistema
     * @return Lista de DTOs de fabricante
     */
    @Transactional(readOnly = true)
    public List<FabricanteDTO> obtenerTodosLosFabricantes() {
        return repositorioFabricante.findAll()
                .stream()
                .map(mapeadorFabricante::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un fabricante por su identificador
     * @param identificador ID del fabricante
     * @return DTO del fabricante encontrado
     */
    @Transactional(readOnly = true)
    public FabricanteDTO buscarPorIdentificador(Integer identificador) {
        return repositorioFabricante.findById(identificador)
                .map(mapeadorFabricante::convertirADTO)
                .orElseThrow(() -> new ResourceNotFoundException("Fabricante no encontrado con id: " + identificador));
    }

}
