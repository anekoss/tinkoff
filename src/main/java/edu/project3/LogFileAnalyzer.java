package edu.project3;

import edu.project3.args.CommandLineArgsParser;
import edu.project3.args.FormatReport;
import edu.project3.metrics.LogAnalyze;
import edu.project3.reader.LogReader;
import edu.project3.report.LogReport;
import java.nio.file.Path;

public class LogFileAnalyzer {
    private final CommandLineArgsParser commandLineArgsParser;
    private final LogReader logReader;
    private final FormatReport formatReport;
    private final LogAnalyze logAnalyze;
    private final LogReport logReport;

    public LogFileAnalyzer(String[] args) {
        commandLineArgsParser = new CommandLineArgsParser(args);
        logReader = new LogReader(commandLineArgsParser);
        formatReport = logReader.getArgsRecord().format();
        logAnalyze = new LogAnalyze(logReader);
        logReport = new LogReport(logAnalyze, formatReport);
    }

    public LogFileAnalyzer(
        CommandLineArgsParser commandLineArgsParser,
        LogReader logReader,
        FormatReport formatReport,
        LogAnalyze logAnalyze,
        LogReport logReport
    ) {
        this.commandLineArgsParser = commandLineArgsParser;
        this.logReader = logReader;
        this.formatReport = formatReport;
        this.logAnalyze = logAnalyze;
        this.logReport = logReport;
    }

    public Path run(String[] args) {
        return logReport.writeMetricsToFile();
    }
}
