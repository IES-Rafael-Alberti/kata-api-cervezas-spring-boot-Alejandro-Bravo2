package api.kata.cervezas.mapper;

import api.kata.cervezas.dto.CategoriaDTO;
import api.kata.cervezas.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapeador para convertir entre Categoria y CategoriaDTO
 * Utiliza MapStruct para la transformación automática de datos
 */
@Mapper(componentModel = "spring")
public interface MapeadorCategoria {

    @Mapping(target = "fechaUltimaModificacion", ignore = true)
    Categoria convertirAEntidad(CategoriaDTO dto);

    CategoriaDTO convertirADTO(Categoria entidadCategoria);
}
