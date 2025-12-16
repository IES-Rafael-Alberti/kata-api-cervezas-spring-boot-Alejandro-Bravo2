package api.kata.cervezas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidad que representa una Cerveza en el sistema
 * Contiene información detallada sobre cada producto cervecero
 */
@Entity
@Table(name = "beers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cerveza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identificador;

    /**
     * Relación N:1 - Muchas cervezas pertenecen a una fábrica
     * Una cerveza es producida por una única cervecería
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brewery_id", nullable = false)
    private Fabricante fabricante;

    @Column(nullable = false)
    private String nombreProducto;

    /**
     * Relación N:1 - Múltiples cervezas comparten la misma categoría
     * Cada cerveza pertenece a una categoría específica
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", nullable = false)
    private Categoria categoriaCerveza;

    /**
     * Relación N:1 - Varias cervezas pueden tener el mismo estilo
     * Cada producto tiene un estilo de cerveza definido
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="style_id", nullable = false)
    private Estilo estiloProducto;

    @Column(nullable = false)
    private Float graduacionAlcoholica;

    @Column(nullable = false)
    private Float amargorIbu;

    @Column(nullable = false)
    private Float colorSrm;

    @Column(nullable = false)
    private Integer codigoBarras;

    @Column(nullable = false)
    private String rutaImagen;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcionProducto;

    @Column(name = "add_user", nullable = false)
    private Integer usuarioCreador;

    @Column(name = "last_mod", nullable = false)
    private LocalDateTime fechaUltimaModificacion;
}
