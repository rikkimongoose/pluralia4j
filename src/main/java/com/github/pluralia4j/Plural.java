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

import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Log
public final class Plural {
    /**
     *
     */
    private static final Pluralisation RUSSIAN_RULE = new PluralisationRussian();
    /**
     *
     */
    private static final Pluralisation ENGLISH_RULE = new PluralisationEnglish();

    /**
     *
     */
    public static final Plural RUSSIAN = new Plural(RUSSIAN_RULE);
    /**
     *
     */
    public static final Plural ENGLISH = new Plural(ENGLISH_RULE);

    /**
     *
     */
    private final Pluralisation pluralisation;

    /**
     *
     */
    private final WordformsDictionary wordformsDictionary;

    @Getter
    @Setter
    private boolean removeTemplateDictionary = false;

    /**
     *
     * @param pluralisation
     */
    public Plural(@NonNull Pluralisation pluralisation) {
        this.pluralisation = pluralisation;
        this.wordformsDictionary = new WordformsDictionary();
    }

    /**
     *
     * @param pluralisation
     */
    public Plural(@NonNull Pluralisation pluralisation, @NonNull WordformsDictionary wordformsDictionary) {
        this(pluralisation);
        this.wordformsDictionary.put(wordformsDictionary);
    }

    /**
     *
     * @param key
     * @param wordforms
     * @return
     */
    public Plural put(String key, String... wordforms) {
        this.wordformsDictionary.put(key, wordforms);
        return this;
    }

    /**
     *
     * @param messageTemplate
     * @param data
     * @return
     */
    public String pluralByData(MessageTemplate messageTemplate, Map<String, Number> data) {
        WordformsDictionary messageTemplateWordforms = messageTemplate.getDictionary();
        wordformsDictionary.putTop(messageTemplateWordforms);
        final String result = messageTemplate.getTemplateItems()
                .stream()
                .map(templateItem -> templateToText(templateItem, data))
                .collect(Collectors.joining());
        if(removeTemplateDictionary) {
            wordformsDictionary.remove(messageTemplateWordforms);
        }
        return result;
    }

    /**
     *
     * @param templateItem
     * @param data
     * @return
     */
    public String templateToText(TemplateItem templateItem, Map<String, Number> data) {
        if(templateItem.getClass().isInstance(TemplateWithKeyItem.class)) {
            final TemplateWithKeyItem templateWithKeyItem = (TemplateWithKeyItem)templateItem;
            final String key = templateWithKeyItem.getKey();
            if(!data.containsKey(key)){
                log.warning(String.format("Provided data doesn't contain key: %s", key));
                return "";
            }
            if (templateWithKeyItem.getClass().isInstance(TemplateDataItem.class)) {
                final TemplateDataItem templateDataItem = (TemplateDataItem)templateItem;
                return String.format(templateDataItem.getFormat(), data.get(key));
            }

            if (templateWithKeyItem.getClass().isInstance(TemplatePluralItem.class)) {
                final TemplatePluralItem templateTextItem = (TemplatePluralItem)templateItem;
                final int index = pluralisation.wordformIndex(data.get(key));
                return wordformsDictionary.translate(templateTextItem.getWord(), index);
            }
        }

        if (templateItem.getClass().isInstance(TemplatePluralNumberItem.class)) {
            final TemplatePluralNumberItem templatePluralNumberItem = (TemplatePluralNumberItem)templateItem;
            final int index = pluralisation.wordformIndex(templatePluralNumberItem.getNum());
            return wordformsDictionary.translate(templatePluralNumberItem.getWord(), index);
        }

        if (templateItem.getClass().isInstance(TemplateTextItem.class)) {
            final TemplateTextItem templateTextItem = (TemplateTextItem)templateItem;
            return templateTextItem.getText();
        }

        return "";
    }
}
