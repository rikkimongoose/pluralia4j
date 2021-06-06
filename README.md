**pluralia4j** is a lightweight library for Java 8+ that makes generating plural version of words easy.

In most langauges nouns have plural forms. When you display a message, you want it to look natural. A format string isn't enough for it, i.e.

```java
String.format("You have %s new messages", newMessagesSize);
// if newMessageSize == 1, text became "You have 1 new messages".
// It's correct Java, but poor English.
```

If values are double, it brings even more trouble (0.5 inch, 1.5 inches, 1 inch, 0 inches, -1 inch, etc). Choosing the right variant [is hard even to native speakers](https://painintheenglish.com/case/2396).

The English rules for plural nouns aren't easy. The languages with more complicated grammar has more complicated rules as well. A lot of languages (like Irish or Arabic) has not only Singular and Plural, but also [Dual grammatical number](https://en.wikipedia.org/wiki/Dual_(grammatical_number)). Some languages has [Paukal number](https://en.wikipedia.org/wiki/Grammatical_number#Paucal) as well (Russian is most popular of them).

That makes i18n of messages with numbers a true pain. This rules are mostly aren't explained in schools.

But **pluralia4j** comes to the rescue. Based on standart [CLDR Unicode Plural Rules](https://unicode-org.github.io/cldr-staging/charts/37/supplemental/language_plural_rules.html), pluralia4j provides a small but powerful DSL to make your messages grammatically consistent.

## Install
The **pluralia4j** package is currently avaible at Maven Central repository.

1. Add to yours `pom.xml`:
```xml
<dependency>
  <groupId>io.github.rikkimongoose</groupId>
  <artifactId>pluralia4j</artifactId>
  <version>1.0</version>
</dependency>
```
2. Run via command line:
```bash
$ mvn install
```

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

## Pluralia DSL
* **text(@NonNull String format, Object... args)** — just put text. The format string-like attributes are allowed/
* **data(@NonNull String key)** — extract data from provided map by key
* **data(@NonNull String key, @NotNull String format)** — extract data from provided map by key and display it formatted
* **plural(@NonNull String mapKey, @NonNull String word, @NonNull String... wordforms)** — Add plural by number key item. The word will be pluralised according to provided numberadd plural by map key item. The word will be pluralised according to number extracted from Map by key item
* **plural(@NonNull Number num, @NonNull String word, @NonNull String... wordforms)** — Add plural by number key item. The word will be pluralised according to provided number

## Adding new languages
To add a new language, just implement plural rules for it deriving from `com.github.pluralia4j.lang.Pluralisation` class. Then just use it for `Plural` object:

```java
final Plural plural = new Plural(new MyPluralisation());
```
You can add your own ```WordformsDictionary``` with predefined forms as well.

```java
final Plural plural = new Plural(new MyPluralisation(), myWordfromsDictionary);
```

You can even add your predefined wordforms dictionary from a YAML file, using `WordformsDictionaryLoader` class:

```java
final Plural plural = new Plural(new MyPluralisation(), WordformsDictionaryLoader.loadFromStream(myFileStream));
```

An example of YAML wordforms dictionary:

```yaml
exceptions:
  formula: formulae
  mouse: mice
  goose: geese
  deer:
  sheep:
```

or, for a language with multiple forms:

```yaml
exceptions:
  секунда: [секунды, секунд]
  минута: [минуты, минут]
  час: [часа, часов]
  день: [дня, дней]
  неделя: [недели, недель]
  месяц: [месяца, месяцев]
  квартал: [квартала, кварталов]
  год: [года,лет]
  десятилетие: [десятилетия, десятилетий]
  столетие: [столетия, столетий]
  век: [века, веков]
  тысячилетие: [тысячилетия, тысячилетий]
```

## Requirements
Java 1.8+

## License
This project available under Apache 2.0 license
