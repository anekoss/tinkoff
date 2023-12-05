package edu.project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LogFileAnalyzerTest {
    @Test
    void adocReportTest() throws IOException {
        String[] args =
            new String[] {"--path", "src/main/resources/project3/testLog/logs.txt", "--from", "2015-06-04", "--format",
                "adoc"};
        LogFileAnalyzer logAnalyze = new LogFileAnalyzer(args);
        assertThat(Files.readString(logAnalyze.run(args))).isEqualTo(Files.readString(Path.of(
            "src/main/resources/project3/report/report.adoc")));
    }

    @Test
    void mdReportTest() throws IOException {
        String[] args =
            new String[] {"--path", "src/main/resources/project3/testLog/logs.txt", "--to", "2015-06-04", "--format",
                "markdown"};
        LogFileAnalyzer logAnalyze = new LogFileAnalyzer(args);
        assertThat(Files.readString(logAnalyze.run(args))).isEqualTo(Files.readString(Path.of(
            "src/main/resources/project3/report/REPORT.md")));
    }
}
