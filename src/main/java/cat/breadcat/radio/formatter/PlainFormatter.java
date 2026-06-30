package cat.breadcat.radio.formatter;

import cat.breadcat.radio.core.LogRecord;

import java.time.format.DateTimeFormatter;

/**
 * Plain text {@link LogFormatter}
 *
 * <p>Formats {@link LogRecord} as:
 *   [TIMESTAMP] [CLASS] [LEVEL] TEXT</p>
 *
 * <p>This formatter is stateless and exposes a reusable singleton instance via {@link #INSTANCE}.</p>
 */
public final class PlainFormatter extends LogFormatter
{
    public static final PlainFormatter INSTANCE = new PlainFormatter();

    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss"
    );

    private PlainFormatter() {}


    @Override
    public String format(LogRecord record)
    {
        String time = record.timestamp().format(TIMESTAMP_FORMATTER);
        String className = record.className();
        String level = record.level().name();
        String text = record.text();

        return "[" + time + "] ["
                + className + "] ["
                + level + "] "
                + text;
    }
}
