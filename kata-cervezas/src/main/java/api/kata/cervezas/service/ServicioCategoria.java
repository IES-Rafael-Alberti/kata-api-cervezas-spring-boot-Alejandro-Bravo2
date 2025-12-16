package api.kata.cervezas.service;

import api.kata.cervezas.dto.CategoriaDTO;
import api.kata.cervezas.exception.ResourceNotFoundException;
import api.kata.cervezas.mapper.MapeadorCategoria;
import api.kata.cervezas.repository.RepositorioCategoria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para la gestión de operaciones de negocio relacionadas con Categoria
 * Implementa la lógica de negocio para categorías de cerveza
 */
@Service
@Transactional
public class ServicioCategoria {

    private final RepositorioCategoria repositorioCategoria;
    private final MapeadorCategoria mapeadorCategoria;

    public ServicioCategoria(RepositorioCategoria repositorioCategoria, MapeadorCategoria mapeadorCategoria) {
        this.repositorioCategoria = repositorioCategoria;
        this.mapeadorCategoria = mapeadorCategoria;
    }

    /**
     * Obtiene todas las categorías del sistema
     * @return Lista de DTOs de categoría
     */
    @Transactional(readOnly = true)
    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        return repositorioCategoria.findAll()
                .stream()
                .map(mapeadorCategoria::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca una categoría por su identificador
     * @param identificador ID de la categoría
     * @return DTO de la categoría encontrada
     */
    @Transactional(readOnly = true)
    public CategoriaDTO buscarPorIdentificador(Integer identificador) {
        return repositorioCategoria.findById(identificador)
                .map(mapeadorCategoria::convertirADTO)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + identificador));
    }

}
