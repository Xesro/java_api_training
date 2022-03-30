package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.client.Client;

public class Game {

    private final boolean[][] ownBoard = new boolean[10][10];
    private final boolean[][] adversaryBoard = new boolean[10][10];
    public final Opponent opponent;

    private final Client player;

    public Game(Opponent opponent) {
        this.opponent = opponent;
        player = new Client(opponent);
        ownBoard[0][0] = ownBoard[0][1] = ownBoard[0][2] = ownBoard[0][3] = ownBoard[0][4] = true;// porte avion
        ownBoard[2][3] = ownBoard[3][3] = ownBoard[4][3] = ownBoard[5][3] = true;// croiseur
        ownBoard[5][6] = ownBoard[5][7] = ownBoard[5][8] = true;// contre-torpilleur
        ownBoard[9][9] = ownBoard[9][8] = ownBoard[9][7] = true;// contre-torpilleur
        ownBoard[8][2] = ownBoard[8][3] = true;//torpilleur
    }

    public void fire(String cell)
    {
        player.fire(cell);
    }

    public void checkHit(String cell) { }

    public void start(String adversaryUrl, String serverId, int ownPort)
    {
        opponent.setAddress(adversaryUrl);
        player.startGame(serverId, ownPort);
    }
}
