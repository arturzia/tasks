package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private SimpleEmailService emailService;


    @Test
    public void fetchTrelloBoardsTest() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "Test List", false));

        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "Test_name", trelloLists));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDto);

        //When
        List<TrelloBoardDto> theList = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, theList.size());

    }

   @Test
    public void createTrelloCardTest() {
        //Given
        BadgesDto trelloBadgesDto = new BadgesDto(5, new
                AttachmentsByTypeDto(new Trello(3, 4)));

        CreatedTrelloCardDto createdCardDto = new CreatedTrelloCardDto("card2", "1", "http://trello", trelloBadgesDto);
        TrelloCardDto newCard = new TrelloCardDto("card2", "desc", "pos", "1");

        when(trelloClient.createNewCard(newCard)).thenReturn(createdCardDto);
        when(adminConfig.getAdminMail()).thenReturn("mail@mail.com");
        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createdTrelloCard(newCard);

        //Then
        assertEquals(createdCardDto.getId(), createdTrelloCardDto.getId());
        assertEquals(createdCardDto.getName(), createdTrelloCardDto.getName());
        assertEquals(createdCardDto.getShortUrl(), createdTrelloCardDto.getShortUrl());
    }


}