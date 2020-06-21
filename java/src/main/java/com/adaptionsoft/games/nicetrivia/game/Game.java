package com.adaptionsoft.games.nicetrivia.game;

import com.adaptionsoft.games.nicetrivia.player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {

    List<Player> players;

    Game() {
        players = new ArrayList<>();
    }

    public static UnstartedGame createNewGame() {
        return new UnstartedGame();
    }

    public static StartedGame startGameFrom(StartableGame startableGame) {
        return new StartedGame(startableGame);
    }

}

