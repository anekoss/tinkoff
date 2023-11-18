package edu.project3;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record LogRecord(String ipAddress,
                        OffsetDateTime timestamp, String request, int statusCode, long responseSize,
                        String referer,
                        String userAgent) {
}
