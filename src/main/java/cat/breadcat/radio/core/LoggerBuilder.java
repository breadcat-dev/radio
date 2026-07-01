package cat.breadcat.radio.core;

import cat.breadcat.radio.formatter.LogFormatter;
import cat.breadcat.radio.sink.ConsoleSink;
import cat.breadcat.radio.sink.FileSink;
import cat.breadcat.radio.sink.LogSink;
import cat.breadcat.toolbox.util.FileIOUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder for {@link Logger}.
 *
 * <p>A builder requires at least one sink before calling {@link #build()}.</p>
 *
 * @author Bread Cat
 * @since 1.0.0
 */
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

    /**
     * Adds a {@link LogSink} to this {@link LoggerBuilder}.
     *
     * @return this {@link LoggerBuilder} for chaining
     */
    public LoggerBuilder sink(LogSink sink)
    {
        this.sinks.add(sink);

        return this;
    }

    /**
     * Sets the minimum {@link cat.breadcat.radio.level.LogLevel} priority required to be logged.
     *
     * <p>Recommended: 0</p>
     *
     * @param minimum the minimum log priority that will be set
     * @return {@link LoggerBuilder} for chaining methods
     */
    public LoggerBuilder minimum(int minimum)
    {
        this.minimum = minimum;

        return this;
    }

    /**
     * Adds a {@link ConsoleSink} using the provided {@link LogFormatter}.
     *
     * @return {@link LoggerBuilder} for chaining
     */
    public LoggerBuilder console(LogFormatter formatter)
    {
        return sink(new ConsoleSink(formatter));
    }

    /**
     * Adds a {@link FileSink} using the provided {@link LogFormatter}.
     *
     * <p>Creates missing parent directories automatically.</p>
     *
     * @return {@link LoggerBuilder} for chaining
     */
    public LoggerBuilder file(LogFormatter formatter, Path path)
    {
        try
        {
            FileIOUtil.createDirectories(path);

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


    /**
     * Builds the logger instance.
     *
     * @throws IllegalStateException if no sinks are configured
     */
    public Logger build()
    {
        if(sinks.isEmpty())
            throw new IllegalStateException("Logger must have at least 1 Sink");

        return new Logger(clazz, sinks, minimum);
    }
}
