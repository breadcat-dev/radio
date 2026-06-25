package cat.breadcat.radio.core;

import cat.breadcat.radio.level.DefaultLogLevel;
import cat.breadcat.radio.level.LogLevel;
import cat.breadcat.radio.sink.LogSink;
import cat.breadcat.toolbox.StringUtil;

import java.time.LocalDateTime;
import java.util.List;

public final class Logger
{
    private final String clazz;
    private final List<LogSink> sinks;
    private final int minimum;

    public Logger(Class<?> clazz, List<LogSink> sinks, int minimum)
    {
        this.clazz = clazz.getSimpleName();
        this.sinks = sinks;
        this.minimum = minimum;
    }


    public void log(LogLevel level, Object... args)
    {
        if(level.priority() < minimum)
            return;

        String text = StringUtil.join(args);
        LogRecord record = new LogRecord(LocalDateTime.now(), clazz, level, text);

        for(LogSink sink : sinks)
        {
            sink.log(record);
        }
    }


    public void info(Object... args)
    {
        log(DefaultLogLevel.INFO, args);
    }

    public void ok(Object... args)
    {
        log(DefaultLogLevel.OK, args);
    }

    public void warn(Object... args)
    {
        log(DefaultLogLevel.WARN, args);
    }

    public void error(Object... args)
    {
        log(DefaultLogLevel.ERROR, args);
    }

    public void raw(String text)
    {
        for(LogSink sink : sinks)
        {
            sink.raw(text);
        }
    }
}
