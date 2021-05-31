package com.github.pluralia4j.template;

import com.github.pluralia4j.dictionary.WordformsDictionary;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A DSL template for message with pluralisation
 */
public final class MessageTemplate {
    /**
     * Message blocks for DSL
     */
    @Getter private final List<TemplateItem> templateItems;

    /**
     * Local wordforms dictionary
     */
    @Getter private final WordformsDictionary dictionary;

    /**
     * Private parametrised constructor for builder
     * @param templateItems
     * @param dictionary
     */
    private MessageTemplate(List<TemplateItem> templateItems, WordformsDictionary dictionary) {
        this.templateItems = templateItems;
        this.dictionary = dictionary;
    }

    /**
     * Get a builder for MessageTemplate.
     * @return
     */
    public static MessageTemplateBuilder builder() {
        return new MessageTemplateBuilder();
    }

    /**
     * {@link MessageTemplate} DSL Builder
     */
    public static class MessageTemplateBuilder {
        /**
         * Message blocks for DSL
         */
        private final List<TemplateItem> templateItemsSource = new ArrayList<>();

        /**
         * Local wordforms dictionary
         */
        private final WordformsDictionary dictionary = new WordformsDictionary();

        /**
         * Constructor
         */
        public MessageTemplateBuilder() {}

        /**
         * Add dictionary item
         * @param word basic word
         * @param wordforms wordforms for this word
         * @return chain of initialisation
         */
        public MessageTemplateBuilder dict(@NonNull String word, @NonNull String... wordforms) {
            dictionary.put(word, wordforms);
            return this;
        }

        /**
         * Add pure text item. Supports String.format-like formatting
         * @param format format string
         * @param args format arguments
         * @return chain of initialisation
         */
        public MessageTemplateBuilder text(@NonNull String format, Object... args) {
            templateItemsSource.add(new TemplateTextItem(String.format(format, args)));
            return this;
        }

        /**
         * Add data by map key item
         * @param key key in provided Map
         * @return chain of initialisation
         */
        public MessageTemplateBuilder data(@NonNull String key) {
            templateItemsSource.add(new TemplateDataItem(key, "%s"));
            return this;
        }

        /**
         * Add data by map key item with predefined format string
         * @param key key in provided Map=
         * @param format format string
         * @return chain of initialisation
         */
        public MessageTemplateBuilder data(@NonNull String key, @NotNull String format) {
            templateItemsSource.add(new TemplateDataItem(key, format));
            return this;
        }

        /**
         * Add plural by map key item. The word will be pluralised according to number extracted from Map by key item
         * @param mapKey key for data Map
         * @param word base word
         * @param wordforms plural wordforms for base word. Adds it to local dictionary
         * @return chain of initialisation
         */
        public MessageTemplateBuilder plural(@NonNull String mapKey, @NonNull String word, @NonNull String... wordforms) {
            if(wordforms.length > 0) {
                dict(word, wordforms);
            }
            templateItemsSource.add(new TemplatePluralItem(mapKey, word));
            return this;
        }

        /**
         * Add plural by number key item. The word will be pluralised according to provided number
         * @param num number to pluralize by
         * @param word base word
         * @param wordforms plural wordforms for base word. Adds it to local dictionary
         * @return chain of initialisation
         */
        public MessageTemplateBuilder plural(@NonNull Number num, @NonNull String word, @NonNull String... wordforms) {
            if(wordforms.length > 0) {
                dictionary.put(word, wordforms);
            }
            templateItemsSource.add(new TemplatePluralNumberItem(num, word));
            return this;
        }

        /**
         * Build template based on DSL
         * @return {@link TemplateItem} with
         */
        public MessageTemplate build() {
            return new MessageTemplate(templateItemsSource, dictionary);
        }
    }
}
