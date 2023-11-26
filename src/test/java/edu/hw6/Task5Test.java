package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.io.IOException;
import java.net.http.HttpClient;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class Task5Test {
    private final HackerNews hackerNews = new HackerNews();

    @Test
    void hackerNewsTopStoriesTest() throws InterruptedException {
        long[] result = hackerNews.hackerNewsTopStories();
        assertThat(result.length).isLessThanOrEqualTo(500).isGreaterThan(0);
        assertThat(result).contains(38302903L);
        assertThat(result).contains(38305234);

    }

    @Test
    void newsTitleTest() throws InterruptedException {
        assertThat(hackerNews.news(37570037)).isEqualTo("JDK 21 Release Notes");
    }

    @Test
    void newsTypeTest() throws InterruptedException, IOException {
        HackerNews hackerNews = Mockito.mock(HackerNews.class);
        when(hackerNews.sendAndGetHttpResponse(any())).thenCallRealMethod();
        when(hackerNews.news(anyInt())).thenCallRealMethod();
        when(hackerNews.getFieldPattern(anyString())).thenReturn(this.hackerNews.getFieldPattern(
            "type"));
        assertThat(hackerNews.news(37570037)).isEqualTo("story");
    }

}
