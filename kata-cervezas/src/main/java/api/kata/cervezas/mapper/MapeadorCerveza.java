package api.kata.cervezas.mapper;

import api.kata.cervezas.dto.CervezaDTO;
import api.kata.cervezas.model.Cerveza;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapeador para convertir entre Cerveza y CervezaDTO
 * Utiliza MapStruct para la transformación automática de datos
 */
@Mapper(componentModel = "spring")
public interface MapeadorCerveza {

    @Mapping(target = "idFabricante", source = "fabricante.identificador")
    @Mapping(target = "idCategoria", source = "categoriaCerveza.identificador")
    @Mapping(target = "idEstilo", source = "estiloProducto.identificador")
    CervezaDTO convertirADTO(Cerveza entidadCerveza);

    @Mapping(target = "fabricante", ignore = true)
    @Mapping(target = "categoriaCerveza", ignore = true)
    @Mapping(target = "estiloProducto", ignore = true)
    @Mapping(target = "usuarioCreador", ignore = true)
    @Mapping(target = "fechaUltimaModificacion", ignore = true)
    Cerveza convertirAEntidad(CervezaDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "identificador", ignore = true)
    @Mapping(target = "fabricante", ignore = true)
    @Mapping(target = "categoriaCerveza", ignore = true)
    @Mapping(target = "estiloProducto", ignore = true)
    @Mapping(target = "usuarioCreador", ignore = true)
    @Mapping(target = "fechaUltimaModificacion", ignore = true)
    void actualizarParcialmente(CervezaDTO dto, @MappingTarget Cerveza entidad);
}
