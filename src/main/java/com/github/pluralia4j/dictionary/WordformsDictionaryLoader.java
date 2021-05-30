package com.github.pluralia4j.dictionary;

import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Objects;

public class WordformsDictionaryLoader {
    private final static Yaml yaml = new Yaml();

    public final static String RUSSIAN_DICTIONARY_RESOURCE = Paths.get("dictionaries", "dictionary-ru.yml").toString();
    public final static String ENGLISH_DICTIONARY_RESOURCE = Paths.get("dictionaries", "dictionary-en.yml").toString();

    @Getter(lazy=true) private final static WordformsDictionary russianDictionary = loadRussianDictionary();

    @Getter(lazy=true) private final static WordformsDictionary englishDictionary = loadEnglishDictionary();

    /**
     *
     * @return
     */
    public static WordformsDictionary loadRussianDictionary() {
        return loadFromResource(RUSSIAN_DICTIONARY_RESOURCE);
    }

    /**
     *
     * @return
     */
    public static WordformsDictionary loadEnglishDictionary() {
        EnglishWordformsDictionary englishWordformsDictionary = new EnglishWordformsDictionary();
        englishWordformsDictionary.put(loadFromResource(ENGLISH_DICTIONARY_RESOURCE));
        return englishWordformsDictionary;
    }

    /**
     *
     * @param resourceFileName
     * @return
     */
    private static WordformsDictionary loadFromResource(String resourceFileName) {
        return loadFromStream(WordformsDictionaryLoader.class
                .getClassLoader()
                .getResourceAsStream(resourceFileName));
    }

    /**
     *
     * @param inputStream
     * @return
     */
    public static WordformsDictionary loadFromStream(InputStream inputStream) {
        final WordformsDictionary wordformsDictionary = new WordformsDictionary();
        final WordformsDictionaryFile wordformsDictionaryFile = yaml.load(inputStream);
        if(Objects.nonNull(wordformsDictionaryFile.getExceptions())) {
            wordformsDictionaryFile.getExceptions().forEach((key, obj) -> {
                if (obj instanceof String) {
                    wordformsDictionary.put(key, (String) obj);
                }
                if (obj instanceof String[]) {
                    wordformsDictionary.put(key, (String[]) obj);
                }
                wordformsDictionary.put(key);
            });
        }
        return wordformsDictionary;
    }
}
