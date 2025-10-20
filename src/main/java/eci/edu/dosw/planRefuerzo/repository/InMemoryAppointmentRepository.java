package eci.edu.dosw.planRefuerzo.repository;

import eci.edu.dosw.planRefuerzo.model.Appointment;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryAppointmentRepository {
    private final Map<Long, Appointment> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public Appointment save(Appointment appt) {
        if (appt.getId() == null) appt.setId(seq.getAndIncrement());
        store.put(appt.getId(), appt);
        return appt;
    }

    public Optional<Appointment> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public void deleteById(Long id) {
        store.remove(id);
    }

    public List<Appointment> findAll() {
        return new ArrayList<>(store.values());
    }

    public List<Appointment> findByVeterinarianId(Long vetId) {
        return store.values().stream()
                .filter(a -> Objects.equals(a.getVeterinarianId(), vetId))
                .collect(Collectors.toList());
    }

    public List<Appointment> findByPetId(Long petId) {
        return store.values().stream()
                .filter(a -> Objects.equals(a.getPetId(), petId))
                .collect(Collectors.toList());
    }

    public List<Appointment> findByVeterinarianAndDateTime(Long vetId, LocalDateTime dateTime) {
        return store.values().stream()
                .filter(a -> Objects.equals(a.getVeterinarianId(), vetId)
                        && a.getDateTime().equals(dateTime)
                        && !"CANCELLED".equals(a.getStatus()))
                .collect(Collectors.toList());
    }
}

