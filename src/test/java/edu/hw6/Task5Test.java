package edu.hw6;

import edu.hw6.Task5.HackerNews;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class Task5Test {
    private final HackerNews hackerNews = new HackerNews();

    @Test
    void hackerNewsTopStoriesTest() throws InterruptedException {
        long[] result = hackerNews.hackerNewsTopStories();
        assertThat(result.length).isLessThanOrEqualTo(500).isGreaterThan(0);
        assertThat(result).contains(38423469L);
        assertThat(result).contains(38416380L);

    }

    @Test
    void newsTitleTest() throws InterruptedException {
        assertThat(hackerNews.news(37570037)).isEqualTo("JDK 21 Release Notes");
    }

    @Test
    void newsTypeTest() throws InterruptedException, IOException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + 37570037 + ".json"))
            .timeout(Duration.of(60, ChronoUnit.SECONDS))
            .GET()
            .build();
        HttpResponse<String> httpResponse = hackerNews.sendAndGetHttpResponse(request);
        HackerNews hackerNews = Mockito.mock(HackerNews.class);
        when(hackerNews.sendAndGetHttpResponse(any())).thenReturn(httpResponse);
        when(hackerNews.getFieldPattern(anyString())).thenReturn(this.hackerNews.getFieldPattern(
            "type"));
        when(hackerNews.news(37570037)).thenCallRealMethod();
        assertThat(hackerNews.news(37570037)).isEqualTo("story");
    }

}
