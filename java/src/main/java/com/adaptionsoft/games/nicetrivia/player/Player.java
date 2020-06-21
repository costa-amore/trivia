package com.adaptionsoft.games.nicetrivia.player;

public abstract class Player {

    protected final String playerName;
    protected final int gold;
    protected int position;

    public static Player create(String playerName) {
        return new ReadyPlayer(playerName);
    }

    protected Player(String playerName) {
        this.playerName = playerName;
        this.position = 0;
        this.gold = 0;
    }
}

