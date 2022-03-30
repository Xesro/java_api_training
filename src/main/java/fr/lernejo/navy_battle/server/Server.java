package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.client.Client;
import fr.lernejo.navy_battle.game.Game;
import fr.lernejo.navy_battle.game.Opponent;
import fr.lernejo.navy_battle.server.controllers.FireController;
import fr.lernejo.navy_battle.server.controllers.PingController;
import fr.lernejo.navy_battle.server.controllers.StartGameController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class Server {

    public Server(int port, String adversaryUrl) {
        ExecutorService executor = new java.util.concurrent.ThreadPoolExecutor(
            1,
            1,
            60L,
            java.util.concurrent.TimeUnit.SECONDS,
            new java.util.concurrent.LinkedBlockingQueue<Runnable>());

        HttpServer server;
        String serverId = String.valueOf(new Random().nextInt(100000));
        Game game = new Game(new Opponent());
        try {
            CountDownLatch latch = new CountDownLatch(1);
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/ping", new PingController());
            server.createContext("/api/game/start", new StartGameController(serverId, game));
            server.createContext("/api/game/fire", new FireController(game));
            server.start();
            System.out.println("server launched on port : " + port);
            if (adversaryUrl != null) {
                game.opponent.setAddress(adversaryUrl);
                game.start(adversaryUrl, serverId, port);
            }
            latch.await();
        } catch (IOException | InterruptedException e) { System.out.println("failed to create server"); }
    }

}
