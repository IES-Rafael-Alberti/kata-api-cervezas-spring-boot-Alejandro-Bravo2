package api.kata.cervezas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidad que representa una Categoría de cerveza
 * Clasifica las cervezas en diferentes tipos generales
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identificador;

    @Column(name = "cat_name", nullable = false)
    private String nombreCategoria;

    @Column(name = "last_mod", nullable = false)
    private LocalDateTime fechaUltimaModificacion;




}
