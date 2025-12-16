package api.kata.cervezas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidad que representa un Estilo de cerveza
 * Define estilos específicos dentro de cada categoría
 */
@Entity
@Table(name = "styles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identificador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", nullable = false)
    private Categoria categoriaPadre;

    @Column(name = "style_name", nullable = false)
    private String nombreEstilo;

    @Column(name = "last_mod", nullable = false)
    private LocalDateTime fechaUltimaModificacion;

}
