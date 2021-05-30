package com.github.pluralia4j;

import com.github.pluralia4j.template.MessageTemplate;
import com.google.common.collect.ImmutableMap;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Map;

public class PluralTest extends TestCase {
    private static final String ITEMS_0 = "cats0";
    private static final String ITEMS_1 = "cats1";
    private static final String ITEMS_2 = "cats2";
    private static final String ITEMS_5 = "cats5";
    private static final String ITEMS_11 = "cats11";
    private static final String ITEMS_20 = "cats20";
    private static final String ITEMS_21 = "cats21";

    @Test
    public void testPluralByData() {
        Map<String, Number> source = ImmutableMap.<String, Number>builder()
                    .put(ITEMS_0, 0)
                    .put(ITEMS_1, 1)
                    .put(ITEMS_2, 2)
                    .put(ITEMS_5, 5)
                    .put(ITEMS_11, 11)
                    .put(ITEMS_20, 20)
                    .put(ITEMS_21, 21)
                .build();

        MessageTemplate messageTemplate = MessageTemplate.builder()
                .text("У нас было")
                .text(" ").data(ITEMS_0).text(" ")
                .plural(ITEMS_0, "кот", "кота", "котов")
                .text(" ").data(ITEMS_1).text(" ")
                .plural(ITEMS_1, "кот", "кота", "котов")
                .text(" ").data(ITEMS_2).text(" ")
                .plural(ITEMS_2, "кот", "кота", "котов")
                .text(" ").data(ITEMS_5).text(" ")
                .plural(ITEMS_5, "кот", "кота", "котов")
                .text(" ").data(ITEMS_11).text(" ")
                .plural(ITEMS_11, "кот", "кота", "котов")
                .text(" ").data(ITEMS_20).text(" ")
                .plural(ITEMS_20, "кот", "кота", "котов")
                .text(" ").data(ITEMS_21).text(" ")
                .plural(ITEMS_21, "кот", "кота", "котов")
                .build();

        assertEquals("У нас было 0 котов 1 кот 2 кота 5 котов 11 котов 20 котов 21 кот", Plural.RUSSIAN.pluralByData(messageTemplate, source));
    }
}