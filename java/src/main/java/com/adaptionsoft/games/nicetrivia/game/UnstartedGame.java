package com.adaptionsoft.games.nicetrivia.game;

import com.adaptionsoft.games.nicetrivia.player.Player;

public class UnstartedGame extends Game{

    UnstartedGame(){
        super();
    }

    private UnstartedGame(UnstartedGame unstartedGame, Player player) {
        super();
        this.players.addAll(unstartedGame.players);
        this.players.add(player);
    }

    public UnstartedGame addPlayer(Player player){
        final UnstartedGame unstartedGame = new UnstartedGame(this, player);

        if (unstartedGame.players.size() >= 2) return new StartableGame(unstartedGame);

        return unstartedGame;
    }
}
