package fr.lernejo.navy_battle.game;

public class Opponent {

    private final String[] address;

    public Opponent() {
        this.address = new String[2];
    }

    public void setAddress(String address)
    {
        this.address[0] = address;
    }

    public String getAddress() {
        return address[0];
    }
}
