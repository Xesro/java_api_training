package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.server.controllers.PingController;
import fr.lernejo.navy_battle.server.controllers.StartGameController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    public Server(int port) {
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/ping", new PingController());
            server.createContext("/api/game/start", new StartGameController());
            server.start();
            System.out.println("server launched on port : " + port);
        } catch (IOException e) {
            System.out.println("failed to create server");
        }
    }
}
