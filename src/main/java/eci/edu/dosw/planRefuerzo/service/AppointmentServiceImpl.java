package eci.edu.dosw.planRefuerzo.service;

import eci.edu.dosw.planRefuerzo.dto.AppointmentRequest;
import eci.edu.dosw.planRefuerzo.model.*;
import eci.edu.dosw.planRefuerzo.repository.*;
import eci.edu.dosw.planRefuerzo.enums.*;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final InMemoryAppointmentRepository apptRepo;
    private final InMemoryVeterinarianRepository vetRepo;
    private final InMemoryPetRepository petRepo;
    private final InMemoryClientRepository clientRepo;

    public AppointmentServiceImpl(InMemoryAppointmentRepository apptRepo,
                                  InMemoryVeterinarianRepository vetRepo,
                                  InMemoryPetRepository petRepo,
                                  InMemoryClientRepository clientRepo) {
        this.apptRepo = apptRepo;
        this.vetRepo = vetRepo;
        this.petRepo = petRepo;
        this.clientRepo = clientRepo;
    }

    @Override
    public Appointment schedule(AppointmentRequest req) {
        // 1. Validate veterinarian exists
        var vet = vetRepo.findById(req.getVeterinarianId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        // 2. Business rule: vet cannot have two appointments at the same time
        LocalDateTime dt = req.getDateTime();
        var collisions = apptRepo.findByVeterinarianAndDateTime(vet.getId(), dt);
        if (!collisions.isEmpty()) {
            throw new RuntimeException("El veterinario ya tiene una cita en esa hora");
        }

        // 3. Create or use client and pet (simulated persistence)
        Client client = new Client(null, req.getClientName(), req.getClientEmail(), req.getClientPhone(), null, null,null);
        client = clientRepo.save(client);

        // Pet creation using Factory Method
        Pet pet;
        switch (req.getPetType().toLowerCase()) {
            case "dog":
                pet = new Dog();
                break;
            case "cat":
                pet = new Cat();
                break;
            default:
                throw new RuntimeException("Invalid pet type: " + req.getPetType());
        }

        pet.setName(req.getPetName());
        pet.setAge(req.getPetAge());
        pet.setOwnerId(client.getId());
        pet = petRepo.save(pet);

        // 4. Create Appointment
        Appointment a = new Appointment();
        a.setClientId(client.getId());
        a.setPetId(pet.getId());
        a.setVeterinarianId(vet.getId());
        a.setDateTime(dt);
        a.setReason(req.getReason());
        a.setStatus(StatusAppointment.PENDING);

        return apptRepo.save(a);
    }

    @Override
    public Optional<Appointment> getById(Long id) {
        return apptRepo.findById(id);
    }

    @Override
    public void cancel(Long id) {
        var opt = apptRepo.findById(id);
        if (opt.isEmpty()) {
            throw new RuntimeException("Appointment not found");
        }
        var appt = opt.get();
        appt.setStatus(StatusAppointment.CANCELLED);
        apptRepo.save(appt);
    }

    @Override
    public List<Appointment> listByVeterinarian(Long vetId) {
        return apptRepo.findByVeterinarianId(vetId);
    }

    @Override
    public List<Appointment> listByPet(Long petId) {
        return apptRepo.findByPetId(petId);
    }
}
