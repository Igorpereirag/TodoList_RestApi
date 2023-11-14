package com.igorpereira.simple_RestApi.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igorpereira.simple_RestApi.model.User;
import com.igorpereira.simple_RestApi.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/User")
@Validated
@AllArgsConstructor
public class UserController {
 
    private  UserService userService;
    

   
    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        return this.userService.findById(id);
    }

    @PostMapping
    public void create(@Valid @RequestBody User newUser) {
         this.userService.createUser(newUser);    
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody User userUpdate) {
        userUpdate.setId(id);
        this.userService.update(userUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        this.userService.delete(id);
    }
}






    

