package org.pursuit.pursuitjeopardy.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public enum CategoryEnums {

    GENERAL_KNOWLEDGE("9"),
    ENTERTAINMENT_BOOKS("10"),
    ENTERTAINMENT_FILM("11"),
    ENTERTAINMENT_MUSIC("12"),
    ENTERTAINMENT_MUSICALS_AND_THEATERS("13"),
    ENTERTAINMENT_TELEVISON("14"),
    ENTERTAINMENT_VIDEO_GAMES("15"),
    ENTERTAINMENT_BOARD_GAMES("16"),
    SCIENCE_AND_NATURE("17"),
    SCIENCE_COMPUTERS("18"),
    SCIENCE_MATHEMATICS("19"),
    MYTHOLOGY("20"),
    SPORTS("21"),
    GEOGRAPHY("22"),
    HISTORY("23"),
    POLITICS("24"),
    ART("25"),
    CELEBRITIES("26"),
    ANIMALS("27"),
    VEHICLES("28"),
    ENTERTAINMENT_COMICS("29"),
    SCIENCE_GADGETS("30"),
    ENTERTAINMENT_JAPANESE_ANIME_AND_MANGA("31"),
    ENTERTAINMENT_CARTOON_AND_ANIMATIONS("32");

    private String linkTranslation;

    public String getLinkTranslation() {
        return linkTranslation;
    }

    public static CategoryEnums randomCategory() {
        final List<CategoryEnums> categoryEnumsList =
                Collections.unmodifiableList(Arrays.asList(values()));
        final Random randomNumber = new Random();
        final int size = categoryEnumsList.size();
        int selected = randomNumber.nextInt(size);
        return categoryEnumsList.get(selected);
    }

    CategoryEnums(String linkTranslation) {
        this.linkTranslation = linkTranslation;
    }
}
