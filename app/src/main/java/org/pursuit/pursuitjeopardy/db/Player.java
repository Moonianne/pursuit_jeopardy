package org.pursuit.pursuitjeopardy.db;

public class Player {
    private int score;
    private int name;

    public Player(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(int name) {
        this.name = name;
    }

}
