package api.kata.cervezas.repository;

import api.kata.cervezas.model.Cerveza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de datos de Cerveza
 * Proporciona operaciones CRUD básicas
 */
@Repository
public interface RepositorioCerveza extends JpaRepository<Cerveza, Integer> {
}
