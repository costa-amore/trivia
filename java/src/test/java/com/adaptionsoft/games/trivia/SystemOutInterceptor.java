package com.adaptionsoft.games.trivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SystemOutInterceptor{
    private PrintStream old;
    private ByteArrayOutputStream log;
    private boolean intercepting = false;

    public void startIntercepting(){
        if (intercepting) return;

        old = System.out;
        System.out.println(" -> LogInterception started <-");
        System.out.flush();

        log = captureTheLogging();
        intercepting = true;
    }

    public String readLog(){
        if (!intercepting) return "";
        return log.toString();
    }

    public void returnToNormal(){
        if (!intercepting) return;

        System.out.println(" -> LogInterception stopped <-");
        System.setOut(old);
        System.out.println(readLog());

        intercepting = false;
    }


    private ByteArrayOutputStream captureTheLogging() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        return baos;
    }
}
