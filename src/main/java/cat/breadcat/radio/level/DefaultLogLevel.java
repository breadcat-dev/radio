package cat.breadcat.radio.level;

import cat.breadcat.toolbox.util.AnsiUtil;

public final class DefaultLogLevel
{
    private DefaultLogLevel() {}


    public static final LogLevel INFO = new LogLevel("INFO", 1, AnsiUtil.BLUE);
    public static final LogLevel OK = new LogLevel("OK", 1, AnsiUtil.GREEN);
    public static final LogLevel WARN = new LogLevel("WARN", 2, AnsiUtil.YELLOW);
    public static final LogLevel ERROR = new LogLevel("ERROR", 3, AnsiUtil.RED);
}
