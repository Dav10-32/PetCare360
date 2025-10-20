package eci.edu.dosw.planRefuerzo.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequest {
    // Pet info
    @NotBlank
    private String petName;
    @NotBlank
    private String petType;
    @NotNull
    private Integer petAge;

    @NotNull
    private Long veterinarianId;

    // Appointment
    @NotNull
    @Future
    private LocalDateTime dateTime;
    @NotBlank
    private String reason;

    // Client info
    @NotBlank
    private String clientName;
    @NotBlank
    private String clientEmail;
    @NotBlank
    private String clientPhone;
}

