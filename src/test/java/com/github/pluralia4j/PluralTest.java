package com.github.pluralia4j;

import com.github.pluralia4j.lang.PluralisationRussian;
import com.github.pluralia4j.template.MessageTemplate;
import com.google.common.collect.ImmutableMap;
import junit.framework.TestCase;

import java.util.Map;

public class PluralTest extends TestCase {
    private static final String ITEMS_0 = "cats0";
    private static final String ITEMS_1 = "cats1";
    private static final String ITEMS_2 = "cats2";
    private static final String ITEMS_5 = "cats5";
    private static final String ITEMS_11 = "cats11";
    private static final String ITEMS_20 = "cats20";
    private static final String ITEMS_21 = "cats21";
    private static final String ITEMS_2_2 = "cats2_2";
    private static final String ITEMS_2_5 = "cats2_5";

    public void testPluralWithData() {
        final Map<String, Number> source = ImmutableMap.<String, Number>builder()
                    .put(ITEMS_0, 0)
                    .put(ITEMS_1, 1)
                    .put(ITEMS_2, 2)
                    .put(ITEMS_5, 5)
                    .put(ITEMS_11, 11)
                    .put(ITEMS_20, 20)
                    .put(ITEMS_21, 21)
                    .put(ITEMS_2_2, 2.2)
                    .put(ITEMS_2_5, 2.5)
                .build();

        //Integer
        final MessageTemplate messageTemplate = MessageTemplate.builder()
                .text("У нас было")
                .dict("кот", "котов", "кота")
                .text(" ").data(ITEMS_0).text(" ")
                .plural(ITEMS_0, "кот")
                .text(" ").data(ITEMS_1).text(" ")
                .plural(ITEMS_1, "кот")
                .text(" ").data(ITEMS_2).text(" ")
                .plural(ITEMS_2, "кот")
                .text(" ").data(ITEMS_5).text(" ")
                .plural(ITEMS_5, "кот")
                .text(" ").data(ITEMS_11).text(" ")
                .plural(ITEMS_11, "кот")
                .text(" ").data(ITEMS_20).text(" ")
                .plural(ITEMS_20, "кот")
                .text(" ").data(ITEMS_21).text(" ")
                .plural(ITEMS_21, "кот")
                .build();

        assertEquals("У нас было 0 котов 1 кот 2 кота 5 котов 11 котов 20 котов 21 кот", Plural.RUSSIAN.plural(messageTemplate, source));

        // Double
        final MessageTemplate messageTemplateDouble = MessageTemplate.builder()
                .text("У нас было")
                .dict("кот", "котов", "кота")
                .text(" ").data(ITEMS_2_2).text(" ")
                .plural(ITEMS_2_2, "кот")
                .text(" ").data(ITEMS_2_5).text(" ")
                .plural(ITEMS_2_5, "кот")
                .build();

        assertEquals("У нас было 2.2 кота 2.5 котов", Plural.RUSSIAN.plural(messageTemplateDouble, source));

        final MessageTemplate messageTemplateWordCase = MessageTemplate.builder()
                .text("У нас было")
                .text(" ").data(ITEMS_0).text(" ")
                .plural(ITEMS_0, "Кот")
                .text(" ").data(ITEMS_2).text(" ")
                .plural(ITEMS_2, "КОТ")
                .build();

        assertEquals("У нас было 0 Котов 2 КОТА", Plural.RUSSIAN.plural(messageTemplateWordCase, source));
    }

    public void testPlural() {
        //Integer
        final MessageTemplate messageTemplate = MessageTemplate.builder()
                .dict("кот", "котов", "кота")
                .text("У нас было")
                .text(" 0 ")
                .plural(0, "кот")
                .text(" 1 ")
                .plural(1, "кот")
                .text(" 2 ")
                .plural(2, "кот")
                .text(" 5 ")
                .plural(5, "кот")
                .text(" 11 ")
                .plural(11, "кот")
                .text(" 20 ")
                .plural(20, "кот")
                .text(" 21 ")
                .plural(21, "кот")
                .build();

        assertEquals("У нас было 0 котов 1 кот 2 кота 5 котов 11 котов 20 котов 21 кот", Plural.RUSSIAN.plural(messageTemplate));
    }

    public void testDict() {
        final Plural plural = new Plural(new PluralisationRussian());
        plural.dict("кот", "котов", "кота");
        final MessageTemplate messageTemplate = MessageTemplate.builder()
                .text("У нас было")
                .text(" 0 ")
                .plural(0, "кот")
                .text(" 1 ")
                .plural(1, "кот")
                .text(" 2 ")
                .plural(2, "кот")
                .text(" 5 ")
                .plural(5, "кот")
                .text(" 11 ")
                .plural(11, "кот")
                .text(" 20 ")
                .plural(20, "кот")
                .text(" 21 ")
                .plural(21, "кот")
                .build();

        assertEquals("У нас было 0 котов 1 кот 2 кота 5 котов 11 котов 20 котов 21 кот", plural.plural(messageTemplate));
    }
}