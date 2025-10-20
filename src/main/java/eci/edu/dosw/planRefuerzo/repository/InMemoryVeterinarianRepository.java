package eci.edu.dosw.planRefuerzo.repository;

import eci.edu.dosw.planRefuerzo.model.Veterinarian;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryVeterinarianRepository {
    private final Map<Long, Veterinarian> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    @PostConstruct
    public void init() {
        save(new Veterinarian(null, "Dra. Laura", "General"));
        save(new Veterinarian(null, "Dr. Juan", "Cirugia"));
    }

    public Veterinarian save(Veterinarian v) {
        if (v.getId() == null) v.setId(seq.getAndIncrement());
        store.put(v.getId(), v);
        return v;
    }

    public Optional<Veterinarian> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Collection<Veterinarian> findAll() {
        return store.values();
    }
}

