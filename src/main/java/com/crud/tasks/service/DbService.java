package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DbService {

    private TaskRepository repository;

    public List<Task> getAllTasks() {

        return repository.findAll();
    }

    public Task getTask(final Long id) {

        return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public void deleteTask(final Long id) {
        repository.delete(id);
    }
}
