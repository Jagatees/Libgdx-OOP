package com.mygdx.game.HealthyGame.GameLogic;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Singleton class for managing the logic of a healthy-themed game.
 * Controls the game's difficulty, word selection, and scoring.
 */
public class HealthyGameLogic {
    public enum Difficulty {
        EASY(Arrays.asList("EASYONE", "EASYTWO", "EASYTHREE")),
        MEDIUM(Arrays.asList("MEDIUMONE", "MEDIUMTWO", "MEDIUMTHREE")),
        HARD(Arrays.asList("HARDONE", "HARDTWO", "HARDTHREE"));

        private final List<String> words;

        Difficulty(List<String> words) {
            this.words = words;
        }

        public List<String> getWords() {
            return words;
        }
    }

    private static final char DEFAULT_CHAR = '!'; // Default character when current word is invalid
    private static volatile HealthyGameLogic instance;
    private Difficulty currentDifficulty;
    private StringBuilder collectedLetters = new StringBuilder();
    private String currentWord;
    private int score = 0;
    private int goal = 0;

    private HealthyGameLogic() {
        this.currentDifficulty = Difficulty.EASY;
        selectNewWord();
        setScoreGoal(currentWord.length());
    }

    public static HealthyGameLogic getInstance() {
        if (instance == null) {
            synchronized (HealthyGameLogic.class) {
                if (instance == null) {
                    instance = new HealthyGameLogic();
                }
            }
        }
        return instance;
    }

    public void selectNewWord() {
        Random random = new Random();
        List<String> words = currentDifficulty.getWords();
        currentWord = words.get(random.nextInt(words.size()));
        collectedLetters.setLength(0); // Reset collected letters
    }

    public void collectLetter(char letter) {
        if (currentWord.contains(String.valueOf(letter))) {
            collectedLetters.append(letter);
            updateScore();
            checkWordCompletion();
        }
    }

    public int getCurrentWordLength() {
        if (currentWord != null) {
            return currentWord.length();
        } else {
            return 0; // Return 0 if there's no current word selected
        }
    }

    private void updateScore() {
        score += 1;
    }

    private void checkWordCompletion() {
        char[] collectedLettersSorted = collectedLetters.toString().toCharArray();
        Arrays.sort(collectedLettersSorted);

        char[] currentWordSorted = currentWord.toCharArray();
        Arrays.sort(currentWordSorted);

        if (Arrays.equals(collectedLettersSorted, currentWordSorted)) {
            System.out.println("Word completed: " + currentWord);
//            selectNewWord(); // Select a new word
        }
    }

    public char getFirstLetterOfCurrentWordSafely(int index) {
        if (currentWord != null && !currentWord.isEmpty() && index < currentWord.length()) {
            return currentWord.charAt(index);
        } else {
            return DEFAULT_CHAR;
        }
    }

    // Standard getters and setters follow here
    public Difficulty getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void setCurrentDifficulty(Difficulty difficulty) {
        this.currentDifficulty = difficulty;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int increment) {
        setScore(getScore() + increment);
    }

    public void setScoreGoal(int goal) {
        this.goal = goal;
    }

    public int getScoreGoal() {
        return goal;
    }

    public void restartScore() {
        setScore(0);
    }
}
