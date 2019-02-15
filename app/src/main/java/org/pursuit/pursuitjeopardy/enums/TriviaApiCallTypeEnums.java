package org.pursuit.pursuitjeopardy.enums;

public enum TriviaApiCallTypeEnums {
    ANY_TYPE(""),
    MULTIPLE("multiple"),
    TRUE_OR_FALSE("boolean");

    private String linkTranslation;

    public String getLinkTranslation() {
        return linkTranslation;
    }

    TriviaApiCallTypeEnums(String linkTranslation) {
        this.linkTranslation = linkTranslation;
    }
}

