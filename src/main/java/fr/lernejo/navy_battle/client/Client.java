package fr.lernejo.navy_battle.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.lernejo.navy_battle.dto.StartGameDto;
import fr.lernejo.navy_battle.game.Opponent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient client = HttpClient.newHttpClient();

    private final Opponent opponent;

    public Client(Opponent opponent) {
        this.opponent = opponent;
    }

    public HttpResponse<String> startGame(String id, int port) {
        StartGameDto dto = new StartGameDto(id, "http://localhost:" + port, "start the game");
        return sendPostRequest(dto, opponent.getAddress() + "/api/game/start");
    }

    public HttpResponse<String> fire(String cell)
    {
        HttpResponse<String> response = sendGetRequest(opponent.getAddress() + "/api/game/fire", "?cell=" + cell);
        System.out.println(response);
        return response;
    }

    private HttpResponse<String> sendPostRequest(Object dto, String url)
    {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(dto)))
                .build();

            return  client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private HttpResponse<String> sendGetRequest(String url, String parameters)
    {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + parameters))
                .setHeader("Accept", "application/json")
                .GET()
                .build();

            return  client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) { System.out.println(e.getMessage());}
        return null;
    }
}
