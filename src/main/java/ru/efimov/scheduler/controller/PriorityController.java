package ru.efimov.scheduler.controller;

import org.springframework.web.bind.annotation.*;
import ru.efimov.scheduler.entity.Priority;
import ru.efimov.scheduler.repository.PriorityRepository;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }


    @GetMapping("/test")
    public List<Priority> test() {
        List<Priority> list = priorityRepository.findAll();
        System.out.println("list: " + list);
        return list;
    }

    @PostMapping("/add")
    public Priority add(@RequestBody Priority priority) {
        return priorityRepository.save(priority);
    }
}
