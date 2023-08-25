package com.lucasangelo.todosimple.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasangelo.todosimple.models.Task;
import com.lucasangelo.todosimple.services.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @GetMapping("/id")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        
        Task objeto = this.taskService.findById(id);
        return ResponseEntity.ok(objeto);

    }

    //* Listar todas as tasks de um usuário */
    @GetMapping("/user/{userId}")
     public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long userId){
        List<Task> tasks = this.taskService.findAllByUserId(userId);
        return ResponseEntity.ok().body(tasks);
    }



    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Task objeto){
        this.taskService.create(objeto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(objeto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PostMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Task objeto, @PathVariable Long id){
        objeto.setId(id);
        this.taskService.update(objeto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

   
}
