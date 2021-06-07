package io.group8.titan.server.controller;

import io.group8.titan.server.service.SolarSystemService;
import io.group8.titan.space.SolarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/SolarSystem")
public class SolarSystemController {

    SolarSystemService service;

    @Autowired
    public SolarSystemController(SolarSystemService service) {
        this.service = service;
    }

    @GetMapping
    public List<SolarSystem> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void create(@RequestBody SolarSystem solarSystem) {
        service.create(solarSystem);
    }
}
