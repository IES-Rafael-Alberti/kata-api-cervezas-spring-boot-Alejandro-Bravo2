package api.kata.cervezas.repository;

import api.kata.cervezas.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de datos de Categoría
 * Proporciona operaciones CRUD básicas
 */
@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Integer> {
}
