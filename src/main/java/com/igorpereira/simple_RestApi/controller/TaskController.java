package com.igorpereira.simple_RestApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igorpereira.simple_RestApi.model.Task;
import com.igorpereira.simple_RestApi.service.TaskService;

@RestController
@RequestMapping("/Task")
@Validated
public class TaskController {
    @Autowired
    private TaskService tasksService;

@GetMapping("/{id}")
public Task findbyid(long id){
 Task task = tasksService.findtaskbyid(id);
 return task;
}

}
