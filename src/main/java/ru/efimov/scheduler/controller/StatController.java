package ru.efimov.scheduler.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.efimov.scheduler.entity.Stat;
import ru.efimov.scheduler.repository.StatRepository;

@RestController
public class StatController {
    private final StatRepository statRepository;


    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    private static final Long defaultId = 1L;

    @GetMapping("/stat")
    public ResponseEntity<Stat> findById() {
        return ResponseEntity.ok(statRepository.findById(defaultId).get());
    }
}
