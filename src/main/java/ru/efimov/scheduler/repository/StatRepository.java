package ru.efimov.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import ru.efimov.scheduler.entity.Stat;

public interface StatRepository extends CrudRepository<Stat, Long> {
}
