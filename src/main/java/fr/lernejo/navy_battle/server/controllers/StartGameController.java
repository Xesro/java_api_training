package fr.lernejo.navy_battle.server.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.dto.StartGameDto;
import fr.lernejo.navy_battle.game.Game;
import org.json.JSONObject;
import utils.SchemaValidator;

import java.io.*;

public class StartGameController extends BaseController implements HttpHandler {

    private final SchemaValidator validator = new SchemaValidator();
    private final String VALIDATION_SCHEMA_PATH = "src/main/resources/start_game_schema.json";
    private final String serverId;
    private final Game game;

    public StartGameController(String serverId, Game game) {
        this.serverId = serverId;
        this.game = game;
    }

    @Override
    public void handle(HttpExchange ex) throws IOException {
        if (ex.getRequestMethod().equalsIgnoreCase("POST")) {
            JSONObject jsonRequest = getResponseBody(ex);
            StartGameDto startGameDto = objectMapper.readValue(jsonRequest.toString(), StartGameDto.class);
            System.out.println(jsonRequest);
            try {
                validator.validate(jsonRequest, VALIDATION_SCHEMA_PATH);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                ex.sendResponseHeaders(400, 0); }
            StartGameDto dto = new StartGameDto(String.valueOf(serverId), "http://" + ex.getLocalAddress().getHostName() + ":" + ex.getLocalAddress().getPort(), "May the best code win");
            jsonOk(dto, ex);
            game.opponent.setAddress(startGameDto.getUrl());
            game.fire("D2");
        }
        else ex.sendResponseHeaders(404, 0);
    }
}
