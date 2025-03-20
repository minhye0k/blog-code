package org.example.asynchronous.config;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FFmpegConfig {


    @Bean
    public FFmpegExecutor ffmpegExecutor() throws IOException {
        FFmpeg fFmpeg = new FFmpeg("/opt/homebrew/Cellar/ffmpeg/7.1.1_1/bin/ffmpeg");
        FFprobe fFprobe = new FFprobe("/opt/homebrew/Cellar/ffmpeg/7.1.1_1/bin/ffprobe");

        return new FFmpegExecutor(fFmpeg, fFprobe);
    }
}
