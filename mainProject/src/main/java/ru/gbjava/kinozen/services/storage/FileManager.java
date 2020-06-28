package ru.gbjava.kinozen.services.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.kinozen.exceptions.StorageException;
import ru.gbjava.kinozen.exceptions.StorageFileNotFoundException;
import ru.gbjava.kinozen.utilites.FileNameGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
public class FileManager {

    //todo FileManager переделать в утилиту

    private final Path rootLocation;

    public FileManager(Path rootLocation) {
        this.rootLocation = rootLocation;
    }

    public String upload(MultipartFile file) {
        String[] fileNameArr = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String extension = fileNameArr[fileNameArr.length - 1];
        String filename = String.format("%s" + "." + "%s", FileNameGenerator.generate(rootLocation), extension);

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
        log.info("Upload success: " + rootLocation.resolve(filename));
        return filename;
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        try {
            Resource resource = new UrlResource(rootLocation.resolve(filename).toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void deleteFileByName(String imageName) {
        Path path = rootLocation.resolve(imageName);
        try {
            Files.deleteIfExists(path);
            log.info("Delete success :" + path.toString());
        } catch (NoSuchFileException e) {
            log.error("Delete file by name (no such): " + path);
        } catch (IOException e) {
            log.error("IOException" + e.getMessage());
        }
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
