package fr.lernejo.navy_battle.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.lernejo.navy_battle.dto.StartGameDto;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient client = HttpClient.newHttpClient();

    public void startGame(String url, String id, int port) {
        StartGameDto dto = new StartGameDto(id, "http://localhost:" + port, "start the game");
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/api/game/start"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(dto)))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }
}
