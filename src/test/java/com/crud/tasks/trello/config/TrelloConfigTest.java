package com.crud.tasks.trello.config;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTest {

    @Autowired
    TrelloConfig trelloConfig;

    @Test
    public void testGetTrelloApiEndpoint() {
        //given
        String trelloApiEndpoint = "https://api.trello.com/1";
        //when
        String excpectApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        //then
        Assert.assertEquals(trelloApiEndpoint, excpectApiEndpoint);
    }
    @Test
    public void testGetTrelloAppKey() {
        //given
        String trelloAppKey = "1466b4555cf77065a937765d8d7d0481";
        //when
        String excpectTrelloAppKey = trelloConfig.getTrelloAppKey();
        //then
        Assert.assertEquals(trelloAppKey, excpectTrelloAppKey);
    }

    @Test
    public void testGetTrelloAppToken() {
        //given
        String trelloAppToken = "acbd309c53aec4c6a8f2183357e790b4dd73f5d24e1aed20ee1df10f7d0fc60d";
        //when
        String excpectTrelloAppToken = trelloConfig.getTrelloToken();
        //then
        Assert.assertEquals(trelloAppToken, excpectTrelloAppToken);
    }

    @Test
    public void testGetTrelloUsername() {
        //given
        String trelloUsername = "arturzia";
        //when
        String excpectTrelloUsername = trelloConfig.getTrelloUserName();
        //then
        Assert.assertEquals(trelloUsername, excpectTrelloUsername);
    }
}
