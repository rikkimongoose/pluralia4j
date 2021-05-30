package com.github.pluralia4j.template;

import lombok.Value;
import java.beans.ConstructorProperties;

@Value
public class TemplatePluralItem extends TemplateWithKeyItem {
    /**
     *
     */
    String word;

    // HACK - Lombok can't catch derived classes
    /**
     * Parametrised constructor.
     *
     * @param key
     * @param word
     */
    @ConstructorProperties({"key", "word"})
    public TemplatePluralItem(String key, String word) {
        super(key);
        this.word = word;
    }
}
