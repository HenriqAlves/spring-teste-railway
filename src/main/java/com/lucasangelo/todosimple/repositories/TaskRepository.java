package com.lucasangelo.todosimple.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucasangelo.todosimple.models.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    Optional<Task> findById(Long id);
 
    List<Task> findByUser_Id(Long id);


    //* ORM JAVA SPRING */
    // @Query(value = "SELECT task FROM Task task WHERE task.user.id = : id")
    // List<Task> findByUser_id(@Param("id") Long id);


    //* SQL PADRÃO */
    // @Query(value = "SELECT * FROM task task WHERE task.user_id = :id", nativeQuery = true)
    // List<Task> findByUser_Id(@Param("id") Long id);
}
