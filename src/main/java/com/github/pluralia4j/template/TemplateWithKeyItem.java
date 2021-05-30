package com.github.pluralia4j.template;

import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

public class TemplateWithKeyItem extends TemplateItem {
    /**
     *
     */
    @Getter @Setter
    String key;

    @ConstructorProperties({"key"})
    public TemplateWithKeyItem(String key) {
        super();
        this.key = key;
    }
}
