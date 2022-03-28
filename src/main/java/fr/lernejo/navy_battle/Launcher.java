package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.server.Server;

public class Launcher {
    public static void main(String[] args) {
        Server server = new Server(8001);
    }
}
