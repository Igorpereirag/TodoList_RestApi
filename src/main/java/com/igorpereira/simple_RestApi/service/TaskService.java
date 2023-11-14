package com.igorpereira.simple_RestApi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.igorpereira.simple_RestApi.model.Task;
import com.igorpereira.simple_RestApi.model.User;
import com.igorpereira.simple_RestApi.repository.TaskRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TaskService {

    private TaskRepository taskrepository;
    private UserService userRepository;

    public Task findtaskbyid(long id) {
        Optional<Task> task = this.taskrepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("task not found with id: " + id));

    }

    public Task create(Task task) {
        // Verificar se o usuário associado à tarefa existe no banco de dados
        Long userId = task.getUsuario().getId();
        User user = this.userRepository.findById(userId);
        task.setUsuario(user);

        // Salvar a tarefa no repositório
        return this.taskrepository.save(task);

    }

    public Task upadate(Task newtask) {
        Task task = findtaskbyid(newtask.getId());
        task.setDescription(newtask.getDescription());
        return taskrepository.save(task);

    }

    public void delete(long id) {
        Task task = findtaskbyid(id);
        try {
            this.taskrepository.delete(task);
        } catch (Exception e) {
            throw new RuntimeException("description not found");
        }

    }

}
