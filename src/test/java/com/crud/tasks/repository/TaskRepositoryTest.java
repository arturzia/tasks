package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void findById() {

        //Given
        Task task = new Task();
        task.setContent("mockcontent");
        task.setTitle("mocktitle");
        taskRepository.save(task);

        //When
        Optional<Task> taskOptional = taskRepository.findById(task.getId());

        //Then
        assertThat(taskOptional.isPresent()).isTrue();
        assertThat(taskOptional.get()).isEqualTo(task);

    }
}