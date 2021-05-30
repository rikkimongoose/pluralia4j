package com.github.pluralia4j.dictionary;

import com.google.common.collect.ImmutableMap;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class EnglishWordformsDictionary extends WordformsDictionary {
    private final static Map<Pattern, String> regExRules = ImmutableMap.<Pattern, String>builder()
            .put(Pattern.compile("(ch|s|x)$"), "$1es")
            .put(Pattern.compile("y$"), "ies")
            .put(Pattern.compile("(fe?)$"), "ves")
            .put(Pattern.compile("o?$"), "oes")
            .build();

    @Override
    public String translateByRule(@NotNull String key, int index) {
        final Pattern foundPattern = regExRules.keySet().stream().filter(pattern -> pattern.matcher(key).matches()).findFirst().orElse(null);
        if(Objects.isNull(foundPattern)) {
            return String.format("%ss", key);
        }
        return foundPattern.matcher(key).replaceAll(regExRules.get(foundPattern));
    }
}
