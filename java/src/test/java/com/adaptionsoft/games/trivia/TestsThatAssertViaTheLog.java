package com.adaptionsoft.games.trivia;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestsThatAssertViaTheLog {
    protected static SystemOutInterceptor logInterceptor = new SystemOutInterceptor();

    @BeforeEach
    void ArrangeAndAct(){
        arrange();
        logInterceptor.startIntercepting();
        act();
    }

    protected void arrange() { /* to be implemented if needed by the actual test classes */}
    protected void act() {/* to be implemented if needed by the actual test classes */ }

    @AfterAll
    static void releaseLogging(){
        logInterceptor.returnToNormal();
    }

    private ByteArrayOutputStream captureTheLogging() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        return baos;
    }
}
