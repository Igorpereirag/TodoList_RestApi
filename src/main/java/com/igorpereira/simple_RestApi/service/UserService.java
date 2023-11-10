package com.igorpereira.simple_RestApi.service;



import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.igorpereira.simple_RestApi.model.Task;
import com.igorpereira.simple_RestApi.model.User;
import com.igorpereira.simple_RestApi.repository.TaskRepository;
import com.igorpereira.simple_RestApi.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Service
@Data
public class UserService {

    private UserRepository userRepository;
    private TaskRepository taskRepository;
    public User findByUserId(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    
    public User createUser(User user) {
        if (this.userRepository.findById(user.getId()).isPresent()) {
            throw new RuntimeException("Usuário com o mesmo ID já existe no banco de dados");
        }
        try {
            // Tente salvar o usuário no repositório. O sistema de persistência gerará automaticamente o ID.
            user = this.userRepository.save(user);
            // Se o usuário tem tarefas associadas, salve-as também.
            if (user.getTasks() != null && !user.getTasks().isEmpty()) {
                for (Task task : user.getTasks()) {
                    task.setUsuario(user); // Defina o usuário da tarefa para estabelecer a relação.
                }
                this.taskRepository.saveAll(user.getTasks());
            }
        } catch (DataIntegrityViolationException e) {
            // Se uma exceção de violação de chave primária (ou similar) ocorrer, trate-a aqui.
            throw new RuntimeException("Usuário com o mesmo ID já existe no banco de dados.");
        }
    
        return user;
    }
    
    public User update(User newUser) {
        // Encontre o usuário existente com base no ID
        User existingUser = findByUserId(newUser.getId());
    
        // Atualize os campos do usuário com base nos valores fornecidos no novo usuário

        existingUser.setPassword(newUser.getPassword()); // Certifique-se de criptografar a senha, se necessário
        // Adicione mais campos para atualização, se necessário
    
        // Salve o usuário atualizado no repositório
        existingUser = this.userRepository.save(existingUser);
    
        return existingUser;
    }
    
    public void delete(Long userId) {
        // Verifique se o usuário existe antes de tentar excluí-lo
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new RuntimeException("Usuário não encontrado para exclusão.");
        }
    }
    

    
}
