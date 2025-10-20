package eci.edu.dosw.planRefuerzo.repository;

import eci.edu.dosw.planRefuerzo.model.Pet;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryPetRepository {
    private final Map<Long, Pet> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public Pet save(Pet p) {
        if (p.getId() == null) p.setId(seq.getAndIncrement());
        store.put(p.getId(), p);
        return p;
    }

    public Optional<Pet> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Pet> findAll() {
        return new ArrayList<>(store.values());
    }
}
