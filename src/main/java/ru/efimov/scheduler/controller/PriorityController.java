package ru.efimov.scheduler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.efimov.scheduler.entity.Priority;
import ru.efimov.scheduler.repository.PriorityRepository;

import java.util.List;

@RestController
@RequestMapping ("/priority")
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


}
