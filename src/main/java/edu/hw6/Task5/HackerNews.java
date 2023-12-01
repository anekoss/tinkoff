package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public class HackerNews {
    private final String titleFieldName = "title";
    private final String separatorKeyAndValue = ":";
    private final int timeout = 60;
    private final static long[] IOEXCEPTION_RESULT = new long[] {};
    private final HttpClient httpClient;

    public HackerNews() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public long[] hackerNewsTopStories() throws InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .timeout(Duration.of(timeout, ChronoUnit.SECONDS))
            .GET()
            .build();
        try {
            var response = sendAndGetHttpResponse(request);
            return Arrays.stream(response.body().replaceAll("([\\[\\]])", "").split(","))
                .mapToLong(value -> {
                    try {
                        return Long.parseLong(value);
                    } catch (NumberFormatException e) {
                        return ByteBuffer.wrap(value.getBytes(StandardCharsets.UTF_8)).getLong();
                    }
                }).toArray();
        } catch (IOException e) {
            return IOEXCEPTION_RESULT;
        }

    }

    public String news(long id) throws InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
            .timeout(Duration.of(timeout, ChronoUnit.SECONDS))
            .GET()
            .build();
        try {
            var response = sendAndGetHttpResponse(request);
            Optional<String> title = Arrays.stream(response.body()
                .split(",")).filter(s -> getFieldPattern(titleFieldName).matcher(s).find()).findAny();
            return title.map(s -> s
                .split(separatorKeyAndValue)[1].replaceAll("\"", "")).orElseGet(String::new);
        } catch (IOException e) {
            return "";
        }
    }

    public HttpResponse<String> sendAndGetHttpResponse(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        );
    }

    public Pattern getFieldPattern(String fieldName) {
        return Pattern.compile("\"" + fieldName + "\":\"[^\"]+\"");
    }

}
