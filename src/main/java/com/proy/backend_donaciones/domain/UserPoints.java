package com.proy.backend_donaciones.domain;

import java.time.LocalDateTime;

public class UserPoints {

    private Long pointId;
    private Long userId;
    private int points;
    private String reason;
    private LocalDateTime dateAssigned;

    public Long getPointId() { return pointId; }
    public void setPointId(Long pointId) { this.pointId = pointId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public LocalDateTime getDateAssigned() { return dateAssigned; }
    public void setDateAssigned(LocalDateTime dateAssigned) { this.dateAssigned = dateAssigned; }
}
