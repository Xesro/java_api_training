package fr.lernejo.navy_battle.dto;

public class FireResponseDto {

    public final String consequence;
    public final boolean shipLeft;

    public FireResponseDto(String consequence, boolean shipLeft) {
        this.consequence = consequence;
        this.shipLeft = shipLeft;
    }
}
