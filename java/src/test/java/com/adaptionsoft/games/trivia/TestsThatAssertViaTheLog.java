package com.adaptionsoft.games.trivia;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

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

}
