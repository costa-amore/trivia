package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	public static final int MIN_PLAYERS = 2;
	public static final int MAX_PLAYERS = 5;
	public static final int MAX_NR_OF_QUESTIONS = 50;
	ArrayList players = new ArrayList();
    int[] places = new int[MAX_PLAYERS+1];
    int[] purses  = new int[MAX_PLAYERS+1];
    boolean[] inPenaltyBox  = new boolean[MAX_PLAYERS+1];
    
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
		GenerateQuestions(MAX_NR_OF_QUESTIONS);
	}

	public boolean isPlayable() {
		return (MIN_PLAYERS <= howManyPlayers());
	}

	public boolean add(String playerName) {
		if (howManyPlayers()==MAX_PLAYERS) return false;

	    players.add(playerName);
		InitializeLastAddedPlayer();

		Announce.playerAddedSuccessfully(playerName, players.size());
		return true;
	}

	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int rollResult) {
		Announce.currentPlayerRolled(rollResult, getCurrentPlayerName());

		if (inPenaltyBox[currentPlayer]) {
			if (oneChanceInTwo(rollResult)) {

				isGettingOutOfPenaltyBox = true;
				Announce.currentPlayerIsGettingOutOfPenaltyBox(getCurrentPlayerName());
				CurrentPlayerTakesTurnWith(rollResult);

			} else {

				Announce.currentPlayerIsStayingInThePenaltyBox(getCurrentPlayerName());
				isGettingOutOfPenaltyBox = false;

			}

		} else {

			CurrentPlayerTakesTurnWith(rollResult);

		}
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){

			if (isGettingOutOfPenaltyBox) {
				Announce.currentPlayerAnsweredCorrectly();
				return winGoldAndEndTurn();

			} else {
				passToNextPlayer();
				return true;
			}

		} else {
			Announce.currentPlayerAnsweredCorrectly_withTypo();
			return winGoldAndEndTurn();
		}
	}

	public boolean wrongAnswer(){
		Announce.wrongAnswerSentCurrentPlayerToPenaltyBox(getCurrentPlayerName());

		inPenaltyBox[currentPlayer] = true;

		passToNextPlayer();
		return true;
	}



	//  Private parts - this is a smell !

	private boolean didPlayerWin() {
		return (purses[currentPlayer] == 6);
	}

	private void GenerateQuestions(int nrOfQuestions) {
		for (int i = 0; i < nrOfQuestions; i++) {
			popQuestions.addLast(createQuestion(i, "Pop"));
			scienceQuestions.addLast(createQuestion(i, "Science"));
			sportsQuestions.addLast(createQuestion(i, "Sports"));
			rockQuestions.addLast(createQuestion(i,"Rock"));
		}
	}

	private String createQuestion(int i, String question) {
		return question + " Question " + i;
	}

	private String getCurrentPlayerName() {
		return (String)players.get(currentPlayer);
	}

	private void InitializeLastAddedPlayer() {
		places[howManyPlayers()] = 0;
		purses[howManyPlayers()] = 0;
		inPenaltyBox[howManyPlayers()] = false;
	}

	private void CurrentPlayerTakesTurnWith(int rollResult) {
		moveCurrentPlayerBy(rollResult);
		Announce.currentPlayersNewPosition(getCurrentPlayerName(), places[currentPlayer], currentCategory());
		askQuestion();
	}

	private void moveCurrentPlayerBy(int rollResult) {
		places[currentPlayer] = places[currentPlayer] + rollResult;
		if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
	}

	private boolean oneChanceInTwo(int rollResult) {
		return rollResult % 2 != 0;
	}

	private void askQuestion() {
		if (currentCategory().equals("Pop")) 		Announce.question((String) popQuestions.removeFirst());
		if (currentCategory().equals("Science")) 	Announce.question((String) scienceQuestions.removeFirst());
		if (currentCategory().equals("Sports")) 	Announce.question((String) sportsQuestions.removeFirst());
		if (currentCategory().equals("Rock")) 		Announce.question((String) rockQuestions.removeFirst());
	}

	private String currentCategory() {
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 10) return "Sports";
		return "Rock";
	}

	private boolean winGoldAndEndTurn() {
		purses[currentPlayer]++;
		Announce.currentPlayersNewPurseStatus(getCurrentPlayerName(), purses[currentPlayer]);
		boolean currentPlayerWon = didPlayerWin();
		passToNextPlayer();

		return !currentPlayerWon;
	}

	private void passToNextPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
	}


}
