package api.kata.cervezas.repository;

import api.kata.cervezas.model.Estilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de datos de Estilo
 * Proporciona operaciones CRUD básicas
 */
@Repository
public interface RepositorioEstilo extends JpaRepository<Estilo, Integer> {
}
