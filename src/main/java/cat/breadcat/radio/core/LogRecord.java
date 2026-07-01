package cat.breadcat.radio.core;

import cat.breadcat.radio.level.LogLevel;

import java.time.LocalDateTime;

/**
 * Immutable log event created by {@link Logger} and dispatched to all {@link cat.breadcat.radio.sink.LogSink}s.
 */
public record LogRecord(
        LocalDateTime timestamp,
        String className,
        LogLevel level,
        String text
)
{}