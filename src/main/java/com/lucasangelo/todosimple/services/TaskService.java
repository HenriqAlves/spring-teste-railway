package com.lucasangelo.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasangelo.todosimple.models.Task;
import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(()-> new RuntimeException(
            "Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName()));
    }

    public List<Task> findAllByUserId(Long userId){
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }


    @Transactional
    public Task create (Task objeto){
        User user = this.userService.findById(objeto.getUser().getId());
        objeto.setId(null);
        objeto.setUser(user);
        objeto = this.taskRepository.save(objeto);
        return objeto;
    }

    @Transactional
    public Task update(Task objeto){
        Task newObjeto = findById(objeto.getId());
        newObjeto.setDescription(objeto.getDescription());
        return this.taskRepository.save(newObjeto);
    }

    public void delete(Long id){
        findById(id);

        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível deletar pois há entidades relacionadas");
        }
    }
}
