package api.kata.cervezas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

/**
 * DTO para transferir datos de Cerveza
 * Contiene validaciones para asegurar la integridad de los datos
 */
@Data
public class CervezaDTO {

    private Integer identificador;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombreProducto;

    @PositiveOrZero(message = "La graduación alcohólica debe ser no negativa")
    private Float graduacionAlcoholica;

    @PositiveOrZero(message = "El amargor IBU debe ser no negativo")
    private Float amargorIbu;

    @PositiveOrZero(message = "El color SRM debe ser no negativo")
    private Float colorSrm;

    private Integer codigoBarras;

    private String rutaImagen;

    private String descripcionProducto;

    private Integer idFabricante;

    private Integer idCategoria;

    private Integer idEstilo;
}
