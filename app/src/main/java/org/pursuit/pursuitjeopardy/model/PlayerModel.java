package org.pursuit.pursuitjeopardy.model;

public class PlayerModel {

    private String name;
    private int currentScore;

    public PlayerModel(){}

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
