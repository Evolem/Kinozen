package ru.gbjava.playerzen.utilites;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

@UtilityClass
public class FileNameGenerator {

    // 1. Проверяет только переданную папку, вложенные папки не проверяются;
    // 2. Если папка пуста или нету файлов с номером в имени - возвращается 1;

    public String generate(Path fileFolder) {
        try (Stream<Path> walk = Files.walk(fileFolder, 1)) {
            Optional<Integer> max = walk
                    .filter(Files::isRegularFile)
                    .filter(file -> isOnlyNumbersInNameExceptExtension(file.getFileName()))
                    .max(Comparator.comparing(file -> getNumberInName(file.getFileName())))
                    .map(file -> getNumberInName(file.getFileName()));
            if (max.isPresent()) {
                int result = max.get() + 1;
                return Integer.toString(result);
            } else return "1";
        } catch (IOException e) {
            throw new IllegalArgumentException("Some problem with fileFolder", e);
        }
    }

    private int getNumberInName(Path file) {
        return Integer.parseInt(file.toString().replaceAll("[.][^.]+$", ""));
    }

    private boolean isOnlyNumbersInNameExceptExtension(Path fileName) {
        String nameWithoutExtension = fileName.toString().replaceAll("[.][^.]+$", "");
        return nameWithoutExtension.matches("[0-9]+");
    }
}
