package ru.gbjava.kinozen.utilites;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class FileNameGenerator {

    // 1. Checks only provided folder, not enters inner folders;
    // 2. If folder is empty or contains invalid files returns 1;

    public static String generate(String fileName, String fileFolder) {
        try (Stream<Path> walk = Files.walk(Paths.get(fileFolder), 1)) {
            Optional<Integer> max = walk
                    .filter(Files::isRegularFile)
                    .filter(file -> isOnlyNumbersInNameExceptExtension(file.getFileName().toString()))
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

    private static int getNumberInName(Path file) {
        return Integer.parseInt(file.getFileName().toString().replaceAll("[.][^.]+$", ""));
    }

    private static boolean isOnlyNumbersInNameExceptExtension(String fileName) {
        String nameWithoutExtension = fileName.replaceAll("[.][^.]+$", "");
        return nameWithoutExtension.matches("[0-9]+");
    }

}
