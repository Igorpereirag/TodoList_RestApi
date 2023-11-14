package com.igorpereira.simple_RestApi.service;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igorpereira.simple_RestApi.model.User;

import com.igorpereira.simple_RestApi.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Service
@Data
public class UserService {

    @Autowired
    private UserRepository userRepository;
   

    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    
    public void createUser(User newUser) {
        // Verificar se o usuário com o mesmo ID já existe
        if (this.userRepository.findById(newUser.getId()).isPresent()) {
            throw new RuntimeException("Usuário com o mesmo ID já existe no banco de dados");
        }
    
        // Verificar se o usuário com o mesmo username já existe
        User existingUser = this.userRepository.findByUsername(newUser.getUsername());
    
        if (existingUser != null) {
            throw new RuntimeException("Usuário com o mesmo username já existe no banco de dados");
        }
    
        // Salvar o novo usuário
        this.userRepository.save(newUser);
    }
    
    
    public User update(User user) {
        // Encontre o usuário existente com base no ID
        User existingUser = findById(user.getId());
    
        // Atualize os campos do usuário com base nos valores fornecidos no novo usuário

        existingUser.setPassword(user.getPassword()); // Certifique-se de criptografar a senha, se necessário
        // Adicione mais campos para atualização, se necessário
    
        // Salve o usuário atualizado no repositório
        existingUser = this.userRepository.save(existingUser);
    
        return existingUser;
    }
    
    public void delete(Long userId) {
        // Verifique se o usuário existe antes de tentar excluí-lo
        if (this.userRepository.existsById(userId)) {
            this.userRepository.deleteById(userId);
        } else {
            throw new RuntimeException("Usuário não encontrado para exclusão.");
        }
    }
    

    
}
