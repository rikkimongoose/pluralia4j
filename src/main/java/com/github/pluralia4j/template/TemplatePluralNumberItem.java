package com.github.pluralia4j.template;

import lombok.Value;

import java.beans.ConstructorProperties;

@Value
public class TemplatePluralNumberItem extends TemplateItem {

    /**
     *
     */
    Number num;

    /**
     *
     */
    String word;

    // HACK - Lombok can't catch derived classes
    /**
     * Parametrised constructor.
     *
     * @param num
     * @param word
     */
    @ConstructorProperties({"num", "word"})
    public TemplatePluralNumberItem(Number num, String word) {
        super();
        this.num = num;
        this.word = word;
    }
}
