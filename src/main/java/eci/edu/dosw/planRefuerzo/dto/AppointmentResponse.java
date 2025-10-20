package eci.edu.dosw.planRefuerzo.dto;

import eci.edu.dosw.planRefuerzo.enums.StatusAppointment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppointmentResponse {
    private Long id;
    private Long clientId;
    private Long petId;
    private Long veterinarianId;
    private LocalDateTime dateTime;
    private String reason;
    private StatusAppointment status;
}

