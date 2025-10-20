package eci.edu.dosw.planRefuerzo.repository;

import eci.edu.dosw.planRefuerzo.model.Client;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryClientRepository {
    private final Map<Long, Client> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public Client save(Client c) {
        if (c.getId() == null) c.setId(seq.getAndIncrement());
        store.put(c.getId(), c);
        return c;
    }

    public Optional<Client> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Client> findAll() {
        return new ArrayList<>(store.values());
    }
}

