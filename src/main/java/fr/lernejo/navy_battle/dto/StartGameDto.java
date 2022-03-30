package fr.lernejo.navy_battle.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartGameDto {
    private final String id;
    private final String url;
    private final String message;

    public StartGameDto(@JsonProperty("id") String id, @JsonProperty("url")String url, @JsonProperty("message") String message) {
        this.id = id;
        this.url = url;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }

}
