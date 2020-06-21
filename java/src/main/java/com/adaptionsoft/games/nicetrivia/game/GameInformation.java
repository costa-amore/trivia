package com.adaptionsoft.games.nicetrivia.game;

import com.adaptionsoft.games.nicetrivia.player.PlayerInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameInformation {
    List<PlayerInformation> players = new ArrayList<>();

    public PlayerInformation findPlayer(String playerName) {
        return players.stream()
                .filter(p-> Objects.equals(p.playerName, playerName))
                .findFirst()
                .orElse(new PlayerInformation());
    }
}

