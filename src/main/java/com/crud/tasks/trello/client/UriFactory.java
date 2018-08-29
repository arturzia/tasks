package com.crud.tasks.trello.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class UriFactory {

    private static final String MEMBERS = "/members/";
    private static final String BOARDS = "/boards";
    private static final String KEY = "key";
    private static final String TOKEN = "token";
    private static final String FIELDS = "fields";
    private static final String PARAMS = "name,id";

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String trelloUserName;

    URI getTrelloBoardsUrl() {

        return UriComponentsBuilder
                .fromHttpUrl(trelloApiEndpoint + MEMBERS + trelloUserName + BOARDS)
                .queryParam(KEY, trelloAppKey)
                .queryParam(TOKEN, trelloToken)
                .queryParam(FIELDS, PARAMS)
                .build().encode().toUri();

    }
}
