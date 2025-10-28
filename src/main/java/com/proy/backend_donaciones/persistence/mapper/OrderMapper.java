package com.proy.backend_donaciones.persistence.mapper;

import com.proy.backend_donaciones.domain.Order;
import com.proy.backend_donaciones.persistence.entity.Pedido;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "alimento.id", target = "alimentoId")
    @Mapping(source = "alimento.nombre", target = "alimentoNombre")
    @Mapping(source = "donante.id", target = "donanteId")
    @Mapping(source = "donante.nombre", target = "donanteNombre")
    @Mapping(source = "estado", target = "estado")
    Order toOrder(Pedido pedido);

    List<Order> toOrders(List<Pedido> pedidos);

    @InheritInverseConfiguration
    // Le decimos al mapper que no se preocupe por estos objetos, los pondremos a mano.
    @Mapping(target = "alimento", ignore = true)
    @Mapping(target = "donante", ignore = true)
    Pedido toPedido(Order order);
}