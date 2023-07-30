package ru.efimov.scheduler.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.efimov.scheduler.entity.Priority;
import ru.efimov.scheduler.repository.PriorityRepository;

import java.util.List;

import static io.micrometer.common.util.StringUtils.isBlank;
import static java.util.Objects.isNull;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private final PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }


    @GetMapping("/all")
    public List<Priority> findAll() {
         return priorityRepository.findAllByOrderByIdAsc();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Priority priority) {

        if (!isNull(priority.getId()) && priority.getId() != 0) {
            return new ResponseEntity<>("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (isBlank(priority.getTitle())) {
            return new ResponseEntity<>("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Priority priority) {

        if (isNull(priority.getId()) || priority.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (isBlank(priority.getTitle())) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        if (isBlank(priority.getColor())) {
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));
    }
}
