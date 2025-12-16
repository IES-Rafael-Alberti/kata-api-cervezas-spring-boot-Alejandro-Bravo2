package api.kata.cervezas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO para transferir datos de Categoría
 * Validación de información de categorías de cerveza
 */
@Data
public class CategoriaDTO {

    private Integer identificador;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String nombreCategoria;
}
