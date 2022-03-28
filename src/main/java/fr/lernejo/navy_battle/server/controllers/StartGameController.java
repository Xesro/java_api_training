package fr.lernejo.navy_battle.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.primitives.Bytes;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.server.controllers.dto.StartGameDto;
import org.json.JSONObject;
import utils.SchemaValidator;

import java.io.OutputStream;
import java.net.InetAddress;
import java.util.UUID;

import java.io.IOException;

public class StartGameController implements HttpHandler {

    private final SchemaValidator validator = new SchemaValidator();
    private final String VALIDATION_SCHEMA_PATH = "src/main/resources/start_game_schema.json";

    @Override
    public void handle(HttpExchange ex) throws IOException {
        if (ex.getRequestMethod().equalsIgnoreCase("POST")) {
            try {
                validator.validate(ex.getRequestBody(), VALIDATION_SCHEMA_PATH);
            } catch (Exception e) {
                ex.sendResponseHeaders(400, 0);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            StartGameDto dto = new StartGameDto(
                UUID.randomUUID().toString(),
                "http://" + ex.getLocalAddress().getHostName() + ":" + String.valueOf(ex.getLocalAddress().getPort()),
                "May the best code win"
            );
            byte json[] = objectMapper.writeValueAsBytes(dto);
            ex.sendResponseHeaders(202, json.length);
            OutputStream os = ex.getResponseBody();
            os.write(json);
            os.close();
        }
        else {
            System.out.println("titi");

            ex.sendResponseHeaders(404, 0);
        }
//        String response = "Ok";
//        exchange.sendResponseHeaders(200, response.length());
//        c;
//        os.write(response.getBytes());
//        os.close();
    }
}
