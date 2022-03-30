package fr.lernejo.navy_battle.server.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.dto.FireResponseDto;
import fr.lernejo.navy_battle.game.Game;

import java.io.IOException;

public class FireController extends BaseController implements HttpHandler {

    private final Game game;

    public FireController(Game game) {
        this.game = game;
    }

    @Override
    public void handle(HttpExchange ex) throws IOException {
        if (ex.getRequestMethod().equalsIgnoreCase("GET")) {
            String cell = ex.getRequestURI().getQuery();
            game.checkHit(cell);
            FireResponseDto dto = new FireResponseDto("sunk", true);
            jsonOk(dto, ex);
            game.fire("C1");
        } else {
            ex.sendResponseHeaders(404, 0);
        }
    }
}
