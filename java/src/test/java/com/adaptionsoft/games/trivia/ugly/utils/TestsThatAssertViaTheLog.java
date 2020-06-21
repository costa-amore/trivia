package com.adaptionsoft.games.trivia.ugly.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestsThatAssertViaTheLog {
    protected SystemOutInterceptor logInterceptor = new SystemOutInterceptor();

    @BeforeEach
    void ArrangeAndAct(){
        arrange();
        logInterceptor.startIntercepting();
        act();
    }

    protected void arrange() { /* to be implemented if needed by the actual test classes */}
    protected void act() {/* to be implemented if needed by the actual test classes */ }

    @AfterEach
    void StopInterceptingTheLog(){
        logInterceptor.returnToNormal();
    }

}
