package io.group8.titan.server.service;

import io.group8.titan.server.repository.SolarSystemRepository;
import io.group8.titan.space.SolarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolarSystemService {

    private final SolarSystemRepository repository;

    @Autowired
    public SolarSystemService(SolarSystemRepository repository) {
        this.repository = repository;
    }

    public List<SolarSystem> getAll() {
        return repository.findAll();
    }

    public void create(SolarSystem solarSystem) {
        repository.save(solarSystem);
    }
}
