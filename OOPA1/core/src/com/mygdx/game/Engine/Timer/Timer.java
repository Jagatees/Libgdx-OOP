package com.mygdx.game.Engine.Timer;

public class Timer {
    private static Timer instance;

    private long startTime;
    private long elapsedTime;
    private boolean isRunning;
    private boolean isPaused;

    public Timer() {
        this.startTime = 0;
        this.elapsedTime = 0;
        this.isRunning = false;
        this.isPaused = false;
    }

    public static Timer getInstance() {
        if (instance == null) {
            instance = new Timer();
        }
        return instance;
    }

    // Adds a specified amount of time to the timer.
    public void addTime(long timeToAdd) {
        if (isRunning && !isPaused) {
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
            isPaused = false;
        }
    }

    public void stop() {
        if (isRunning) {
            elapsedTime = System.currentTimeMillis() - startTime;
            isRunning = false;
            isPaused = false;
        }
    }

    public void pause() {
        if (isRunning && !isPaused) {
            elapsedTime = System.currentTimeMillis() - startTime;
            isPaused = true;
        }
    }

    public void resume() {
        if (isRunning && isPaused) {
            startTime = System.currentTimeMillis() - elapsedTime;
            isPaused = false;
        }
    }

    public String getTime() {
        long totalTimeMillis;
        if (isRunning && !isPaused) {
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
