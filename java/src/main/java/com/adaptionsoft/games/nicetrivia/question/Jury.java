package com.adaptionsoft.games.nicetrivia.question;

import java.util.ArrayList;
import java.util.List;

public class Jury {
    private final List<Question> questions;

    Jury(List<Question> questions) {
        this.questions = questions;
    }

    public static Jury createAround(List<Question> questions) {
        return new Jury(questions);
    }

    public static Jury createNew() {
        return new Jury(new ArrayList<>());
    }

    public boolean hasEnoughQuestionsToStart() {
        return this.questions.size() >= 2;
    }
}
