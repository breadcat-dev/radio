package cat.breadcat.radio.core;

import cat.breadcat.radio.formatter.LogFormatter;
import cat.breadcat.radio.sink.ConsoleSink;
import cat.breadcat.radio.sink.FileSink;
import cat.breadcat.radio.sink.LogSink;
import cat.breadcat.toolbox.utils.FileUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class LoggerBuilder
{
    private final Class<?> clazz;
    private final List<LogSink> sinks;
    private int minimum;

    public LoggerBuilder(Class<?> clazz)
    {
        this.clazz = clazz;
        this.sinks = new ArrayList<>();
        this.minimum = 0;
    }


    public LoggerBuilder sink(LogSink sink)
    {
        this.sinks.add(sink);

        return this;
    }

    public LoggerBuilder minimum(int minimum)
    {
        this.minimum = minimum;

        return this;
    }

    public LoggerBuilder console(LogFormatter formatter)
    {
        return sink(new ConsoleSink(formatter));
    }

    public LoggerBuilder file(LogFormatter formatter, Path path)
    {
        try
        {
            FileUtil.createDirectories(path);

            PrintStream out = new PrintStream(
                    new FileOutputStream(
                            path.toFile(),
                            true
                    ),
                    true
            );

            return sink(new FileSink(formatter, out));
        }
        catch (IOException e)
        {
            throw new RuntimeException("Failed to create Log File: " + path, e);
        }
    }


    public Logger build()
    {
        if(sinks.isEmpty())
            throw new IllegalStateException("Logger must have at least 1 Sink");

        return new Logger(clazz, sinks, minimum);
    }
}
