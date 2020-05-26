package ru.gbjava.kinozen.utilites;

import com.ibm.icu.text.Transliterator;

public class StringConverter {

    private static final String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";

    public static String cyrillicToLatin(String cyrillic) {
        Transliterator toLatin = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        return toLatin.transliterate(cyrillic).toLowerCase();
    }
}
