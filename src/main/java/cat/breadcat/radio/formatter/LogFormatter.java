package cat.breadcat.radio.formatter;

import cat.breadcat.radio.core.LogRecord;

public interface LogFormatter
{
    String format(LogRecord record);
}
