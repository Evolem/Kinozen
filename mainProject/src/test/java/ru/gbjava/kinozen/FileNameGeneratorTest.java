package ru.gbjava.kinozen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.gbjava.kinozen.utilites.FileNameGenerator;

public class FileNameGeneratorTest {

    @Test
    public void generateTest() {
        String res = FileNameGenerator.generate("t", "src/test/resources/filesForNameGeneratorTest");
        Assertions.assertEquals("3", res);
    }
}
