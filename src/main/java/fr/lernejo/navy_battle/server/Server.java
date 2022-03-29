package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.client.Client;
import fr.lernejo.navy_battle.server.controllers.FireController;
import fr.lernejo.navy_battle.server.controllers.PingController;
import fr.lernejo.navy_battle.server.controllers.StartGameController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Random;

public class Server {

    public Server(int port, String adversaryUrl) {
        HttpServer server;
        String serverId = String.valueOf(new Random().nextInt(100000));
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/ping", new PingController());
            server.createContext("/api/game/start", new StartGameController(serverId));
            server.createContext("/api/game/fire", new FireController());
            server.start();
            System.out.println("server launched on port : " + port);
        } catch (IOException e) { System.out.println("failed to create server"); }
        Client client = new Client();
        if (adversaryUrl != null) client.startGame(adversaryUrl, serverId, port);
    }
}
