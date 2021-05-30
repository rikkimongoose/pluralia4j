package com.github.pluralia4j.template;

import com.github.pluralia4j.dictionary.WordformsDictionary;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class MessageTemplate {
    private final List<TemplateItem> templateItems;

    @Getter private final WordformsDictionary dictionary;

    private MessageTemplate(List<TemplateItem> templateItems, WordformsDictionary dictionary) {
        this.templateItems = templateItems;
        this.dictionary = dictionary;
    }

    public static MessageTemplateBuilder builder() {
        return new MessageTemplateBuilder();
    }

    public List<TemplateItem> getTemplateItems() { return templateItems; }

    public static class MessageTemplateBuilder {
        private final List<TemplateItem> templateItemsSource = new ArrayList<TemplateItem>();

        private final WordformsDictionary dictionary = new WordformsDictionary();

        public MessageTemplateBuilder() {}

        public MessageTemplateBuilder dict(@NonNull String word, @NonNull String... wordforms) {
            dictionary.put(word, wordforms);
            return this;
        }

        public MessageTemplateBuilder text(@NonNull String text, Object... objects) {
            templateItemsSource.add(new TemplateTextItem(String.format(text, objects)));
            return this;
        }

        public MessageTemplateBuilder data(@NonNull String key) {
            templateItemsSource.add(new TemplateDataItem(key, "%s"));
            return this;
        }

        public MessageTemplateBuilder data(@NonNull String key, @NotNull String format) {
            templateItemsSource.add(new TemplateDataItem(key, format));
            return this;
        }

        public MessageTemplateBuilder plural(@NonNull String mapKey, @NonNull String word, @NonNull String... wordforms) {
            dict(word, wordforms);
            templateItemsSource.add(new TemplatePluralItem(mapKey, word));
            return this;
        }
        public MessageTemplateBuilder plural(@NonNull Number num, @NonNull String word, @NonNull String... wordforms) {
            if(wordforms.length > 0) {
                dictionary.put(word, wordforms);
            }
            templateItemsSource.add(new TemplatePluralNumberItem(num, word));
            return this;
        }

        public MessageTemplate build() {
            return new MessageTemplate(templateItemsSource, dictionary);
        }
    }
}
