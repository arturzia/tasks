package com.crud.tasks.trello.mapper;



import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;


    @Test
    public void mapToTaskTest() {
        //Given
        Task task = new Task(1L, "task1", "content1");
        TaskDto taskDto = new TaskDto(1L, "task1", "content1");

        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(task.getId(), mappedTask.getId());
        assertEquals(task.getTitle(), mappedTask.getTitle());
        assertEquals(task.getContent(), mappedTask.getContent());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "task2", "content2");
        Task task = new Task(1L, "task2", "content2");

        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(taskDto.getId(), mappedTaskDto.getId());
        assertEquals(taskDto.getTitle(), mappedTaskDto.getTitle());
        assertEquals(taskDto.getContent(), mappedTaskDto.getContent());
    }

    @Test
    public void mapTaskDtoListTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "task3", "content3");
        List<TaskDto> taskDtoList = Arrays.asList(taskDto);

        Task task = new Task(1L, "task3", "content3");
        List<Task> taskList = Arrays.asList(task);

        //When
        List<TaskDto> mappedList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertNotNull(mappedList);
        assertEquals(1, mappedList.size());

        mappedList.forEach(t -> {
            assertEquals(taskDto.getId(), t.getId());
            assertEquals(taskDto.getTitle(), t.getTitle());
            assertEquals(taskDto.getContent(),t.getContent());
        });
    }
}
