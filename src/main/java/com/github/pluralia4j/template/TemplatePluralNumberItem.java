package com.github.pluralia4j.template;

import lombok.Value;

import java.beans.ConstructorProperties;

/**
 * Word, pluralised by already defined number
 */
@Value
public class TemplatePluralNumberItem extends TemplateItem {

    /**
     * Number of items
     */
    Number num;

    /**
     * Word to pluralise
     */
    String word;

    // HACK - Lombok can't catch derived classes
    /**
     * Parametrised constructor.
     *
     * @param num number of itemss
     * @param word word to pluralise
     */
    @ConstructorProperties({"num", "word"})
    public TemplatePluralNumberItem(Number num, String word) {
        super();
        this.num = num;
        this.word = word;
    }
}
