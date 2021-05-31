package com.github.pluralia4j;

import com.github.pluralia4j.dictionary.WordformsDictionary;
import com.github.pluralia4j.lang.Pluralisation;
import com.github.pluralia4j.lang.PluralisationEnglish;
import com.github.pluralia4j.lang.PluralisationRussian;
import com.github.pluralia4j.template.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.java.Log;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Plural converter from template item using pluralisation rule for a language
 */
@Log
public final class Plural {
    /**
     * Already defined Plural with pluralisation rules for Russian language
     */
    public static final Plural RUSSIAN = new Plural(new PluralisationRussian());

    /**
     * Already defined Plural with pluralisation rules for English language
     */
    public static final Plural ENGLISH = new Plural(new PluralisationEnglish());

    /**
     * Pluralisation rules for current
     */
    private final Pluralisation pluralisation;

    /**
     * Local dictionary for current Plural
     */
    private final WordformsDictionary wordformsDictionary;

    /**
     * Store wordforms dictionary in Plural even when generation from a template is over.
     */
    @Getter
    @Setter
    private boolean cacheDictionaryFromTemplate = true;

    /**
     * Constructor just with a pluralisation rule
     *
     * @param pluralisation pluralisation rule for a language
     */
    public Plural(@NonNull Pluralisation pluralisation) {
        this.pluralisation = pluralisation;
        this.wordformsDictionary = new WordformsDictionary();
    }

    /**
     * Constructor just with a pluralisation rule and already defined dictionary of wordforms
     *
     * @param pluralisation pluralisation rule for a language
     * @param wordformsDictionary dictionary of wordforms
     */
    public Plural(@NonNull Pluralisation pluralisation, @NonNull WordformsDictionary wordformsDictionary) {
        this(pluralisation);
        this.wordformsDictionary.put(wordformsDictionary);
    }

    /**
     * Add wordforms record to local dictionary
     *
     * @param word word
     * @param wordforms plural wordforms for this word
     * @return chain of initialisation
     */
    public Plural dict(String word, String... wordforms) {
        this.wordformsDictionary.put(word, wordforms);
        return this;
    }

    /**
     * Make plural form from {@link MessageTemplate} using Map<String, Number>
     *
     * @param messageTemplate template in local DSL
     * @param data data source
     * @return String with proper plural forms, based on messageTemplate
     */
    public String plural(@NonNull MessageTemplate messageTemplate, Map<String, Number> data) {
        WordformsDictionary messageTemplateWordforms = messageTemplate.getDictionary();
        wordformsDictionary.putTop(messageTemplateWordforms);
        final String result = messageTemplate.getTemplateItems()
                .stream()
                .map(templateItem -> templateToText(templateItem, data))
                .collect(Collectors.joining());
        if(!cacheDictionaryFromTemplate) {
            wordformsDictionary.remove(messageTemplateWordforms);
        }
        return result;
    }

    /**
     * Make plural form from {@link MessageTemplate} without using Map<String, Number>
     *
     * @param messageTemplate template in local DSL
     * @return String with proper plural forms, based on messageTemplate
     */
    public String plural(@NonNull MessageTemplate messageTemplate) {
        return plural(messageTemplate, Collections.emptyMap());
    }

    /**
     * Convert template item to pluralised text
     *
     * @param templateItem template item of DSL
     * @param data data source
     * @return pluralised text for current template item
     */
    private String templateToText(TemplateItem templateItem, Map<String, Number> data) {
        if(templateItem instanceof TemplateWithKeyItem) {
            final TemplateWithKeyItem templateWithKeyItem = (TemplateWithKeyItem)templateItem;
            final String key = templateWithKeyItem.getKey();
            if(!data.containsKey(key)){
                log.warning(String.format("Provided data doesn't contain key: %s", key));
                return "";
            }
            if (templateWithKeyItem instanceof TemplateDataItem) {
                final TemplateDataItem templateDataItem = (TemplateDataItem)templateItem;
                return String.format(templateDataItem.getFormat(), data.get(key));
            }

            if (templateWithKeyItem instanceof TemplatePluralItem) {
                final TemplatePluralItem templateTextItem = (TemplatePluralItem)templateItem;
                final int index = pluralisation.wordformIndex(data.get(key));
                return wordformsDictionary.plural(templateTextItem.getWord(), index);
            }
        }

        if (templateItem instanceof TemplatePluralNumberItem) {
            final TemplatePluralNumberItem templatePluralNumberItem = (TemplatePluralNumberItem)templateItem;
            final int index = pluralisation.wordformIndex(templatePluralNumberItem.getNum());
            return wordformsDictionary.plural(templatePluralNumberItem.getWord(), index);
        }

        if (templateItem instanceof TemplateTextItem) {
            final TemplateTextItem templateTextItem = (TemplateTextItem)templateItem;
            return templateTextItem.getText();
        }

        return "";
    }
}
