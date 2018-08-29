package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/task")
@AllArgsConstructor
@Slf4j
public class TaskController {

    private final DbService service;

    @GetMapping
    public List<TaskDto> getTasks() {
        log.info("get all tasks called");

        return TaskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @GetMapping("{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {
        log.info("get task {} called", taskId);

        return TaskMapper.mapToTaskDto(service.getTask(taskId));
    }

    @DeleteMapping
    public void deleteTask(@RequestParam Long taskId) {
        service.deleteTask(taskId);
    }

    @PutMapping
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return TaskMapper.mapToTaskDto(service.saveTask(TaskMapper.mapToTask(taskDto)));
    }

    @PostMapping
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(TaskMapper.mapToTask(taskDto));
    }


}
