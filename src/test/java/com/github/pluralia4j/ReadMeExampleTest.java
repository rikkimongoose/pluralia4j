package com.github.pluralia4j;

import com.github.pluralia4j.lang.PluralisationRussian;
import com.github.pluralia4j.template.MessageTemplate;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class ReadMeExampleTest extends TestCase {
    public void testReadMeExample() {
        //stored data
        final Map<String, Number> source = new HashMap<>();
        source.put("catsCount", 21);
        source.put("dogsCount", 10);

        //Create plural
        final Plural plural = new Plural(new PluralisationRussian());
        //fill dictionary
        plural.dict("кот", "кота", "котов");

        //Create a template
        final MessageTemplate messageTemplate = MessageTemplate.builder()
                .text("У вас ")
                .data("catsCount")
                .text(" ")
                .plural("catsCount", "кот")
                .text(", ")
                .data("dogsCount")
                .text(" ")
                //templates has their own dictionary
                .plural("dogsCount", "собака", "собаки", "собак")
                .build();
        assertEquals("У вас 21 кот, 10 собак", plural.plural(messageTemplate, source));
    }
}
