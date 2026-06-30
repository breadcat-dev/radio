package cat.breadcat.radio.core;

import cat.breadcat.radio.level.DefaultLogLevel;
import cat.breadcat.radio.level.LogLevel;
import cat.breadcat.radio.sink.LogSink;
import cat.breadcat.toolbox.utils.StringUtil;

import java.time.LocalDateTime;
import java.util.List;


/**
 * The core Logger class.
 *
 * @author Bread Cat
 * @since 1.0.0
 */
public final class Logger
{
    private final String className;
    private final List<LogSink> sinks;
    private final int minimum;

    public Logger(Class<?> clazz, List<LogSink> sinks, int minimum)
    {
        this.className = clazz.getSimpleName();
        this.sinks = sinks;
        this.minimum = minimum;
    }


    /**
     Logs a message using the specified Log Level.

     <p>If the {@link LogLevel}'s priority is lower than the configured minimum level,
     the message is ignored.</p>

     <p>The provided arguments are joined into a single {@link String} and wrapped
     into a {@link LogRecord}, which is then dispatched to all registered {@link LogSink}.</p>

     @param level the severity level of the log entry
     @param args the message parts to be joined into a single log message
     @since 1.0.0
     */
    public void log(LogLevel level, Object... args)
    {
        if(level.priority() < minimum)
            return;

        String text = StringUtil.join(args);
        LogRecord record = new LogRecord(LocalDateTime.now(), className, level, text);

        for(LogSink sink : sinks)
        {
            sink.log(record);
        }
    }

    /**
     Delegates to {@link #log(LogLevel, Object...)} with the INFO {@link LogLevel}.

     @param args the message parts to be joined into a single log message
     @since 1.0.0
     */
    public void info(Object... args)
    {
        log(DefaultLogLevel.INFO, args);
    }

    /**
     Delegates to {@link #log(LogLevel, Object...)} with the OK {@link LogLevel}.

     @param args the message parts to be joined into a single log message
     @since 1.0.0
     */
    public void ok(Object... args)
    {
        log(DefaultLogLevel.OK, args);
    }

    /**
     Delegates to {@link #log(LogLevel, Object...)} with the WARN {@link LogLevel}.

     @param args the message parts to be joined into a single log message
     @since 1.0.0
     */
    public void warn(Object... args)
    {
        log(DefaultLogLevel.WARN, args);
    }

    /**
     Delegates to {@link #log(LogLevel, Object...)} with the ERROR {@link LogLevel}.

     @param args the message parts to be joined into a single log message
     @since 1.0.0
     */
    public void error(Object... args)
    {
        log(DefaultLogLevel.ERROR, args);
    }

    /**
     Logs a raw unformatted message, bypassing log level filtering and formatting.

     <p>The provided {@link String} is dispatched to all registered sinks.</p>

     @param text the text message
     @since 1.0.0
     */
    public void raw(String text)
    {
        for(LogSink sink : sinks)
        {
            sink.raw(text);
        }
    }
}
