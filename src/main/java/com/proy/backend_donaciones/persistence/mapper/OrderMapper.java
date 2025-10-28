package com.proy.backend_donaciones.persistence.mapper;

import com.proy.backend_donaciones.domain.Order;
import com.proy.backend_donaciones.persistence.entity.Pedido;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mappings({
        @Mapping(source = "alimento.id", target = "alimentoId"),
        @Mapping(source = "alimento.nombre", target = "alimentoNombre"),
        @Mapping(source = "donante.id", target = "donanteId"),
        @Mapping(source = "donante.nombre", target = "donanteNombre"),
        @Mapping(source = "estado", target = "estado"),
        @Mapping(source = "fechaCreacion", target = "fechaCreacion")
    })
    Order toOrder(Pedido pedido);

    List<Order> toOrders(List<Pedido> pedidos);

    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target = "alimento", ignore = true),
        @Mapping(target = "donante", ignore = true)
    })
    Pedido toPedido(Order order);
}