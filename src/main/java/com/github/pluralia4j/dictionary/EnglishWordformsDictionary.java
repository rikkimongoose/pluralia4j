package com.github.pluralia4j.dictionary;

import com.google.common.collect.ImmutableMap;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * A ready dictionary covering most of plural forms in English.
 */
public final class EnglishWordformsDictionary extends WordformsDictionary {
    /**
     * Plural index for English
     */
    public static final int INDEX_MANY = 1;

    /**
     * Rules for standard English plural nouns
     */
    private final static Map<Pattern, String> regExRules = ImmutableMap.<Pattern, String>builder()
            .put(Pattern.compile("(.*)(ch|s|x)$", Pattern.CASE_INSENSITIVE), "$1$2es")
            .put(Pattern.compile("(.*)y$", Pattern.CASE_INSENSITIVE), "$1ies")
            .put(Pattern.compile("(.*)(fe?)$", Pattern.CASE_INSENSITIVE), "$1ves")
            .put(Pattern.compile("(.*)o$", Pattern.CASE_INSENSITIVE), "$1oes")
            .build();

    /**
     * {@inheritDoc}
     */
    @Override
    public String pluralByRule(@NotNull String key, int index) {
        if(index == 0) {
            return key;
        }
        final Pattern foundPattern = regExRules.keySet().stream().filter(pattern -> pattern.matcher(key).matches()).findFirst()
                .orElse(null);
        if(Objects.isNull(foundPattern)) {
            return String.format("%ss", key);
        }
        return foundPattern.matcher(key).replaceAll(regExRules.get(foundPattern));
    }
}
