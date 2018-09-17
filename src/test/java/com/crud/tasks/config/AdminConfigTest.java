package com.crud.tasks.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminConfigTest {

    @Autowired
    AdminConfig adminConfig;

    @Test
    public void getAdminMail() {

        //Given & When
        String adminMail = adminConfig.getAdminMail();

        //Then
        assertThat(adminMail).isEqualTo("mail");

    }

    @Test
    public void getAdminName() {
    }
}