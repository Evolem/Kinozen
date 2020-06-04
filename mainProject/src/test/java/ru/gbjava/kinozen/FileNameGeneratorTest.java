package ru.gbjava.kinozen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.gbjava.kinozen.utilites.FileNameGenerator;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringJUnitConfig
@SpringBootTest
public class FileNameGeneratorTest {

    @Value("${files.storepath.video_download}")
    Path video_path;

    @Test
    public void generateTest_innerFolderCheck() {
        Path fleFolder = Paths.get("src/test/resources/filesForNameGeneratorTest/innerFolderCheck");
        String res = FileNameGenerator.generate(fleFolder);
        Assertions.assertEquals("3", res);
    }

    @Test
    public void generateTest_checkCorrectNameInCaseBigDifference() {
        Path fleFolder = Paths.get("src/test/resources/filesForNameGeneratorTest/bigDifferenceName");
        String res = FileNameGenerator.generate(fleFolder);
        Assertions.assertEquals("593", res);
    }

    @Test
    public void generateTest_fileInsteadFolderAsArgument() {
        Path fleFolder = Paths.get("src/test/resources/filesForNameGeneratorTest/bigDifferenceName/150.txt");
        String res = FileNameGenerator.generate(fleFolder);
        Assertions.assertEquals("151", res);
    }

    @Test
    public void generateTest_defaultFolderValueNotEmpty() {
        Assertions.assertNotNull(video_path);
        Assertions.assertFalse(video_path.getFileName().toString().isEmpty());
    }

    @Disabled //Папка для видео указана для операционок windows, этот тест на других ОС будет падать
    @Test
    public void generateTest_defaultFolderCheck() {
        String res = FileNameGenerator.generate(video_path);
        Assertions.assertTrue(Integer.parseInt(res) > 0);
    }
}
