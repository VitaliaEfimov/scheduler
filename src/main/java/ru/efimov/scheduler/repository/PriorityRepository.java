package ru.efimov.scheduler.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.efimov.scheduler.entity.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

}
