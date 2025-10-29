package com.proy.backend_donaciones.persistence.mapper;

import com.proy.backend_donaciones.domain.Donation;
import com.proy.backend_donaciones.persistence.entity.Donacion;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings; // ¡Asegúrate de importar Mappings!

import java.util.List;

@Mapper(componentModel = "spring")
public interface DonationMapper {

    @Mappings({
        @Mapping(source = "donante.id", target = "donorId"),
        @Mapping(source = "categoriaAlimento", target = "foodCategory"),
        @Mapping(source = "descripcion", target = "description"),
        @Mapping(source = "cantidadAprox", target = "approximateQuantity"),
        @Mapping(source = "unidad", target = "unit"),
        @Mapping(source = "direccionRecojo", target = "pickupAddress"),
        @Mapping(source = "fechaRecojo", target = "pickupDate"),
        @Mapping(source = "horaRecojo", target = "pickupTime"),
        @Mapping(source = "telefonoContacto", target = "contactPhone"),
        @Mapping(source = "tipoAsignacion", target = "assignmentType"),
        @Mapping(source = "idBeneficiario", target = "beneficiaryId"),
        @Mapping(source = "tipoBeneficiario", target = "beneficiaryType"),
        @Mapping(source = "aptoParaConsumo", target = "consumable"),
        @Mapping(source = "fechaCreacion", target = "creationDate"),
        @Mapping(source = "estado", target = "status")
    })
    Donation toDonation(Donacion donacion);

    List<Donation> toDonations(List<Donacion> donaciones);

    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target = "donante", ignore = true)
    })
    Donacion toDonacion(Donation donation);
}