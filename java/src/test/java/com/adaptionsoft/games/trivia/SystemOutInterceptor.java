package com.adaptionsoft.games.trivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SystemOutInterceptor{
    private PrintStream old;
    private ByteArrayOutputStream log;

    public void startIntercepting(){
        old = System.out;
        log = captureTheLogging();
    }

    public String readLog(){
        return log.toString();
    }

    public void returnToNormal(){
        System.out.flush();
        System.setOut(old);
    }


    private ByteArrayOutputStream captureTheLogging() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        return baos;
    }
}
