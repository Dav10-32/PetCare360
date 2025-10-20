package eci.edu.dosw.planRefuerzo.controller;

import eci.edu.dosw.planRefuerzo.dto.AppointmentRequest;
import eci.edu.dosw.planRefuerzo.dto.AppointmentResponse;
import eci.edu.dosw.planRefuerzo.facade.AppointmentFacade;
import eci.edu.dosw.planRefuerzo.model.Appointment;
import eci.edu.dosw.planRefuerzo.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AppointmentController {

    private final AppointmentFacade facade;
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentFacade facade, AppointmentService appointmentService) {
        this.facade = facade;
        this.appointmentService = appointmentService;
    }

    @PostMapping("/citas")
    public ResponseEntity<AppointmentResponse> schedule(@Valid @RequestBody AppointmentRequest req) {
        Appointment appt = facade.scheduleAppointment(req);
        return ResponseEntity.ok(toResponse(appt));
    }

    @GetMapping("/citas/{id}")
    public ResponseEntity<AppointmentResponse> get(@PathVariable Long id) {
        return appointmentService.getById(id)
                .map(a -> ResponseEntity.ok(toResponse(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/citas/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        facade.cancelAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/veterinarios/{vetId}/citas")
    public ResponseEntity<List<AppointmentResponse>> listByVet(@PathVariable Long vetId) {
        var list = appointmentService.listByVeterinarian(vetId).stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/mascotas/{petId}/citas")
    public ResponseEntity<List<AppointmentResponse>> listByPet(@PathVariable Long petId) {
        var list = appointmentService.listByPet(petId).stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    private AppointmentResponse toResponse(Appointment a) {
        return new AppointmentResponse(a.getId(), a.getClientId(), a.getPetId(), a.getVeterinarianId(), a.getDateTime(), a.getReason(), a.getStatus());
    }
}

