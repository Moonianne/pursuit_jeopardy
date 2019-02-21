package org.pursuit.pursuitjeopardy.enums;

public enum DifficultyEnums {

    ANY_DIFFICULTY(""),
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private String linkTranslation;

    public String getLinkTranslation() {
        return linkTranslation;
    }

    DifficultyEnums(String linkTranslation) {
        this.linkTranslation = linkTranslation;
    }
}
