package api.kata.cervezas.mapper;

import api.kata.cervezas.dto.FabricanteDTO;
import api.kata.cervezas.model.Fabricante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapeador para convertir entre Fabricante y FabricanteDTO
 * Utiliza MapStruct para la transformación automática de datos
 */
@Mapper(componentModel = "spring")
public interface MapeadorFabricante {

    @Mapping(target = "usuarioCreador", ignore = true)
    @Mapping(target = "fechaUltimaModificacion", ignore = true)
    Fabricante convertirAEntidad(FabricanteDTO dto);

    FabricanteDTO convertirADTO(Fabricante entidadFabricante);
}
