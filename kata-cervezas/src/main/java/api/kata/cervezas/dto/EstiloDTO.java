package api.kata.cervezas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO para transferir datos de Estilo
 * Validación de información de estilos de cerveza
 */
@Data
public class EstiloDTO {

    private Integer identificador;

    @NotBlank(message = "El nombre del estilo es obligatorio")
    private String nombreEstilo;

    private Integer idCategoria;
}
