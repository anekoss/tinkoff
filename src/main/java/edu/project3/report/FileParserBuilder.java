package edu.project3.report;

import edu.project3.args.FormatReport;
import edu.project3.metrics.LogAnalyze;

public class FileParserBuilder {
    private FormatReport formatReport;
    private LogAnalyze logAnalyze;

    FileParser build() {
        if (formatReport == FormatReport.ADOC) {
            return new AdocFileparser(logAnalyze);
        }
        return new MdFileParser(logAnalyze);
    }

    public FileParserBuilder setFormatReport(FormatReport formatReport) {
        this.formatReport = formatReport;
        return this;
    }

    public FileParserBuilder setLogAnalyze(LogAnalyze logAnalyse) {
        this.logAnalyze = logAnalyse;
        return this;
    }
}
