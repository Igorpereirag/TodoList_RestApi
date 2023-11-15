package com.igorpereira.simple_RestApi.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igorpereira.simple_RestApi.model.Task;
import com.igorpereira.simple_RestApi.service.TaskService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/Task")
@Validated
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @GetMapping("/{id}")
    public Task findbyid(@PathVariable Long id) {
        Task task = taskService.findtaskbyid(id);
        return task;
    }

    @PostMapping()
    public void create(@Valid @RequestBody Task newtask) {
        this.taskService.create(newtask);

    }

    @PutMapping("/{id}")
    public void upatade(@PathVariable Long id, @Valid @RequestBody Task taskupdate) {
        taskupdate.setId(id);
        this.taskService.upadate(taskupdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        this.taskService.delete(id);
    }

    @GetMapping("/Task/{UserId}")
    public List<Task> findAllByUserId(@PathVariable Long id){
        List<Task> tasks = this.taskService.findAllByUserId(id);
        return tasks;
    }

}
