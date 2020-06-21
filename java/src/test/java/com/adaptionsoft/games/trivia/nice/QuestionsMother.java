package com.adaptionsoft.games.trivia.nice;

import com.adaptionsoft.games.nicetrivia.question.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsMother {
    public QuestionsMother() {
    }

    public List<Question> create(int nrOfQuestions) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < nrOfQuestions; i++) {
            questions.add(Question.create("Q"));
        }
        return questions;
    }
}