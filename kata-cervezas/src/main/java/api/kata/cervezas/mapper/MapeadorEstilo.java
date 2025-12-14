package api.kata.cervezas.mapper;

import api.kata.cervezas.dto.EstiloDTO;
import api.kata.cervezas.model.Estilo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapeador para convertir entre Estilo y EstiloDTO
 * Utiliza MapStruct para la transformación automática de datos
 */
@Mapper(componentModel = "spring")
public interface MapeadorEstilo {

    @Mapping(target = "categoriaPadre", ignore = true)
    @Mapping(target = "fechaUltimaModificacion", ignore = true)
    Estilo convertirAEntidad(EstiloDTO dto);

    @Mapping(target = "idCategoria", source = "categoriaPadre.identificador")
    EstiloDTO convertirADTO(Estilo entidadEstilo);
}