package ru.gbjava.kinozen.utilites.breadCrumbs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Link {
    String label();

    String family();

    String parent();
}
