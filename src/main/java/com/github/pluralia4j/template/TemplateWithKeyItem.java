package com.github.pluralia4j.template;

import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

/**
 * An item that extracts data from provided Map<String, Number>
 */
public class TemplateWithKeyItem extends TemplateItem {
    /**
     * Key to extract the data by
     */
    @Getter @Setter
    String key;

    /**
     * Parametrised constructor.
     * @param key key to extract the data by
     */
    @ConstructorProperties({"key"})
    public TemplateWithKeyItem(String key) {
        super();
        this.key = key;
    }
}
