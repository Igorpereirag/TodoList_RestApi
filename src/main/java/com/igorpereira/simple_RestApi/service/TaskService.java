package com.igorpereira.simple_RestApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igorpereira.simple_RestApi.model.Task;
import com.igorpereira.simple_RestApi.model.User;
import com.igorpereira.simple_RestApi.repository.TaskRepository;
import com.igorpereira.simple_RestApi.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskService {
   
    @Autowired
    private TaskRepository taskrepository;
    @Autowired
    private UserRepository userRepository;

  

    public Task findtaskbyid(long id) {
        Optional<Task> task = this.taskrepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("task not found with id: " + id));

    }

    public Task create(Task task) {
        // Verificar se o usuário associado à tarefa existe no banco de dados
        Long userId = task.getUser().getId();
        Optional<User> userOptional = this.userRepository.findById(userId);
    
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            task.setUser(user);
    
            // Salvar a tarefa no repositório
            return this.taskrepository.save(task);
        } else {
            // Tratar o caso em que o usuário não foi encontrado
            throw new RuntimeException("Usuário com ID " + userId + " não encontrado.");
        }
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
    
    public List<Task> findAllByUserId(long userId){
    List<Task> tasks = this.taskrepository.findAllByUserId(userId);
    return tasks;
    }


}
