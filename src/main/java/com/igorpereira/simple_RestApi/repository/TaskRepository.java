package com.igorpereira.simple_RestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igorpereira.simple_RestApi.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
   
}

