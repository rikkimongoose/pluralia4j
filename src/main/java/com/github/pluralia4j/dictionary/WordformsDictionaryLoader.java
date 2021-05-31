package com.github.pluralia4j.dictionary;

import lombok.Getter;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Loader class for dictionaries stored in YAML format
 */
public class WordformsDictionaryLoader {
    private final static Yaml yaml = new Yaml(new Constructor(WordformsDictionaryFile.class));

    public final static String RUSSIAN_DICTIONARY_RESOURCE = Paths.get("dictionaries", "dictionary-ru.yml").toString();
    public final static String ENGLISH_DICTIONARY_RESOURCE = Paths.get("dictionaries", "dictionary-en.yml").toString();

    @Getter(lazy=true) private final static WordformsDictionary russianDictionary = loadRussianDictionary();

    @Getter(lazy=true) private final static WordformsDictionary englishDictionary = loadEnglishDictionary();

    /**
     * Load {@link WordformsDictionary}from resources the default Russian dictionary of plural forms
     * @return {@link WordformsDictionary} based on dictionaries/dictionary-ru.yml from resources
     */
    public static WordformsDictionary loadRussianDictionary() {
        return loadFromResource(RUSSIAN_DICTIONARY_RESOURCE);
    }

    /**
     * Load {@link WordformsDictionary} from resources the default English dictionary of plural forms
     * @return {@link WordformsDictionary} based on dictionaries/dictionary-en.yml from resources
     */
    public static WordformsDictionary loadEnglishDictionary() {
        EnglishWordformsDictionary englishWordformsDictionary = new EnglishWordformsDictionary();
        englishWordformsDictionary.put(loadFromResource(ENGLISH_DICTIONARY_RESOURCE));
        return englishWordformsDictionary;
    }

    /**
     * Load {@link WordformsDictionary} from local resources.
     * @param resourceFilePath resource file name
     * @return {@link WordformsDictionary} from defined recourse
     */
    private static WordformsDictionary loadFromResource(String resourceFilePath) {
        return loadFromStream(WordformsDictionaryLoader.class
                .getClassLoader()
                .getResourceAsStream(resourceFilePath));
    }

    /**
     * Load {@link WordformsDictionary} from an input stream.
     * @param inputStream source stream
     * @return {@link WordformsDictionary} parsed from input stream.
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
