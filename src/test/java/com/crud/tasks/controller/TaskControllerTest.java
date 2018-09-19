package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "Test_title1", "Test_content1");
        TaskDto taskDto2 = new TaskDto(2L, "Test_title2", "Test_content2");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto1);
        taskDtoList.add(taskDto2);

        when(taskMapper.mapToTaskDtoList(Mockito.anyList())).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test_title1")))
                .andExpect(jsonPath("$[0].content", is("Test_content1")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        Task task = new Task(1L, "Task", "Test1");
        TaskDto taskDto = new TaskDto(1L, "Task", "Test1");

        when(service.getTask(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(Mockito.any(Task.class))).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON).param("taskId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task")))
                .andExpect(jsonPath("$.content", is("Test1")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        Long taskId = 1L;

        //When
        mockMvc.perform(delete("/v1/task/deleteTask")
                .param("taskId", taskId.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());

        //Then
        verify(service, times(1)).deleteTask(taskId);
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(10L, "Test_title1", "Test_content1");
        Task task = new Task();

        when(taskMapper.mapToTask(any())).thenReturn(task);
        when(service.saveTask(task)).thenReturn(any());
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask").content(jsonContent).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.title", is("Test_title1")))
                .andExpect(jsonPath("$.content", is("Test_content1")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(10L, "Test_title1", "Test_content1");
        Task task = new Task(10L, "Test_title1", "Test_content1");

        taskMapper.mapToTask(taskDto);
        service.saveTask(task);

        //When & Then
        verify(taskMapper, times(1)).mapToTask(taskDto);
        verify(service, times(1)).saveTask(task);
    }

}
