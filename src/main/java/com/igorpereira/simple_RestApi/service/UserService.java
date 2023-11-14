package com.igorpereira.simple_RestApi.service;



import org.springframework.stereotype.Service;

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
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    
    public User createUser(User user) {
        if (this.userRepository.findById(user.getId()).isPresent()) {
            throw new RuntimeException("Usuário com o mesmo ID já existe no banco de dados");
        }           
        user = this.userRepository.save(user);
        return user;
    }
    
    public User update(User newUser) {
        // Encontre o usuário existente com base no ID
        User existingUser = findById(newUser.getId());
    
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
