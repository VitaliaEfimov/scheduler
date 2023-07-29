package ru.efimov.scheduler.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.efimov.scheduler.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
