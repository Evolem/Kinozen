package ru.gbjava.kinozen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.gbjava.kinozen.utilites.StringConverter;

public class StringConverterTest {

    @Test
    public void cyrillicToLatinTest() {
        String result = StringConverter.cyrillicToLatin("Тестовая строка");
        Assertions.assertEquals("testovaya stroka", result);
    }

    @Test
    public void cyrillicToLatinTest_stringWithSymbols() {
        String result = StringConverter.cyrillicToLatin("строка !@#$%^&*(){}-+=1");
        Assertions.assertEquals("stroka !@#$%^&*(){}-+=1", result);
    }

    @Test
    public void cyrillicToLatinTest_emptyString() {
        String result = StringConverter.cyrillicToLatin("");
        Assertions.assertEquals("", result);
    }

    @Test
    public void cyrillicToLatinTest_nullString() {
        Assertions.assertThrows(NullPointerException.class, () -> StringConverter.cyrillicToLatin(null));
    }

    @Test
    public void cyrillicToLatinTest_latinString() {
        String result = StringConverter.cyrillicToLatin("Test string second");
        Assertions.assertEquals("test string second", result);
    }

    @Test
    public void cyrillicToLatinTest_combinedString() {
        String result = StringConverter.cyrillicToLatin("Кириллица Latin");
        Assertions.assertEquals("kirillitsa latin", result);
    }
}
