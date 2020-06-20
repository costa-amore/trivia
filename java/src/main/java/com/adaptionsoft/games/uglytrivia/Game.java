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

		announcePlayerAddedSuccessfully(playerName);
		return true;
	}

	public int howManyPlayers() {
		return players.size();
	}

	public void currentPlayerRolled(int rollResult) {
		announceCurrentPlayerRolled(rollResult);

		if (inPenaltyBox[currentPlayer]) {
			if (oneChanceInTwo(rollResult)) {

				isGettingOutOfPenaltyBox = true;
				announceCurrentPlayerIsGettingOutOfPenaltyBox();
				CurrentPlayerTakesTurnWith(rollResult);

			} else {

				announceCurrentPlayerIsStayingInThePenaltyBox();
				isGettingOutOfPenaltyBox = false;

			}

		} else {

			CurrentPlayerTakesTurnWith(rollResult);

		}
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){

			if (isGettingOutOfPenaltyBox) {
				announceCurrentPlayerAnsweredCorrectly();
				return endOfTurn();

			} else {
				passToNextPlayer();
				return true;
			}

		} else {
			announceCurrentPlayerAnsweredCorrectly_withTypo();
			return endOfTurn();
		}
	}

	public boolean wrongAnswer(){
		announceWrongAnswerSentCurrentPlayerToPenaltyBox();

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

	private String createQuestion(int i, String s) {
		return s + " Question " + i;
	}


	private void announcePlayerAddedSuccessfully(String playerName) {
		System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
	}

	private void announceWrongAnswerSentCurrentPlayerToPenaltyBox() {
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
	}
	private void InitializeLastAddedPlayer() {
		places[howManyPlayers()] = 0;
		purses[howManyPlayers()] = 0;
		inPenaltyBox[howManyPlayers()] = false;
	}


	private void CurrentPlayerTakesTurnWith(int rollResult) {
		moveCurrentPlayerBy(rollResult);
		announceCurrentPlayersNewPosition();
		askQuestion();
	}

	private void moveCurrentPlayerBy(int rollResult) {
		places[currentPlayer] = places[currentPlayer] + rollResult;
		if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
	}

	private void announceCurrentPlayersNewPosition() {
		System.out.println(players.get(currentPlayer)
				+ "'s new location is "
				+ places[currentPlayer]);
		System.out.println("The category is " + currentCategory());
	}

	private void announceCurrentPlayerIsStayingInThePenaltyBox() {
		System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
	}

	private void announceCurrentPlayerIsGettingOutOfPenaltyBox() {
		System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
	}

	private boolean oneChanceInTwo(int rollResult) {
		return rollResult % 2 != 0;
	}

	private void announceCurrentPlayerRolled(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
	}


	private void askQuestion() {
		if (currentCategory() == "Pop")
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(rockQuestions.removeFirst());
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


	private void announceCurrentPlayerAnsweredCorrectly_withTypo() {
		System.out.println("Answer was corrent!!!!");
	}

	private boolean endOfTurn() {
		purses[currentPlayer]++;
		announceCurrentPlayersNewPurseStatus();
		boolean currentPlayerWon = didPlayerWin();
		passToNextPlayer();

		return !currentPlayerWon;
	}

	private void passToNextPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
	}

	private void announceCurrentPlayersNewPurseStatus() {
		System.out.println(players.get(currentPlayer)
				+ " now has "
				+ purses[currentPlayer]
				+ " Gold Coins.");
	}

	private void announceCurrentPlayerAnsweredCorrectly() {
		System.out.println("Answer was correct!!!!");
	}


}
