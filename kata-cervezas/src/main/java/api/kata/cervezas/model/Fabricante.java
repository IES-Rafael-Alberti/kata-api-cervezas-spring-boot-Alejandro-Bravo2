package api.kata.cervezas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidad que representa una Fábrica o Cervecería
 * Almacena la información completa de los productores de cerveza
 */
@Entity
@Table(name = "breweries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fabricante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identificador;

    @Column(nullable = false)
    private String nombreFabricante;

    @Column(nullable = false)
    private String direccionPrincipal;

    @Column(nullable = false)
    private String direccionSecundaria;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String provincia;

    @Column(nullable = false)
    private String codigoPostal;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    private String numeroTelefono;

    @Column(nullable = false)
    private String sitioWeb;

    @Column(nullable = false)
    private String rutaImagen;

    @Column(nullable = false)
    private String descripcionFabricante;

    @Column(name = "add_user", nullable = false)
    private Integer usuarioCreador;

    @Column(name = "last_mod", nullable = false)
    private LocalDateTime fechaUltimaModificacion;

}
