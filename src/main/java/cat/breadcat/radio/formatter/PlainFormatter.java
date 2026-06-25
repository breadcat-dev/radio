package cat.breadcat.radio.formatter;

import cat.breadcat.radio.core.LogRecord;

import java.time.format.DateTimeFormatter;

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
        String clazz = record.clazz();
        String level = record.level().name();
        String text = record.text();

        return "[" + time + "] ["
                + clazz + "] ["
                + level + "] "
                + text;
    }
}
