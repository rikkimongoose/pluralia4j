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
     *
     */
    public static final Map<WordCase, UnaryOperator<String>> WORD_CASE_TO_CONVERT =
            Maps.immutableEnumMap(
            ImmutableMap.<WordCase, UnaryOperator<String>>builder()
                    .put(WordCase.UPPER, StringUtils::upperCase)
                    .put(WordCase.LOWER, StringUtils::lowerCase)
                    .put(WordCase.UPPER_FIRST, StringUtils::capitalize)
                    .build());
    /**
     *
     * @param word
     * @return
     */
    public static WordCase caseFormatByWord(@NotNull String word) {
        if(StringUtils.isMixedCase(word)) {
            return WordCase.UPPER_FIRST;
        }
        if(StringUtils.isAllUpperCase(word)) {
            return WordCase.UPPER;
        }

        return WordCase.LOWER;
    }

    /**
     *
     * @param word
     * @param wordCase
     * @return
     */
    public static String caseFormatByWord(@NotNull String word, @NotNull WordCase wordCase) {
        if(StringUtils.isEmpty(word) || !WORD_CASE_TO_CONVERT.containsKey(wordCase)){
            return word;
        }
        return WORD_CASE_TO_CONVERT.get(wordCase).apply(word);
    }

    /**
     * Получить
     * @param list
     * @param index
     * @return
     */
    public static String getOrLast(List<String> list, int index) {
        return list != null && !list.isEmpty() ? (list.size() > index ? list.get(index) : list.get(list.size() - 1)) : null;
    }
}
