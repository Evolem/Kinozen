package ru.gbjava.kinozen.utilites;

import com.ibm.icu.text.Transliterator;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StringConverter {

    private final String CYRILLIC_TO_LATIN = "Russian-Latin/BGN; Latin-ASCII";

    public String cyrillicToLatin(String cyrillic) {
        Transliterator toLatin = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        return toLatin.transliterate(cyrillic).toLowerCase().trim()
                .replaceAll("\\s+", "-")
                .replaceAll("[^a-z0-9_-]", "");
    }
}
