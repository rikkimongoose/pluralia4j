package com.github.pluralia4j.dictionary;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

/**
 * Special methods for wordforms.
 */
public class WordformsUtils {
    /***
     * Mapping the allowed WordCase to a method that transforms a word according to it
     */
    private static final Map<WordCase, UnaryOperator<String>> WORD_CASE_TO_CONVERT =
            Maps.immutableEnumMap(
            ImmutableMap.<WordCase, UnaryOperator<String>>builder()
                    .put(WordCase.UPPER, StringUtils::upperCase)
                    .put(WordCase.LOWER, StringUtils::lowerCase)
                    .put(WordCase.UPPER_FIRST, StringUtils::capitalize)
                    .build());
    /**
     * Returns WordCase for the word.
     *
     * @param word word to get it's WordCase
     * @return WordCase of current word
     */
    public static WordCase wordCaseByWord(@NotNull String word) {
        if(StringUtils.isMixedCase(word)) {
            return WordCase.UPPER_FIRST;
        }
        if(StringUtils.isAllUpperCase(word)) {
            return WordCase.UPPER;
        }

        return WordCase.LOWER;
    }

    /**
     * Transforms word by specified WordCase. The original word's case doesn't matter
     * @param word word for transformation
     * @param wordCase word case
     * @return the same word in a defined case
     */
    public static String wordToWordCase(@NotNull String word, @NotNull WordCase wordCase) {
        if(StringUtils.isEmpty(word) || !WORD_CASE_TO_CONVERT.containsKey(wordCase)){
            return word;
        }
        return WORD_CASE_TO_CONVERT.get(wordCase).apply(word);
    }

    /**
     * Get an item from list by index. If index is
     * @param list source list
     * @param index index in list
     * @return item by index, if index is less then the size of the list, the last element of list
     * if index is larder then size, null if list is empty
     */
    public static String getOrLast(List<String> list, int index) {
        return list != null && !list.isEmpty() ? (list.size() > index ? list.get(index) : list.get(list.size() - 1)) : null;
    }
}
