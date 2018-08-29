package com.crud.tasks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Task not found")
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(long id) {
        super("Task with id " + id + " not found");
    }
}
