package ru.efimov.scheduler.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.efimov.scheduler.entity.Category;
import ru.efimov.scheduler.repository.CategoryRepository;
import ru.efimov.scheduler.search.CategorySearchValues;

import java.util.List;

import static io.micrometer.common.util.StringUtils.isBlank;
import static java.util.Objects.isNull;

@RestController
@RequestMapping("/category")
@Log4j2
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/all")
    public List<Category> findAll() {
        log.info("CategoryController: findAll()-------------------------------------");
        return categoryRepository.findAllByOrderByTitleAsc();
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

    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues) {
        log.info("CategoryController: search(" + categorySearchValues.getText() + ")-------------------------------------");
        return ResponseEntity.ok(categoryRepository.findByTitle(categorySearchValues.getText()));
    }
}
