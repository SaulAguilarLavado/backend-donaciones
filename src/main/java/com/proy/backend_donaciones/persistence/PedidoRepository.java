package com.proy.backend_donaciones.persistence;

import com.proy.backend_donaciones.domain.Order;
import com.proy.backend_donaciones.domain.repository.OrderRepository;
import com.proy.backend_donaciones.persistence.crud.AlimentoCrudRepository;
import com.proy.backend_donaciones.persistence.crud.PedidoCrudRepository;
import com.proy.backend_donaciones.persistence.crud.UsuarioCrudRepository;
import com.proy.backend_donaciones.persistence.entity.Pedido;
import com.proy.backend_donaciones.persistence.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepository implements OrderRepository {

    @Autowired private PedidoCrudRepository crud;
    @Autowired private OrderMapper mapper;
    // Inyectamos los otros repositorios necesarios para buscar las entidades
    @Autowired private AlimentoCrudRepository alimentoCrudRepository;
    @Autowired private UsuarioCrudRepository usuarioCrudRepository;

    @Override
    public List<Order> getAll() {
        return mapper.toOrders(crud.findAll());
    }

    @Override
    public Optional<Order> findById(long id) {
        return crud.findById(id).map(mapper::toOrder);
    }

    @Override
    public Order save(Order order) {
        // 1. Convertimos el objeto de dominio a la entidad básica (sin relaciones)
        Pedido pedido = mapper.toPedido(order);

        // 2. Buscamos y asignamos las entidades completas usando los IDs del objeto 'order'
        alimentoCrudRepository.findById(order.getAlimentoId())
                .ifPresent(pedido::setAlimento); // Asigna el alimento si se encuentra

        usuarioCrudRepository.findById(order.getDonanteId())
                .ifPresent(pedido::setDonante); // Asigna el donante si se encuentra

        // 3. Guardamos la entidad 'pedido' ahora sí completa y con sus relaciones
        return mapper.toOrder(crud.save(pedido));
    }

    @Override
    public void delete(long id) {
        crud.deleteById(id);
    }

     @Override
    public List<Order> getByDonorId(long donorId) {
        return mapper.toOrders(crud.findByDonanteId(donorId));
    }

    @Override
public List<Order> findByUsuarioEmail(String email) {
    return mapper.toOrders(crud.findByDonanteEmail(email));
}
}