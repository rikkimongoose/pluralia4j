package com.github.pluralia4j.template;

import com.github.pluralia4j.Plural;
import com.google.common.collect.ImmutableMap;
import junit.framework.TestCase;

import java.util.Map;

public class MessageTemplateTest extends TestCase {

    private static final String MICE_COUNT = "cats2_5";
    private static final String RATS_COUNT = "cats2_2";

    public void testBuilder() {
        final Map<String, Number> source = ImmutableMap.<String, Number>builder()
                .put(MICE_COUNT, 3)
                .put(RATS_COUNT, 177)
                .build();

        final MessageTemplate messageTemplate = MessageTemplate.builder()
                .dict("кот", "котов", "кота")
                .dict("крыса", "крыс", "крысы")
                .text("Мы тестируем: %s, %s, %s и %s. ", "котов", "собак", "мышей", "крыс")
                .text("У нас есть 3 ")
                .plural(3, "кот")
                .text(", 3 ")
                .plural(3, "собака", "собак", "собаки")
                .text(", ")
                .data(MICE_COUNT)
                .text(" ")
                .plural(MICE_COUNT, "мышь", "мышей", "мыши")
                .text(", ")
                .data(RATS_COUNT)
                .text(" ")
                .plural(RATS_COUNT, "крыса")
                .text(". А если перевести число крыс в восьмеричную систему, получится ")
                .data(RATS_COUNT, "0x%h")
                .build();

        assertEquals("Мы тестируем: котов, собак, мышей и крыс. У нас есть 3 кота, 3 собаки, 3 мыши, 177 крыс. А если перевести число крыс в восьмеричную систему, получится 0xb1", Plural.RUSSIAN.plural(messageTemplate, source));
    }
}