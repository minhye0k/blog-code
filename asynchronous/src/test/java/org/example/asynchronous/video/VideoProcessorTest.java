package org.example.asynchronous.video;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class VideoProcessorTest {
    @Autowired
    private VideoProcessor videoProcessor;

    @Test
    void extractThumbnailsInParallel() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("videos");
        log.info(classPathResource.getURL().getPath());

        File dir = new File(classPathResource.getURL().getPath());
        final File[] files = dir.listFiles();
        assert files != null;
        final List<String> filePaths = Arrays.stream(files).map(File::getAbsolutePath).toList();

        videoProcessor.extractThumbnailsInParallel(filePaths);
    }

    @Test
    void extractThumbnail() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("videos");
        log.info(classPathResource.getURL().getPath());

        File dir = new File(classPathResource.getURL().getPath());
        final File[] files = dir.listFiles();
        assert files != null;
        final List<String> filePaths = Arrays.stream(files).map(File::getAbsolutePath).toList();

        filePaths.forEach(filePath -> {
            try {
                videoProcessor.extractThumbnail(filePath).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}