package com.proy.backend_donaciones.domain.dto;

public class RankingDTO {

    private String nombreUsuario;
    private Long puntosTotales;

    public RankingDTO(String nombreUsuario, Long puntosTotales) {
        this.nombreUsuario = nombreUsuario;
        this.puntosTotales = puntosTotales;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Long getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(Long puntosTotales) {
        this.puntosTotales = puntosTotales;
    }
}
