package cat.breadcat.radio.sink;

import cat.breadcat.radio.core.LogRecord;
import cat.breadcat.radio.formatter.LogFormatter;

import java.io.PrintStream;

public final class FileSink implements LogSink, AutoCloseable
{
    private final LogFormatter formatter;
    private final PrintStream out;

    public FileSink(LogFormatter formatter, PrintStream out)
    {
        this.formatter = formatter;
        this.out = out;
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

    @Override
    public void close()
    {
        out.close();
    }
}
