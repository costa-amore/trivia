package com.adaptionsoft.games.trivia.playing_a_game;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestsThatAssertViaTheLog {
    private static PrintStream old;

    @BeforeAll
    static void prepareToCaptureLogging(){
        old = System.out;
    }

    @AfterAll
    static void releaseLogging(){
        System.out.flush();
        System.setOut(old);
    }

    protected ByteArrayOutputStream captureTheLogging() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        return baos;
    }
}
