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

    // --- Mapeo de Entidad (Donacion) a Dominio (Donation) ---
    @Mappings({
            @Mapping(source = "donante.id", target = "donorId"),
            @Mapping(source = "categoriaAlimento", target = "foodCategory"),
            @Mapping(source = "cantidadAprox", target = "approximateQuantity"),
            @Mapping(source = "unidad", target = "unit"),
            @Mapping(source = "direccionRecojo", target = "pickupAddress"),
            @Mapping(source = "fechaRecojo", target = "pickupDate"),
            @Mapping(source = "horaRecojo", target = "pickupTime"),
            @Mapping(source = "telefonoContacto", target = "contactPhone"),
            @Mapping(source = "tipoAsignacion", target = "assignmentType"),
            @Mapping(source = "idBeneficiario", target = "beneficiaryId"),
            @Mapping(source = "tipoBeneficiario", target = "beneficiaryType"),

            // --- AQUÍ ESTÁ LA CORRECCIÓN PRINCIPAL ---
            @Mapping(source = "aptoParaConsumo", target = "consumable"), // Cambiado de "isConsumable" a "consumable"

            @Mapping(source = "fechaCreacion", target = "creationDate"),
            @Mapping(source = "estado", target = "status") // Mapeo que faltaba
    })
    Donation toDonation(Donacion donacion);
    List<Donation> toDonations(List<Donacion> donaciones);

    // --- Mapeo Inverso: de Dominio (Donation) a Entidad (Donacion) ---
    @InheritInverseConfiguration
    @Mappings({
            // Ignoramos el donante porque lo asignamos manualmente en el repositorio
            @Mapping(target = "donante", ignore = true),
            // Añadimos los mapeos inversos que faltaban
            @Mapping(source = "description", target = "descripcion"),
            @Mapping(source = "consumable", target = "aptoParaConsumo"),
            @Mapping(source = "status", target = "estado")
    })
    Donacion toDonacion(Donation donation);
}