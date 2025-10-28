package com.proy.backend_donaciones.domain.service;

import com.proy.backend_donaciones.domain.Order;
import com.proy.backend_donaciones.domain.User;
import com.proy.backend_donaciones.domain.repository.OrderRepository;
import com.proy.backend_donaciones.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public Optional<Order> getById(long id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        // Obtenemos el donante del contexto de seguridad
        String donanteEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User donante = userRepository.findByEmail(donanteEmail)
                .orElseThrow(() -> new RuntimeException("Usuario donante no encontrado"));

        // Asignamos los datos de forma segura
        order.setDonanteId(donante.getUserId());
        order.setFechaCreacion(LocalDateTime.now());
        order.setEstado("PENDIENTE");

        // Simplemente llamamos al repositorio, que ahora sabe cómo guardar todo
        return orderRepository.save(order);
    }

    public boolean delete(long id) {
        return getById(id).map(order -> {
            orderRepository.delete(id);
            return true;
        }).orElse(false);
    }
}