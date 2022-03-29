package fr.lernejo.navy_battle.server.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class FireController extends BaseController implements HttpHandler {


    @Override
    public void handle(HttpExchange ex) throws IOException {
        if (ex.getRequestMethod().equalsIgnoreCase("GET")) {
            String cell = ex.getRequestURI().getQuery();

        } else {
            ex.sendResponseHeaders(404, 0);
        }
    }
}
