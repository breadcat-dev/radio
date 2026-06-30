package cat.breadcat.radio.formatter;

import cat.breadcat.radio.core.LogRecord;
import cat.breadcat.toolbox.utils.AnsiUtil;

import java.time.format.DateTimeFormatter;

/**
 * Colored text {@link LogFormatter}
 *
 * <p>Formats {@link LogRecord} as:
 *   [TIMESTAMP] [CLASS] [LEVEL] TEXT</p>
 *
 * <p>This formatter is stateless and exposes a reusable singleton instance via {@link #INSTANCE}.</p>
 */
public final class ColoredFormatter extends LogFormatter
{
    public static final ColoredFormatter INSTANCE = new ColoredFormatter();

    private static final String TIMESTAMP_COLOR = AnsiUtil.CYAN;
    private static final String CLASS_COLOR = AnsiUtil.MAGENTA;
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss"
    );

    private ColoredFormatter() {}


    @Override
    public String format(LogRecord record)
    {
        String time = TIMESTAMP_COLOR + record.timestamp().format(TIMESTAMP_FORMATTER) + AnsiUtil.RESET;
        String clazz = CLASS_COLOR + record.clazz() + AnsiUtil.RESET;
        String level = record.level().color() + record.level().name() + AnsiUtil.RESET;
        String text = record.text();

        return "[" + time + "] ["
                + clazz + "] ["
                + level + "] "
                + text;
    }
}
