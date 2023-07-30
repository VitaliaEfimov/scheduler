package ru.efimov.scheduler.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.efimov.scheduler.entity.Category;
import ru.efimov.scheduler.entity.Priority;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    @Query("SELECT p from Priority p where " +
            "(:title is null or :title='' or lower(p.title) like lower(concat('%', :title, '%'))) " +
            "order by p.title asc")
    List<Priority> findByTitle(@Param("title") String title);

    List<Priority> findAllByOrderByIdAsc();
}
