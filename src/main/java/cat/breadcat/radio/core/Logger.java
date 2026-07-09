package cat.breadcat.radio.core;

import cat.breadcat.radio.level.DefaultLogLevels;
import cat.breadcat.radio.level.LogLevel;
import cat.breadcat.radio.sink.LogSink;
import cat.breadcat.toolbox.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public final class Logger implements AutoCloseable
{
    private final String className;
    private final List<LogSink> sinks;
    private final int minimum;

    public Logger(Class<?> clazz, List<LogSink> sinks, int minimum)
    {
        this.className = clazz.getSimpleName();
        this.sinks = List.copyOf(sinks);
        this.minimum = minimum;
    }


    public static LoggerBuilder builder(Class<?> clazz)
    {
        return new LoggerBuilder(clazz);
    }


    public void log(LogLevel level, Object... args)
    {
        if(level.priority() < minimum)
            return;

        String text;
        if(args.length == 1)
            text = args[0].toString();
        else
            text = StringUtils.join(" ", args);
        LogRecord record = new LogRecord(LocalDateTime.now(), className, level, text);

        for(LogSink sink : sinks)
        {
            sink.log(record);
        }
    }

    public void info(Object... args)
    {
        log(DefaultLogLevels.INFO, args);
    }
    public void ok(Object... args)
    {
        log(DefaultLogLevels.OK, args);
    }
    public void warn(Object... args)
    {
        log(DefaultLogLevels.WARN, args);
    }
    public void error(Object... args)
    {
        log(DefaultLogLevels.ERROR, args);
    }
    public void raw(String text)
    {
        for(LogSink sink : sinks)
        {
            sink.raw(text);
        }
    }

    @Override
    public void close() throws Exception
    {
        for (LogSink sink : sinks)
        {
            if(sink instanceof AutoCloseable c)
                c.close();
        }
    }
}
