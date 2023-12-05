package edu.project3.args;

public enum FormatReport {

    MARKDOWN("markdown", ".md"), ADOC("adoc", ".adoc"), OTHER("", "");
    private final String format;
    private final String glob;

    FormatReport(String format, String glob) {
        this.format = format;
        this.glob = glob;
    }

    public String getFormat() {
        return format;
    }

    public String getGlob() {
        return glob;
    }
}
