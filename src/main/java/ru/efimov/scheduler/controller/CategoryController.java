package ru.efimov.scheduler.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.efimov.scheduler.entity.Category;
import ru.efimov.scheduler.repository.CategoryRepository;

import java.util.List;

import static io.micrometer.common.util.StringUtils.isBlank;
import static java.util.Objects.isNull;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/test")
    public List<Category> test() {
        List<Category> list = categoryRepository.findAll();
        System.out.println("list: " + list);
        return list;


    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Category category) {

        if (!isNull(category.getId()) && category.getId() != 0) {
            return new ResponseEntity<>("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (isBlank(category.getTitle())) {
            return new ResponseEntity<>("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Category category) {

        if (isNull(category.getId()) || category.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (isBlank(category.getTitle())) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }

}
