package org.example.asynchronous.video;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class VideoProcessor {
    private static final String exception = "EXCEPTION";
    private final ThreadPoolTaskExecutor executor;
    private final FFmpegExecutor fFmpegExecutor;

    public List<String> extractThumbnailsInParallel(List<String> videos) throws IOException{
        List<CompletableFuture<String>> completableFutures = new ArrayList<>();

        for (String video : videos) {
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(extractThumbnail(video), executor)
                    .exceptionally(
                            e -> {
                                log.error("error occurring in extracted thumbnail of {} ", video, e);
                                return exception;
                            }
                    );
            completableFutures.add(completableFuture);
        }
        final CompletableFuture<List<String>> completableFuture = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]))
                .thenApply(v -> completableFutures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
                );

        return completableFuture.join();
    }

    public Supplier<String> extractThumbnail(String videoPath) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("thumbnails");
        final String path = classPathResource.getURI().getPath();

        return () -> {
            log.info("starting extracted thumbnail of {}", videoPath);
            final String thumbnailFilename = UUID.randomUUID().toString();

            FFmpegBuilder fFmpegBuilder = new FFmpegBuilder()
                    .addInput(videoPath)
                    .addExtraArgs("-ss", "00:00:01")
                    .addOutput(path + "/" + thumbnailFilename + ".png")
                    .setFrames(1)
                    .done();

            fFmpegExecutor.createJob(fFmpegBuilder).run();

            return thumbnailFilename;
        };
    }
}
