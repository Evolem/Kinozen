package ru.gbjava.kinozen.validators.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// For BreadCrumbs
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Link {
    String label();

    String family();

    String parent();
}
