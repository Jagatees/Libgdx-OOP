package com.mygdx.game.LearningGame.GameLogic;

public class Timer {
    private static Timer instance;

    private long startTime;
    private long elapsedTime;
    private boolean isRunning;

    public Timer() {
        this.startTime = 0;
        this.elapsedTime = 0;
        this.isRunning = false;
    }

    public static Timer getInstance() {
        if (instance == null) {
            instance = new Timer();
        }
        return instance;
    }

    // Adds a specified amount of time to the timer.
    public void addTime(long timeToAdd) {
        if (isRunning) {
            elapsedTime = System.currentTimeMillis() - startTime;
            elapsedTime += timeToAdd;
            startTime = System.currentTimeMillis() - elapsedTime;
        } else {
            elapsedTime += timeToAdd;
        }
    }

    // Example method to add 10 seconds to the timer
    public void addToTimer() {
        long tenSecondsInMillis = 1 * 100;
        addTime(tenSecondsInMillis);
    }


    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis() - elapsedTime;
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            elapsedTime = System.currentTimeMillis() - startTime;
            isRunning = false;
        }
    }

    public void pause() {
        if (isRunning) {
            elapsedTime = System.currentTimeMillis() - startTime;
            isRunning = false;
        }
    }

    public void resume() {
        if (!isRunning) {
            startTime = System.currentTimeMillis() - elapsedTime;
            isRunning = true;
        }
    }

    public String getTime() {
        long totalTimeMillis;
        if (isRunning) {
            totalTimeMillis = System.currentTimeMillis() - startTime;
        } else {
            totalTimeMillis = elapsedTime;
        }

        long seconds = totalTimeMillis / 1000;
        long minutes = seconds / 60;
        seconds %= 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
}
