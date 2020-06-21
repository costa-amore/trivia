package com.adaptionsoft.games.nicetrivia.player;

public class PlayerExporter {

    public static PlayerInformation export(Player player) {
        final PlayerInformation playerInformation = new PlayerInformation();
        playerInformation.playerName = player.playerName;
        playerInformation.playerPosition = String.valueOf(player.position);
        playerInformation.playerGold = String.valueOf(player.gold);

        if (player.getClass()==ReadyPlayer.class) playerInformation.status = "ready";

        return playerInformation;
    }

}
