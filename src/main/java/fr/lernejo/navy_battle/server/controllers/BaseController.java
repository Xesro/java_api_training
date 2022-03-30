package fr.lernejo.navy_battle.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import fr.lernejo.navy_battle.dto.StartGameDto;
import org.json.JSONObject;

import java.io.OutputStream;
import java.util.Arrays;

public class BaseController {

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected JSONObject getResponseBody(HttpExchange ex)
    {
        try {
            return new JSONObject(new String(ex.getRequestBody().readAllBytes()));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    protected void jsonOk(Object dto, HttpExchange ex)
    {
        try {
            byte[] json = objectMapper.writeValueAsBytes(dto);
            System.out.println(Arrays.toString(json));
            ex.sendResponseHeaders(202, json.length);
            OutputStream os = ex.getResponseBody();
            os.write(json);
            os.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
