package com.github.pluralia4j.template;

import lombok.Value;
import java.beans.ConstructorProperties;

/**
 * Word, pluralised by extracted data
 */
@Value
public class TemplatePluralItem extends TemplateWithKeyItem {
    /**
     * Word to pluralise
     */
    String word;

    // HACK - Lombok can't catch derived classes
    /**
     * Parametrised constructor.
     * @param key key to extract the data by
     * @param word word to pluralise
     */
    @ConstructorProperties({"key", "word"})
    public TemplatePluralItem(String key, String word) {
        super(key);
        this.word = word;
    }
}
