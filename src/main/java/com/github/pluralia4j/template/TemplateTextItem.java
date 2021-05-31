package com.github.pluralia4j.template;

import lombok.Value;

import java.beans.ConstructorProperties;

@Value
public class TemplateTextItem extends TemplateItem {
    /**
     * Pure text item
     */
    String text;

    // HACK - Lombok can't catch derived classes
    /**
     * Parametrised constructor.
     * @param text pure text
     */
    @ConstructorProperties({"text"})
    public TemplateTextItem(String text) {
        super();
        this.text = text;
    }
}
