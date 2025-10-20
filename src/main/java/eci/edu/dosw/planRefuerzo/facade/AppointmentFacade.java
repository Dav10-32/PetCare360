package eci.edu.dosw.planRefuerzo.facade;

import eci.edu.dosw.planRefuerzo.dto.AppointmentRequest;
import eci.edu.dosw.planRefuerzo.model.Appointment;
import eci.edu.dosw.planRefuerzo.service.AppointmentService;
import org.springframework.stereotype.Component;

@Component
public class AppointmentFacade {

    private final AppointmentService appointmentService;

    public AppointmentFacade(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public Appointment scheduleAppointment(AppointmentRequest req) {
        // Aquí podríamos orquestar más pasos: validar políticas, notificar, facturar (si aplica)
        Appointment appt = appointmentService.schedule(req);
        // Notificar: (si tuvieras NotifService lo llamarías aquí)
        return appt;
    }

    public void cancelAppointment(Long appointmentId) {
        appointmentService.cancel(appointmentId);
        // Notificar cancelación si es necesario
    }
}

