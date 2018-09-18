package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void getAllTasksTest() {
        //Given
        Task task = new Task(1L, "task1", "content1");
        List<Task> taskList = Arrays.asList(task);

        when(repository.findAll()).thenReturn(taskList);

        //When
        List<Task> theList = dbService.getAllTasks();

        //Then
        assertNotNull(theList);
        assertEquals(1, theList.size());
    }

    @Test
    public void getTaskTest() {
        //Given
        Task task = new Task(1L, "task2", "content2");
        List<Task> taskList = Arrays.asList(task);
        Optional <Task> taskTest = Optional.of(taskList.get(0));
        when(repository.findById(1L)).thenReturn(taskTest);

        //When
        Optional<Task> result = dbService.getTask(1L);

        //Then
        assertEquals(taskTest, result);
    }

    @Test
    public void saveTaskTest(){
        //Given
        Task task = new Task(1L, "task3", "content3");
        when(repository.save(task)).thenReturn(task);

        //When
        Task savedTask = dbService.saveTask(task);

        //Then
        assertEquals(task.getId(), savedTask.getId());
        assertEquals(task.getTitle(), savedTask.getTitle());
        assertEquals(task.getContent(), savedTask.getContent());
    }

    @Test
    public void deleteTaskTest(){
        //Given

        //When
        dbService.deleteTask(1L);

        //Then
        verify(repository, times(1)).delete(1L);
    }
}
