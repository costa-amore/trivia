package com.adaptionsoft.games.trivia.GameSetup;

import com.adaptionsoft.games.trivia.runner.GameRunner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class GoldenMaster {

    public static final String[] ARGUMENTS_WITH_RANDOMIZER_SEED = {"0"};

    @Test
    void WHEN_running_the_the_same_game_THEN_the_log_should_be_the_same() throws IOException {
        File log = RunTestAndCaptureLogIn("RunLog.txt");
        File master = new File("Golden_master.txt");

        Assertions.assertThat(log).hasSameTextualContentAs(master);
    }

    @Test
    @Disabled("only run to generate the golden master")
    void Generate_golden_master() throws IOException {
        RunTestAndCaptureLogIn("Golden_master.txt");
    }

    private File RunTestAndCaptureLogIn(String logFile) throws IOException {
        final File goldenMaster = new File(logFile);
        goldenMaster.createNewFile();

        System.setOut(new PrintStream(goldenMaster));

        GameRunner.main(ARGUMENTS_WITH_RANDOMIZER_SEED);

        System.setOut(System.out);

        return goldenMaster;
    }


}
