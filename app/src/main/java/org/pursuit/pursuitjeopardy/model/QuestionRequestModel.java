package org.pursuit.pursuitjeopardy.model;

import org.pursuit.pursuitjeopardy.enums.CategoryEnums;
import org.pursuit.pursuitjeopardy.enums.DifficultyEnums;

/**
 * request model used as a parameter in RetrofitSingleton.triviaApiCall method
 *
 * To use, simply instantiate an anonymous class inside method parameter with enums selections
 */

public class QuestionRequestModel {

    public final String categoryEnums;
    public final String difficultyEnums;

    public QuestionRequestModel(CategoryEnums categoryEnums, DifficultyEnums difficultyEnums) {
        this.categoryEnums = categoryEnums.getLinkTranslation();
        this.difficultyEnums = difficultyEnums.getLinkTranslation();
    }
}
