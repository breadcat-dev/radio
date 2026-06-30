package cat.breadcat.radio.formatter;

import cat.breadcat.radio.core.LogRecord;

/**
 * Base abstract class for log message formatters.
 *
 * <p>Implementations must override {@link #format(LogRecord)} to define how log records are converted into strings.</p>
 */
public abstract class LogFormatter
{
    public abstract String format(LogRecord record);
}
