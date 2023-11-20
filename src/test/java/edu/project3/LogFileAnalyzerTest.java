package edu.project3;

import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

public class LogFileAnalyzerTest {
    @Test
    void adocReportTest() {
        String[] args = new String[] {"--path", "testLog/logs.txt", "--from", "2015-06-04", "--format", "adoc"};
        LogFileAnalyzer logAnalyze = new LogFileAnalyzer(args);
        assertThat(logAnalyze.run(args)).isEqualTo(Path.of("report.adoc"));
    }

    @Test
    void mdReportTest() {
        String[] args = new String[] {"--path", "testLog/logs.txt", "--to", "2015-06-04", "--format", "markdown"};
        LogFileAnalyzer logAnalyze = new LogFileAnalyzer(args);
        assertThat(logAnalyze.run(args)).isEqualTo(Path.of("REPORT.md"));
    }
}
