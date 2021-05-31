# pluralia4j

**pluralia4j** is a lightweight library for Java 8+ that makes generating plural version of words easy.

In most langauges nouns have plural forms. When you display a message, you want it to look natural. A format string isn't enough for it, i.e.

```java
  String.format("You have %s new messages.", newMessagesSize);
  //If newMessageSize = 1, you got "You have 1 new messages.", that is correct Java, but poor English.
```

If values are double, it brings even more trouble (0.5 inch, 1.5 inches, 1 inch, 0 inches, -1 inch, etc). Choosing the right variant [is hard even to native speakers](https://painintheenglish.com/case/2396).

The English rules for plural nouns aren't easy. The languages with more complicated grammar has more complicated rules as well. A lot of languages (like Irish or Arabic) has not only Singular and Plural, but also [Dual grammatical number](https://en.wikipedia.org/wiki/Dual_(grammatical_number)). Some languages has [Paukal number](https://en.wikipedia.org/wiki/Grammatical_number#Paucal) as well (Russian is most popular of them).

That makes i18n of messages with numbers a true pain. This rules are mostly aren't explained in schools.

But **pluralia4j** comes to the rescue. Based on standart [CLDR Unicode Plural Rules](https://unicode-org.github.io/cldr-staging/charts/37/supplemental/language_plural_rules.html), pluralia4j provides a small but powerful DSL to make your messages grammatically consistent.

## Usage
Translate using external data:

```java
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
        
    plural.plural(messageTemplate, source); //returns "У вас 21 кот, 10 собак"
```
### Requirements

Java 1.8+

### License

This project available under Apache 2.0 license
