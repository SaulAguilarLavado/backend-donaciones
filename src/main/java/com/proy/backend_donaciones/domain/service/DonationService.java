package com.proy.backend_donaciones.domain.service;

import com.proy.backend_donaciones.domain.Donation;
import com.proy.backend_donaciones.domain.User;
import com.proy.backend_donaciones.domain.dto.CreateDonationRequest;
import com.proy.backend_donaciones.domain.repository.DonationRepository;
import com.proy.backend_donaciones.domain.repository.UserRepository;
import com.proy.backend_donaciones.persistence.entity.Donacion;
import com.proy.backend_donaciones.persistence.entity.Donacion.EstadoDonacion;
import com.proy.backend_donaciones.persistence.DonacionRepository;
import com.proy.backend_donaciones.persistence.AlbergueRepository;
import com.proy.backend_donaciones.persistence.OngRepository;
import com.proy.backend_donaciones.domain.Shelter;
import com.proy.backend_donaciones.domain.Ngo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DonationService {

      @Autowired 
    private DonationRepository donationRepository;

    @Autowired 
    private UserRepository userRepository;

    @Autowired 
    private PuntoUsuarioService puntoUsuarioService;

    @Autowired
    private DonacionRepository donacionEntityRepository;

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private AlbergueRepository albergueRepository;

    
    


    // ---------------------- CRUD DOMAIN ----------------------

    public List<Donation> getAll() {
        List<Donation> donations = donationRepository.getAll();
        donations.forEach(d -> d.setBeneficiaryName(
                obtenerNombreBeneficiario(d.getBeneficiaryId(), d.getBeneficiaryType())
        ));
        return donations;
    }

    public Optional<Donation> getById(Long id) {
        Optional<Donation> donation = donationRepository.findById(id);
        donation.ifPresent(d -> d.setBeneficiaryName(
                obtenerNombreBeneficiario(d.getBeneficiaryId(), d.getBeneficiaryType())
        ));
        return donation;
    }

    public Donation save(Donation donation) {
        // Obtener usuario autenticado
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User donor = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        donation.setDonorId(donor.getUserId());
        donation.setCreationDate(LocalDateTime.now());
        donation.setStatus("PENDIENTE");

        Donation saved = donationRepository.save(donation);

        // Asignar nombre del beneficiario
        saved.setBeneficiaryName(
                obtenerNombreBeneficiario(saved.getBeneficiaryId(), saved.getBeneficiaryType())
        );

        return saved;
    }

    public boolean delete(Long id) {
        return getById(id).map(donation -> {
            donationRepository.delete(id);
            return true;
        }).orElse(false);
    }

    public List<Donation> getMyDonations(Long donorId) {
        List<Donation> donations = donationRepository.findByDonorId(donorId);
        donations.forEach(d -> d.setBeneficiaryName(
                obtenerNombreBeneficiario(d.getBeneficiaryId(), d.getBeneficiaryType())
        ));
        return donations;
    }

    public List<Donation> getMyDonationsByEmail(String email) {
        List<Donation> donations = donationRepository.findByUsuarioEmail(email);
        donations.forEach(d -> d.setBeneficiaryName(
                obtenerNombreBeneficiario(d.getBeneficiaryId(), d.getBeneficiaryType())
        ));
        return donations;
    }

    // ---------------------- CAMBIO DE ESTADO (ENTITY JPA) ----------------------

  public Donacion cambiarEstado(Long id, EstadoDonacion nuevoEstado) {

    Donacion donacion = donacionEntityRepository.findEntityById(id)
            .orElseThrow(() -> new RuntimeException("Donación no encontrada"));

    EstadoDonacion estadoAnterior = donacion.getEstado();

    if (estadoAnterior == nuevoEstado) {
        return donacion;
    }

    // Cambiar estado
    donacion.setEstado(nuevoEstado);

    // Guardar
    Donacion guardada = donacionEntityRepository.saveEntity(donacion);

    // Asignar puntos solo cuando pasa a COMPLETADA
    if (nuevoEstado == EstadoDonacion.COMPLETADA &&
        estadoAnterior != EstadoDonacion.COMPLETADA) {

        puntoUsuarioService.asignarPuntos(
                guardada.getDonante().getId(),
                10,
                "Donación completada: " +
                        guardada.getCategoriaAlimento() + " (" +
                        guardada.getCantidadAprox() + " " +
                        guardada.getUnidad() + ")"
        );
    }

    return guardada;
}




    // ---------------------- CREAR DONACIÓN DOMAIN ----------------------

    public Donation createDonation(CreateDonationRequest request) {
        Donation donation = new Donation();
        donation.setFoodCategory(request.getFoodCategory());
        donation.setDescription(request.getDescription());
        donation.setApproximateQuantity(request.getApproximateQuantity());
        donation.setUnit(request.getUnit());
        donation.setPickupAddress(request.getPickupAddress());
        donation.setPickupDate(request.getPickupDate());
        donation.setPickupTime(request.getPickupTime());
        donation.setContactPhone(request.getContactPhone());
        donation.setAssignmentType(request.getAssignmentType());
        donation.setBeneficiaryId(request.getBeneficiaryId());
        donation.setBeneficiaryType(request.getBeneficiaryType());
        donation.setConsumable(request.isConsumable());

        // Validación de identificacion
        String ident = request.getIdentificacion();
        if (ident == null || !(ident.equalsIgnoreCase("USUARIO INDEPENDIENTE") 
                              || ident.equalsIgnoreCase("PANADERIA") 
                              || ident.equalsIgnoreCase("RESTAURANTE"))) {
            donation.setIdentificationType("USUARIO INDEPENDIENTE");
        } else {
            donation.setIdentificationType(ident.toUpperCase());
        }

        return save(donation);
    }

    // ---------------------- MÉTODO PARA OBTENER NOMBRE DEL BENEFICIARIO ----------------------

    private String obtenerNombreBeneficiario(Long idBeneficiario, String tipoBeneficiario) {
    if (idBeneficiario == null || tipoBeneficiario == null) return "-";

    if ("ONG".equalsIgnoreCase(tipoBeneficiario)) {
        Optional<Ngo> ngoOpt = ongRepository.findById(idBeneficiario);
        return ngoOpt.map(Ngo::getName).orElse("-"); // ← usa getName()
    } else if ("ALBERGUE".equalsIgnoreCase(tipoBeneficiario)) {
        Optional<Shelter> shelterOpt = albergueRepository.findById(idBeneficiario);
        return shelterOpt.map(Shelter::getName).orElse("-"); // ← usa getName()
    } else {
        return "-";
    }
}
}