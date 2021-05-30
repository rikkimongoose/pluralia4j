package com.github.pluralia4j.template;

import lombok.Value;

import java.beans.ConstructorProperties;

/**
 * Extract data from provided Map and show it in a special format
 */
@Value
public class TemplateDataItem extends TemplateWithKeyItem {
    /**
     * Data format
     */
    String format;

    // HACK - Lombok can't catch derived classes
    /**
     * Parametrised constructor.
     * @param key key to extract the data by
     * @param format format for data
     */
    @ConstructorProperties({"key", "format"})
    public TemplateDataItem(String key, String format) {
        super(key);
        this.format = format;
    }
}
