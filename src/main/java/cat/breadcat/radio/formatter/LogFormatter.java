package cat.breadcat.radio.formatter;

import cat.breadcat.radio.core.LogRecord;

public abstract class LogFormatter
{
    public abstract String format(LogRecord record);
}
