package edu.project3.report;

import edu.project3.args.FormatReport;
import edu.project3.metrics.LogAnalyze;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.project3.report.CloneFile.cloneFile;

public class LogReport {
    private final static String FILE_NAME_MD = "src/main/resources/project3/REPORT";
    private final static String FILE_NAME_DOC = "src/main/resources/project3/report";
    private final FileParser fileParser;
    private final Path file;

    public LogReport(LogAnalyze logAnalyze, FormatReport format) {
        logAnalyze.getLogAnalyzeMetrics();
        this.fileParser = new FileParserBuilder().setFormatReport(format).setLogAnalyze(logAnalyze).build();
        this.file = createFileReport(format);
    }

    public Path createFileReport(FormatReport format) {
        if (format == FormatReport.MARKDOWN) {
            return createFile(FILE_NAME_MD, format);
        }
        if (format == FormatReport.ADOC) {
            return createFile(FILE_NAME_DOC, format);
        }
        return Path.of("");
    }

    public Path writeMetricsToFile() {
        try {
            return Files.write(file, fileParser.parseMetricLogsInfo());

        } catch (IOException e) {
            throw new IllegalArgumentException("Не удалось записать метрику в файл");
        }
    }

    private Path createFile(String fileName, FormatReport formatReport) {
        Path path = Path.of(fileName + formatReport.getGlob());
        try {
            if (Files.exists(path)) {
                path = cloneFile(path);
            } else {
                Files.createFile(path);
            }
            return path;
        } catch (IOException e) {
            throw new IllegalArgumentException("Не удалось создать файл отчета");
        }
    }
}

