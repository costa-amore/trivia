package com.adaptionsoft.games.nicetrivia.game;

import com.adaptionsoft.games.nicetrivia.player.Player;
import com.adaptionsoft.games.nicetrivia.question.Jury;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {

    protected Jury jury;
    List<Player> players;

    Game() {
        players = new ArrayList<>();
        jury = Jury.createNew();
    }

    public static UnstartedGame createNewGame() {
        return new UnstartedGame();
    }

    public static StartedGame startGameFrom(StartableGame startableGame) {
        return new StartedGame(startableGame);
    }

}

