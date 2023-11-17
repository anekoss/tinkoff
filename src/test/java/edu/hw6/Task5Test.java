package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class Task5Test {
    @Test
    void hackerNewsTopStoriesTest() throws InterruptedException {
        long[] result = new HackerNews().hackerNewsTopStories();
        assertThat(result.length).isLessThanOrEqualTo(500).isGreaterThan(0);
        assertThat(result).contains(38302903L);
        assertThat(result).contains(38305234);

    }

    @Test
    void newsTitleTest() throws InterruptedException {
        assertThat(new HackerNews().news(37570037)).isEqualTo("JDK 21 Release Notes");
    }

    @Test
    void newsTypeTest() throws InterruptedException {
        HackerNews hackerNews = Mockito.spy(HackerNews.class);
        when(hackerNews.getFieldPattern(anyString())).thenReturn(new HackerNews().getFieldPattern("type"));
        assertThat(hackerNews.news(37570037)).isEqualTo("story");
    }

}
