package com.proy.backend_donaciones.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "puntos_usuario")
public class PuntoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private int puntos;

    @Column(nullable = false, length = 255)
    private String motivo;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    // Getters y Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public int getPuntos() { return puntos; }

    public void setPuntos(int puntos) { this.puntos = puntos; }

    public String getMotivo() { return motivo; }

    public void setMotivo(String motivo) { this.motivo = motivo; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }

    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
