package com.adaptionsoft.games.trivia.game_setup.golden_master;

import com.adaptionsoft.games.trivia.runner.GameRunner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class GoldenMaster {

    public static final String[] ARGUMENTS_WITH_RANDOMIZER_SEED = {"0"};

    @Test
    void WHEN_running_the_the_same_game_THEN_the_log_should_be_the_same() throws IOException {
        File log = RunTestAndCaptureLogIn(addPathTo("RunLog.txt"));
        File master = new File(addPathTo("Golden_master.txt"));

        assertThat(log).hasSameTextualContentAs(master);
    }

//    only run this 'test' to generate the golden master")
//    @Test
    @SuppressWarnings("unused")
    void Generate_golden_master() throws IOException {
        RunTestAndCaptureLogIn("Golden_master.txt");
    }

    private String addPathTo(String fileName) {
        // some mysterious java magic required which I don't really understand, but hey, it works :D
        // I just want to operate the golden master files in the same folder as the goldenmaster code...
        int LAST_PART_OF_THE_PATH_OF_THE_CLASS_WHICH_I_DONT_GET = 20;
        // current implementation will break when the namespace (package name) changes !
        String RELATIVE_PATH_OF_THE_FOLDER_STRUCTURE_WHERE_THIS_CLASS_IS_STORED = "src/test/java/com/adaptionsoft/games/trivia/game_setup/golden_master/";

        String pathOfThisJavaClass = GoldenMaster.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String relativePath = RELATIVE_PATH_OF_THE_FOLDER_STRUCTURE_WHERE_THIS_CLASS_IS_STORED + fileName;
        return pathOfThisJavaClass.substring(0, pathOfThisJavaClass.length()- LAST_PART_OF_THE_PATH_OF_THE_CLASS_WHICH_I_DONT_GET).concat(relativePath);
    }

    private File RunTestAndCaptureLogIn(String logFileNameAndPath) throws IOException {
        final File logFile = new File(logFileNameAndPath);
        if (!logFile.createNewFile()) return logFile;

        System.setOut(new PrintStream(logFile));

        GameRunner.main(ARGUMENTS_WITH_RANDOMIZER_SEED);

        System.setOut(System.out);

        return logFile;
    }


}
