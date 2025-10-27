package com.proy.backend_donaciones.domain.service;

import com.proy.backend_donaciones.domain.dto.DashboardDto;
import com.proy.backend_donaciones.domain.repository.FoodRepository;
import com.proy.backend_donaciones.domain.repository.ShelterRepository;
import com.proy.backend_donaciones.domain.repository.NgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired private FoodRepository foodRepository;
    @Autowired private ShelterRepository shelterRepository;
    @Autowired private NgoRepository ngoRepository;

    public DashboardDto getDashboardPreview() {
        DashboardDto dto = new DashboardDto();
        dto.setLatestFoods(foodRepository.findLatest3());
        dto.setLatestShelters(shelterRepository.findLatest3());
        dto.setLatestNgos(ngoRepository.findLatest3());
        return dto;
    }
}