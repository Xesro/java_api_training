package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.server.Server;

public class Launcher {
    public static void main(String[] args) {
        String adversaryUrl = null;
        if (args.length == 2) {
            adversaryUrl = args[1];
        }

        Server server = new Server(Integer.parseInt(args[0]), adversaryUrl);
    }
}
