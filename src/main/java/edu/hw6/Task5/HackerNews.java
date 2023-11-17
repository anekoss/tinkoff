package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public class HackerNews {
    private final String titleFieldName = "title";
    private final String separatorKeyAndValue = ":";
    private final int timeout = 60;

    public long[] hackerNewsTopStories() throws InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .timeout(Duration.of(timeout, ChronoUnit.SECONDS))
            .GET()
            .build();
        try {
            var response = newHttpClient().send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );
            return Arrays.stream(response.body().replaceAll("([\\[\\]])", "").split(","))
                .mapToLong(value -> {
                    try {
                        return Long.parseLong(value);
                    } catch (NumberFormatException e) {
                        return ByteBuffer.wrap(value.getBytes(StandardCharsets.UTF_8)).getLong();
                    }
                }).toArray();
        } catch (
            IOException e) {
            return new long[] {};
        }

    }

    public String news(long id) throws InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
            .timeout(Duration.of(timeout, ChronoUnit.SECONDS))
            .GET()
            .build();
        try {
            var response = newHttpClient().send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );
            Optional<String> title = Arrays.stream(response.body()
                .split(",")).filter(s -> getFieldPattern(titleFieldName).matcher(s).find()).findAny();
            return title.map(s -> s
                .split(separatorKeyAndValue)[1].replaceAll("\"", "")).orElseGet(String::new);
        } catch (IOException e) {
            return "";
        }
    }

    public Pattern getFieldPattern(String fieldName) {
        return Pattern.compile("\"" + fieldName + "\":\"[^\"]+\"");
    }

}
