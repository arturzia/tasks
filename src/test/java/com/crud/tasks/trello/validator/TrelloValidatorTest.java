package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {

    @Autowired
    TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoards(){

        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "list1", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test1", trelloLists));
        trelloBoards.add(new TrelloBoard("2", "test2", trelloLists));

        //When
        List<TrelloBoard> excpectedTrelloBoardList = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertNotNull(excpectedTrelloBoardList);
        assertEquals(2, excpectedTrelloBoardList.size());
        assertEquals(excpectedTrelloBoardList.get(0).getId(),trelloBoards.get(0).getId());
        assertEquals(excpectedTrelloBoardList.get(0).getName(),trelloBoards.get(0).getName());
        assertEquals(excpectedTrelloBoardList.get(1).getId(),trelloBoards.get(1).getId());
        assertEquals(excpectedTrelloBoardList.get(1).getName(),trelloBoards.get(1).getName());
    }
}
