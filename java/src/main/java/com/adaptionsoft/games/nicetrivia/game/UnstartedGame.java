package com.adaptionsoft.games.nicetrivia.game;

import com.adaptionsoft.games.nicetrivia.player.Player;
import com.adaptionsoft.games.nicetrivia.question.Jury;

public class UnstartedGame extends Game{

    UnstartedGame(){
        super();
    }

    private UnstartedGame(UnstartedGame unstartedGame, Player playerToAdd) {
        super();
        this.players.addAll(unstartedGame.players);
        this.players.add(playerToAdd);
        this.jury = unstartedGame.jury;
    }

    public UnstartedGame(UnstartedGame unstartedGame, Jury juryToAdd) {
        super();
        this.players.addAll(unstartedGame.players);
        this.jury = juryToAdd;
    }

    public UnstartedGame add(Player player){
        final UnstartedGame unstartedGame = new UnstartedGame(this, player);

        return verified(unstartedGame);
    }

    public UnstartedGame add(Jury jury) {
        final UnstartedGame unstartedGame = new UnstartedGame(this, jury);

        return verified(unstartedGame);
    }

    private UnstartedGame verified(UnstartedGame unstartedGame) {
        if (allSetToStart(unstartedGame)) return new StartableGame(unstartedGame);

        return unstartedGame;
    }

    private boolean allSetToStart(UnstartedGame unstartedGame) {
        if (unstartedGame.players.size() < 2) return false;
        return unstartedGame.jury.hasEnoughQuestionsToStart();
    }
}
