package com.mygdx.game.LearningGame.GameLogic;

import com.mygdx.game.Engine.Timer.Timer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Singleton class for managing the logic of a learning-themed game.
 * Controls the game's difficulty, word selection, and scoring.
 */
public class LearningGameLogic {
    public enum Difficulty {
        EASY(Arrays.asList("DOG", "CAT", "BALL", "BOOK", "HOUSE",
                "TREE", "SUN", "RED", "RUN", "HAPPY")),
        MEDIUM(Arrays.asList("ADVENTURE", "COURAGE", "MYSTERY", "HARMONY", "CHALLENGE",
                "INNOVATION", "SERENITY", "PERSISTENCE", "TRANQUILITY", "RESILIENCE")),
        HARD(Arrays.asList("EXTRAPOLATE", "DISCOMBOBULATE", "PERSPICACIOUS", "OBFUSCATE", "ANTEDILUVIAN",
                "PERNICIOUS", "MELLIFLUOUS", "DISQUISITION", "CIRCUMLOCUTION", "EXACERBATE"));

        private final List<String> words;
        Difficulty(List<String> words) {
            this.words = words;
        }
        public List<String> getWords() {
            return words;
        }
    }

    private static final char DEFAULT_CHAR = '!'; // Default character when current word is invalid
    private static volatile LearningGameLogic instance;
    private Difficulty currentDifficulty;
    private StringBuilder collectedLetters = new StringBuilder();
    private String currentWord;
    private int score = 0;
    private int goal = 0;


    private LearningGameLogic() {
        Timer.getInstance().start();
        this.currentDifficulty = Difficulty.EASY;
        selectNewWord();
        setScoreGoal(currentWord.length());
    }

    public static LearningGameLogic getInstance() {
        if (instance == null) {
            synchronized (LearningGameLogic.class) {
                if (instance == null) {
                    instance = new LearningGameLogic();
                }
            }
        }
        return instance;
    }

    /** Selects a new word, according to the difficulty (i.e. when it's easy,
     * select from one of the random words in the easy list **/
    public void selectNewWord() {
        Random random = new Random();
        List<String> words = currentDifficulty.getWords();
        currentWord = words.get(random.nextInt(words.size()));
        collectedLetters.setLength(0); // Reset collected letters
    }

    /** Gets the current selected word length - to be used for checking if user collected all the letters length required **/
    public int getCurrentWordLength() {
        if (currentWord != null) {
            return currentWord.length();
        } else {
            return 0; // Return 0 if there's no current word selected
        }
    }

    /** Method to increment the score, to be called when user collides into the correct letter **/
    private void updateScore() {
        score += 1;
    }


    /** Returns the current letter for the selected word **/
    public char getFirstLetterOfCurrentWordSafely(int index) {
        if (currentWord != null && !currentWord.isEmpty() && index < currentWord.length()) {
            return currentWord.charAt(index);
        } else {
            return DEFAULT_CHAR;
        }
    }

    /** Getter and Setters for game difficulty **/
    // Standard getters and setters follow here
    public Difficulty getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void setCurrentDifficulty(Difficulty difficulty) {
        this.currentDifficulty = difficulty;
    }

    /** Getter for the current word, returns selected word **/
    public String getCurrentWord() {
        return currentWord;
    }

    /** Getter & Setter for score **/
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int increment) {
        setScore(getScore() + increment);
    }

    /** Getter & Setter for the score to be met (goal) **/

    public int getScoreGoal() {
        return goal;
    }

    public void setScoreGoal(int goal) {
        this.goal = goal;
    }



    /** Method to reset the score, to be used across different difficulty for checking if word completion met **/
    public void restartScore() {
        setScore(0);
    }
}
