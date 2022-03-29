package fr.lernejo.navy_battle.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import fr.lernejo.navy_battle.dto.StartGameDto;
import org.json.JSONObject;

import java.io.OutputStream;

public class BaseController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JSONObject getResponseBody(HttpExchange ex)
    {
        try {
            return new JSONObject(new String(ex.getRequestBody().readAllBytes()));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void jsonOk(Object dto, HttpExchange ex)
    {
        try {
            byte[] json = objectMapper.writeValueAsBytes(dto);
            ex.sendResponseHeaders(202, json.length);
            OutputStream os = ex.getResponseBody();
            os.write(json);
            os.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
