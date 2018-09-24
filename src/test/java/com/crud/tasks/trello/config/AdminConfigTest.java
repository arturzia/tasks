package com.crud.tasks.trello.config;


import com.crud.tasks.config.AdminConfig;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
@NoArgsConstructor
public class AdminConfigTest {

    @Autowired
    AdminConfig adminConfig;

    @Test
    public void testGetAdminMail() {
        //given
        String adminMail = "arturzia@gmail.com";
        //when
        String excpectAdminMail = adminConfig.getAdminMail();
        //then
        Assert.assertEquals(adminMail, excpectAdminMail);
    }

    @Test
    public void testGetAdminName() {
        //given
        String adminName = "arturzia";
        //when
        String excpectAdminName = adminConfig.getAdminName();
        //then
        Assert.assertEquals(adminName, excpectAdminName);
    }
}