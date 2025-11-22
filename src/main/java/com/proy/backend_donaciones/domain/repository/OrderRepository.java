package com.proy.backend_donaciones.domain.repository;

import com.proy.backend_donaciones.domain.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> getAll();
    Optional<Order> findById(long id);
    Order save(Order order);
    void delete(long id);
     List<Order> getByDonorId(long donorId);
     List<Order> findByUsuarioEmail(String email);

}
