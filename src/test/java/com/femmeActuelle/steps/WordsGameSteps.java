package com.femmeActuelle.steps;

import com.femmeActuelle.pageObjects.WordsGamePage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class WordsGameSteps implements En {

    public WordsGameSteps(WordsGamePage wordsGamePage ) {
        Then("Note that the text color used for container texts is unreadable.",() -> {
            wordsGamePage.saveScreenShotPNG();
            Assert.assertTrue(wordsGamePage.textIsReadable());
        });
    }
}
