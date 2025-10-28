package com.proy.backend_donaciones.persistence;

import com.proy.backend_donaciones.domain.Order;
import com.proy.backend_donaciones.domain.repository.OrderRepository;
import com.proy.backend_donaciones.persistence.crud.PedidoCrudRepository;
import com.proy.backend_donaciones.persistence.entity.Pedido;
import com.proy.backend_donaciones.persistence.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepository implements OrderRepository {

    @Autowired
    private PedidoCrudRepository crud;

    @Autowired
    private OrderMapper mapper;

    @Override
    public List<Order> getAll() {
        List<Pedido> pedidos = crud.findAll();
        return mapper.toOrders(pedidos);
    }

    @Override
    public Optional<Order> findById(long id) {
        return crud.findById(id).map(mapper::toOrder);
    }

    @Override
    public Order save(Order order) {
        Pedido pedido = mapper.toPedido(order);
        
        return mapper.toOrder(crud.save(pedido));
    }

    @Override
    public void delete(long id) {
        crud.deleteById(id);
    }

     public Pedido saveEntity(Pedido pedido) {
        return crud.save(pedido);
    }
}
