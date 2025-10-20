package eci.edu.dosw.planRefuerzo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import eci.edu.dosw.planRefuerzo.enums.StatusAppointment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    private Long id;
    private Long clientId;
    private Long petId;
    private Long veterinarianId;
    private LocalDateTime dateTime;
    private Long serviceId;
    private String reason;
    private StatusAppointment status;
}

