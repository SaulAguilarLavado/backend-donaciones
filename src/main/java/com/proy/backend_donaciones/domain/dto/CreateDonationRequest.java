package com.proy.backend_donaciones.domain.dto;

import java.time.LocalDate;

// Contiene todos los campos que el usuario llena en el formulario
public class CreateDonationRequest {
    // Paso 1
    private String foodCategory;
    private String description;
    private Double approximateQuantity;
    private String unit;
    // Paso 2
    private String pickupAddress;
    private LocalDate pickupDate;
    private String pickupTime;
    private String contactPhone;
    // Paso 3
    private String assignmentType;
    private Long beneficiaryId;
    private String beneficiaryType;
    // Paso 4
    private boolean isConsumable;

    private String identificacion;

public String getIdentificacion() {
    return identificacion;
}

public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
}

    //<editor-fold desc="Getters y Setters">

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getApproximateQuantity() {
        return approximateQuantity;
    }

    public void setApproximateQuantity(Double approximateQuantity) {
        this.approximateQuantity = approximateQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getBeneficiaryType() {
        return beneficiaryType;
    }

    public void setBeneficiaryType(String beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }

    public boolean isConsumable() {
        return isConsumable;
    }

    public void setConsumable(boolean consumable) {
        isConsumable = consumable;
    }

    //</editor-fold>
}