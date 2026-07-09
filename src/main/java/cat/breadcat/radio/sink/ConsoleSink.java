package cat.breadcat.radio.sink;

import cat.breadcat.radio.core.LogRecord;
import cat.breadcat.radio.formatter.LogFormatter;

import java.io.PrintStream;

public final class ConsoleSink implements LogSink
{
    private final LogFormatter formatter;
    private final PrintStream out;

    public ConsoleSink(LogFormatter formatter)
    {
        this.formatter = formatter;
        this.out = System.out;
    }


    @Override
    public void log(LogRecord record)
    {
        out.println(formatter.format(record));
    }
    @Override
    public void raw(String text)
    {
        out.println(text);
    }
}
