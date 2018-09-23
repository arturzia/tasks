package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {

    @Mock
    SimpleEmailService simpleEmailService;

    @Mock
    TaskRepository taskRepository;

    @Mock
    AdminConfig adminConfig;

    @InjectMocks
    EmailScheduler emailScheduler;

    @Captor
    ArgumentCaptor<Mail> mailArgumentCaptor;

    @Test
    public void sendInformationEmail_singleTaskCase() {

        //Given
        long count = 123L;
        when(taskRepository.count()).thenReturn(count);
        String adminMail = "adminMail";
        when(adminConfig.getAdminMail()).thenReturn(adminMail);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(mailArgumentCaptor.capture());
        Mail mailResponse = mailArgumentCaptor.getValue();

        Assert.assertEquals(adminMail, mailResponse.getMailTo());
        Assert.assertEquals("Tasks: Once a day email", mailResponse.getSubject());
        Assert.assertEquals("Currently in database you got: 123 tasks", mailResponse.getMessage());
        Assert.assertEquals("", mailResponse.getToCc());
    }


    @Test
    public void sendInformationEmail_manyTasksCase() {

        //Given
        long count = 1;
        when(taskRepository.count()).thenReturn(count);
        String adminMail = "adminMail";
        when(adminConfig.getAdminMail()).thenReturn(adminMail);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(mailArgumentCaptor.capture());
        Mail mailResponse = mailArgumentCaptor.getValue();

        Assert.assertEquals(adminMail, mailResponse.getMailTo());
        Assert.assertEquals("Tasks: Once a day email", mailResponse.getSubject());
        Assert.assertEquals("Currently in database you got: 1 task", mailResponse.getMessage());
        Assert.assertEquals("", mailResponse.getToCc());
    }
}
