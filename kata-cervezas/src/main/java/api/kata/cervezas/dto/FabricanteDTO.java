package api.kata.cervezas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO para transferir datos de Fabricante
 * Valida la información de las cervecerías
 */
@Data
public class FabricanteDTO {

    private Integer identificador;

    @NotBlank(message = "El nombre del fabricante es obligatorio")
    private String nombreFabricante;

    private String direccionPrincipal;

    private String direccionSecundaria;

    private String ciudad;

    private String provincia;

    private String codigoPostal;

    private String pais;

    private String numeroTelefono;

    private String sitioWeb;

    private String rutaImagen;

    private String descripcionFabricante;
}
