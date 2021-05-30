package com.github.pluralia4j.template;

import com.github.pluralia4j.lang.Pluralisation;
import lombok.Value;

import java.beans.ConstructorProperties;
import java.util.Map;

@Value
public class TemplateDataItem extends TemplateWithKeyItem {
    /**
     * Data format
     */
    String format;

    // HACK - Lombok can't catch derived classes
    /**
     *
     * @param key data to insert
     * @param format format for data
     */
    @ConstructorProperties({"key", "format"})
    public TemplateDataItem(String key, String format) {
        super(key);
        this.format = format;
    }
}
