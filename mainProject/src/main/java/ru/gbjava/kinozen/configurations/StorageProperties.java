package ru.gbjava.kinozen.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */

    @Value("${files.storage.video_download}")
    private Path location;

    public Path getLocation() {
        return location;
    }

    public void setLocation(Path location) {
        this.location = location;
    }

}
