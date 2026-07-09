package cat.breadcat.radio.core;

import cat.breadcat.radio.level.LogLevel;

import java.time.LocalDateTime;

public record LogRecord(
        LocalDateTime timestamp,
        String className,
        LogLevel level,
        String text
)
{}