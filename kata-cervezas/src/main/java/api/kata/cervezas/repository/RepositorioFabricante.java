package api.kata.cervezas.repository;

import api.kata.cervezas.model.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de datos de Fabricante
 * Proporciona operaciones CRUD básicas
 */
@Repository
public interface RepositorioFabricante extends JpaRepository<Fabricante, Integer> {
}
