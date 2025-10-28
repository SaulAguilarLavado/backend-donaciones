package com.proy.backend_donaciones.domain.service;


import com.proy.backend_donaciones.domain.Order;
import com.proy.backend_donaciones.domain.repository.OrderRepository;
import com.proy.backend_donaciones.persistence.PedidoRepository;
import com.proy.backend_donaciones.persistence.crud.AlimentoCrudRepository;
import com.proy.backend_donaciones.persistence.crud.UsuarioCrudRepository;
import com.proy.backend_donaciones.persistence.entity.Alimento;
import com.proy.backend_donaciones.persistence.entity.Pedido;
import com.proy.backend_donaciones.persistence.entity.Usuario;
import com.proy.backend_donaciones.persistence.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private AlimentoCrudRepository alimentoCrudRepository;

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private OrderMapper mapper;

    public List<Order> getAll() {
        return repository.getAll();
    }

    public Optional<Order> getById(long id) {
        return repository.findById(id);
    }

    public Order save(Order order) {
        // Convertimos el DTO a entidad
        Pedido pedido = mapper.toPedido(order);

        // Asignamos el alimento
        Alimento alimento = alimentoCrudRepository.findById(order.getAlimentoId())
                .orElseThrow(() -> new RuntimeException("Alimento no encontrado"));
        pedido.setAlimento(alimento);

        // Asignamos el donante
        Usuario donante = usuarioCrudRepository.findById(order.getDonanteId())
                .orElseThrow(() -> new RuntimeException("Donante no encontrado"));
        pedido.setDonante(donante);

        // Guardamos en la BD
        Pedido saved = pedidoRepository.saveEntity(pedido);

        // Retornamos el DTO convertido
        return mapper.toOrder(saved);
    }

    public boolean delete(long id) {
        return getById(id).map(order -> {
            repository.delete(id);
            return true;
        }).orElse(false);
    }
}