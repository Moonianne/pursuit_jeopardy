package org.pursuit.pursuitjeopardy.enums;

public enum TriviaApiCallDifficultyEnums {
    ANY_DIFFICULTY(""),
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private String linkTranslation;

    public String getLinkTranslation() {
        return linkTranslation;
    }

    TriviaApiCallDifficultyEnums(String linkTranslation) {
        this.linkTranslation = linkTranslation;
    }
}
