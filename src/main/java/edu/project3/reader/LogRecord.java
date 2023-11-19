package edu.project3.reader;

import java.time.OffsetDateTime;

public record LogRecord(String ipAddress,
                        OffsetDateTime timestamp, String request, int statusCode, long responseSize,
                        String referer,
                        String userAgent) {

}
