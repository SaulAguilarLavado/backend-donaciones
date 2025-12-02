package com.proy.backend_donaciones.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "donaciones")
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Paso 1: ¿Qué vas a donar? ---
    @Column(nullable = false, length = 50)
    private String categoriaAlimento;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Double cantidadAprox;

    @Column(nullable = false, length = 20)
    private String unidad;

    // --- Paso 2: ¿Desde dónde y cuándo? ---
    @Column(nullable = false, length = 255)
    private String direccionRecojo;

    @Column(nullable = false)
    private LocalDate fechaRecojo;

    @Column(nullable = false, length = 50)
    private String horaRecojo;

    @Column(nullable = false, length = 15)
    private String telefonoContacto;

    // --- Paso 3: ¿A quién quieres ayudar? ---
    @Column(nullable = false, length = 50)
    private String tipoAsignacion; // "INTELIGENTE" o "MANUAL"

    private Long idBeneficiario; // ID de la ONG o Albergue si es manual

    @Column(length = 20)
    private String tipoBeneficiario; // "ONG" o "ALBERGUE"

    // --- Paso 4: Confirmación ---
    @Column(nullable = false)
    private boolean aptoParaConsumo;

    

    // --- Datos del Sistema ---
    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoDonacion estado = EstadoDonacion.PENDIENTE;

    @ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "donante_id", nullable = false)
private Usuario donante;
    

  public enum EstadoDonacion {
    PENDIENTE,
    RECOJO_ASIGNADO,
    EN_CAMINO,
    ENTREGADO,
    COMPLETADA,    // ← AGREGAR ESTO
    CANCELADO
}


@Column(nullable = false, length = 50)
private String identificacion;

// Getter y Setter
public String getIdentificacion() {
    return identificacion;
}

public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    


    public String getCategoriaAlimento() {
        return categoriaAlimento;
    }

    public void setCategoriaAlimento(String categoriaAlimento) {
        this.categoriaAlimento = categoriaAlimento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCantidadAprox() {
        return cantidadAprox;
    }

    public void setCantidadAprox(Double cantidadAprox) {
        this.cantidadAprox = cantidadAprox;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getDireccionRecojo() {
        return direccionRecojo;
    }

    public void setDireccionRecojo(String direccionRecojo) {
        this.direccionRecojo = direccionRecojo;
    }

    public LocalDate getFechaRecojo() {
        return fechaRecojo;
    }

    public void setFechaRecojo(LocalDate fechaRecojo) {
        this.fechaRecojo = fechaRecojo;
    }

    public String getHoraRecojo() {
        return horaRecojo;
    }

    public void setHoraRecojo(String horaRecojo) {
        this.horaRecojo = horaRecojo;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getTipoAsignacion() {
        return tipoAsignacion;
    }

    public void setTipoAsignacion(String tipoAsignacion) {
        this.tipoAsignacion = tipoAsignacion;
    }

    public Long getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(Long idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public String getTipoBeneficiario() {
        return tipoBeneficiario;
    }

    public void setTipoBeneficiario(String tipoBeneficiario) {
        this.tipoBeneficiario = tipoBeneficiario;
    }

    public boolean isAptoParaConsumo() {
        return aptoParaConsumo;
    }

    public void setAptoParaConsumo(boolean aptoParaConsumo) {
        this.aptoParaConsumo = aptoParaConsumo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoDonacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoDonacion estado) {
        this.estado = estado;
    }

    public Usuario getDonante() {
        return donante;
    }

    public void setDonante(Usuario donante) {
        this.donante = donante;
    }
}