package com.crud.tasks.trello.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TrelloConfig {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String trelloUserName;

    public String getTrelloApiEndpoint() {
        return this.trelloApiEndpoint;
    }

    public String getTrelloAppKey() {
        return this.trelloAppKey;
    }

    public String getTrelloToken() {
        return this.trelloToken;
    }

    public String getTrelloUserName() {
        return this.trelloUserName;
    }
}
