package com.mygdx.game.HealthyGame.GameLogic;

import com.mygdx.game.Engine.Scenes.SceneManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HealthyGameLogic {
    private List<String> words = Arrays.asList("hello");
    private String currentWord;
    private int score = 0;
    private StringBuilder collectedLetters = new StringBuilder();
    private static HealthyGameLogic instance;



    // Private constructor to prevent instantiation
    private HealthyGameLogic() {
        selectNewWord();
    }

    // Public method to get the instance
    public static synchronized HealthyGameLogic getInstance() {
        if (instance == null) {
            instance = new HealthyGameLogic();
        }
        return instance;
    }


    // Selects a new word randomly from the list
    public void selectNewWord() {
        Random random = new Random();
        currentWord = words.get(random.nextInt(words.size()));
        collectedLetters.setLength(0); // Reset collected letters for the new word
    }

    // Call this method when a letter is collected in the game
    public void collectLetter(char letter) {
        if (currentWord.contains(String.valueOf(letter))) {
            collectedLetters.append(letter);
            updateScore();
            checkWordCompletion();
        }
    }

    private void updateScore() {
        score += 10;
    }

    private void checkWordCompletion() {
        // Convert collectedLetters StringBuilder to a sorted char array
        char[] collectedLettersSorted = collectedLetters.toString().toCharArray();
        Arrays.sort(collectedLettersSorted);

        // Convert currentWord to a sorted char array
        char[] currentWordSorted = currentWord.toCharArray();
        Arrays.sort(currentWordSorted);

        // Check if sorted arrays are equal, indicating all letters have been collected
        if (Arrays.equals(collectedLettersSorted, currentWordSorted)) {
            System.out.println("Word completed: " + currentWord);
            selectNewWord(); // Select a new word for the next round
        }
    }

    // Getter for the current word (to display in the game)
    public String getCurrentWord() {
        return currentWord;
    }

    // Getter for the current score
    public int getScore() {
        return score;
    }

    public void setScore(int i) {
        this.score = i;
    }

}
