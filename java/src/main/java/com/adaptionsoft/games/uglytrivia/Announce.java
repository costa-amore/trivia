package com.adaptionsoft.games.uglytrivia;

public class Announce {
    static void playerAddedSuccessfully(String playerName, int playerId) {
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + playerId);
    }

    static void wrongAnswerSentCurrentPlayerToPenaltyBox(String currentPlayerName) {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayerName + " was sent to the penalty box");
    }

    static void currentPlayersNewPosition(String currentPlayerName, int currentPlayerLocation, String currentQuestionCategory) {
        System.out.println(currentPlayerName
                + "'s new location is "
                + currentPlayerLocation);
        System.out.println("The category is " + currentQuestionCategory);
    }

    static void currentPlayerIsStayingInThePenaltyBox(String currentPlayerName) {
        System.out.println(currentPlayerName + " is not getting out of the penalty box");
    }

    static void currentPlayerIsGettingOutOfPenaltyBox(Object currentPlayerName) {
        System.out.println(currentPlayerName + " is getting out of the penalty box");
    }

    static void currentPlayerRolled(int roll, Object currentPlayerName) {
        System.out.println(currentPlayerName + " is the current player");
        System.out.println("They have rolled a " + roll);
    }

    static void currentPlayerAnsweredCorrectly_withTypo() {
        System.out.println("Answer was corrent!!!!");
    }

    static void currentPlayersNewPurseStatus(String currentPlayerName, int currentPlayerPurse) {
        System.out.println(currentPlayerName
                + " now has "
                + currentPlayerPurse
                + " Gold Coins.");
    }

    static void currentPlayerAnsweredCorrectly() {
        System.out.println("Answer was correct!!!!");
    }

    static void question(String question) {
        System.out.println(question);
    }
}