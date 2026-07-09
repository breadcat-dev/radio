package cat.breadcat.radio.formatter;

import cat.breadcat.radio.core.LogRecord;
import cat.breadcat.toolbox.constant.AnsiConstants;

import java.time.format.DateTimeFormatter;

public final class ColoredFormatter implements LogFormatter
{
    public static final ColoredFormatter INSTANCE = new ColoredFormatter();

    private static final String TIMESTAMP_COLOR = AnsiConstants.CYAN;
    private static final String CLASS_COLOR = AnsiConstants.MAGENTA;
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss"
    );

    private ColoredFormatter() {}


    @Override
    public String format(LogRecord record)
    {
        String time = TIMESTAMP_COLOR + record.timestamp().format(TIMESTAMP_FORMATTER) + AnsiConstants.RESET;
        String className = CLASS_COLOR + record.className() + AnsiConstants.RESET;
        String level = record.level().color() + record.level().name() + AnsiConstants.RESET;
        String text = record.text();

        return "[" + time + "] [" +
                className + "] [" +
                level + "] " +
                text;
    }
}
