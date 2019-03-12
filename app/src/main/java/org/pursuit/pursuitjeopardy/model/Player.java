package org.pursuit.pursuitjeopardy.model;

public class Player {

    private String name;
    private int currentScore;

    public Player(){}

    public Player(String name, int currentScore) {
        this.name = name;
        this.currentScore = currentScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
