package eci.edu.dosw.planRefuerzo.service;

import eci.edu.dosw.planRefuerzo.dto.AppointmentRequest;
import eci.edu.dosw.planRefuerzo.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment schedule(AppointmentRequest req);
    Optional<Appointment> getById(Long id);
    void cancel(Long id);
    List<Appointment> listByVeterinarian(Long vetId);
    List<Appointment> listByPet(Long petId);
}

