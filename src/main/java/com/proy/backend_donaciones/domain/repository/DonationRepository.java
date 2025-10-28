package com.proy.backend_donaciones.domain.repository;

import com.proy.backend_donaciones.domain.Donation;

import java.util.List;
import java.util.Optional;

public interface DonationRepository {

    /**
     * Guarda una nueva donación en la base de datos.
     * @param donation El objeto de donación a guardar.
     * @return La donación guardada con su ID asignado.
     */
    Donation save(Donation donation);

    /**
     * Busca todas las donaciones realizadas por un donante específico.
     * @param donorId El ID del donante.
     * @return Una lista de donaciones del usuario, ordenada por fecha de creación descendente.
     */
    List<Donation> findByDonorId(Long donorId);

    /**
     * Obtiene todas las donaciones registradas en el sistema.
     * (Útil para el panel de administrador).
     * @return Una lista con todas las donaciones.
     */
    List<Donation> getAll();

    /**
     * Busca una donación específica por su ID.
     * @param id El ID de la donación a buscar.
     * @return Un Optional que contiene la donación si se encuentra, o un Optional vacío si no.
     */
    Optional<Donation> findById(Long id);

    /**
     * Elimina una donación de la base de datos por su ID.
     * @param id El ID de la donación a eliminar.
     */
    void delete(Long id);
}