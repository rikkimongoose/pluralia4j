package com.github.pluralia4j.dictionary;

import lombok.Data;

import java.util.Map;

/**
 * Model for external wordforms dictionary in YAML format
 */
@Data
public class WordformsDictionaryFile {
    /**
     * Words to wordforms dictionary, i.e.:
     *
     * formula: formulae
     * goose: geese
     * deer:
     * sheep:
     */
    Map<String, Object> exceptions;
}
