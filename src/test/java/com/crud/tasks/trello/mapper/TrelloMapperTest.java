package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("List", "1/1", false);
        List<TrelloListDto> listDto = Arrays.asList(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("Board", "1", listDto);
        List<TrelloBoardDto> boardDto = Arrays.asList(trelloBoardDto);

        //When
        List<TrelloBoard> mappedList = trelloMapper.mapToBoards(boardDto);

        //Then
        Assert.assertEquals(boardDto.get(0).getId(), mappedList.get(0).getId());
        Assert.assertEquals(boardDto.get(0).getName(), mappedList.get(0).getName());
        Assert.assertEquals(boardDto.get(0).getLists().get(0).getId(),
                mappedList.get(0).getLists().get(0).getId());
        Assert.assertEquals(boardDto.get(0).getLists().get(0).getName(),
                mappedList.get(0).getLists().get(0).getName());
        Assert.assertEquals(boardDto.get(0).getLists().get(0).isClosed(),
                mappedList.get(0).getLists().get(0).isClosed());

    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        TrelloList trelloList = new TrelloList("1/1", "List", false);
        List<TrelloList> list = Arrays.asList(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("1", "Board", list);
        List<TrelloBoard> board = Arrays.asList(trelloBoard);

        //When
        List<TrelloBoardDto> mappedList = trelloMapper.mapToBoardsDto(board);

        //Then
        Assert.assertEquals(board.get(0).getId(), mappedList.get(0).getId());
        Assert.assertEquals(board.get(0).getName(), mappedList.get(0).getName());
        Assert.assertEquals(board.get(0).getLists().get(0).getId(),
                mappedList.get(0).getLists().get(0).getId());
        Assert.assertEquals(board.get(0).getLists().get(0).getName(),
                mappedList.get(0).getLists().get(0).getName());
        Assert.assertEquals(board.get(0).getLists().get(0).isClosed(),
                mappedList.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("List", "1/1", false);
        List<TrelloListDto> listDto = Arrays.asList(trelloListDto);

        //When
        List<TrelloList> mappedList = trelloMapper.mapToList(listDto);

        //Then
        Assert.assertEquals(listDto.get(0).getId(), mappedList.get(0).getId());
        Assert.assertEquals(listDto.get(0).getName(), mappedList.get(0).getName());
        Assert.assertEquals(listDto.get(0).isClosed(), mappedList.get(0).isClosed());
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        TrelloList trelloList = new TrelloList("1/1", "List", false);
        List<TrelloList> list = Arrays.asList(trelloList);

        //When
        List<TrelloListDto> mappedList = trelloMapper.mapToListDto(list);

        //Then
        Assert.assertEquals(list.get(0).getId(), mappedList.get(0).getId());
        Assert.assertEquals(list.get(0).getName(), mappedList.get(0).getName());
        Assert.assertEquals(list.get(0).isClosed(), mappedList.get(0).isClosed());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "desc", "pos", "2/0");

        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals(trelloCardDto.getName(), mappedCard.getName());
        Assert.assertEquals(trelloCardDto.getDescription(), mappedCard.getDescription());
        Assert.assertEquals(trelloCardDto.getPos(), mappedCard.getPos());
        Assert.assertEquals(trelloCardDto.getListId(), mappedCard.getListId());
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card", "desc", "pos", "2/0");

        //When
        TrelloCardDto mappedCard = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals(trelloCard.getName(), mappedCard.getName());
        Assert.assertEquals(trelloCard.getDescription(), mappedCard.getDescription());
        Assert.assertEquals(trelloCard.getPos(), mappedCard.getPos());
        Assert.assertEquals(trelloCard.getListId(), mappedCard.getListId());
    }
}
