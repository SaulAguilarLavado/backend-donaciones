package com.proy.backend_donaciones.domain;

import java.time.LocalDateTime;

public class Order {

    private Long id;
    private Long alimentoId;
    private String alimentoNombre;
    private Long donanteId;
    private String donanteNombre;
    private LocalDateTime fechaCreacion;
    private String estado; 


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAlimentoId() { return alimentoId; }
    public void setAlimentoId(Long alimentoId) { this.alimentoId = alimentoId; }

    public String getAlimentoNombre() { return alimentoNombre; }
    public void setAlimentoNombre(String alimentoNombre) { this.alimentoNombre = alimentoNombre; }

    public Long getDonanteId() { return donanteId; }
    public void setDonanteId(Long donanteId) { this.donanteId = donanteId; }

    public String getDonanteNombre() { return donanteNombre; }
    public void setDonanteNombre(String donanteNombre) { this.donanteNombre = donanteNombre; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
   
}