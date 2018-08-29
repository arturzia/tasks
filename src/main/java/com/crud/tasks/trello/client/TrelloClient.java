package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@AllArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;
    private final UriFactory uriFactory;

    public List<TrelloBoardDto> getTrelloBoards() {

        URI url = uriFactory.getTrelloBoardsUrl();

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));

    }


}
