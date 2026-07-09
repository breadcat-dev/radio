package cat.breadcat.radio.level;

import cat.breadcat.toolbox.constant.AnsiConstants;

public final class DefaultLogLevels
{
    private DefaultLogLevels() {}

    public static final LogLevel INFO = new LogLevel("INFO", 1, AnsiConstants.BLUE);
    public static final LogLevel OK = new LogLevel("OK", 1, AnsiConstants.GREEN);
    public static final LogLevel WARN = new LogLevel("WARN", 2, AnsiConstants.YELLOW);
    public static final LogLevel ERROR = new LogLevel("ERROR", 3, AnsiConstants.RED);
}
