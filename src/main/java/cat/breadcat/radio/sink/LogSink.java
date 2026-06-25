package cat.breadcat.radio.sink;

import cat.breadcat.radio.core.LogRecord;
import cat.breadcat.radio.formatter.LogFormatter;

import java.io.PrintStream;

public abstract class LogSink
{
    protected final LogFormatter formatter;
    protected final PrintStream out;

    public LogSink(LogFormatter formatter, PrintStream out)
    {
        this.formatter = formatter;
        this.out = out;
    }


    protected String format(LogRecord record)
    {
        return formatter.format(record);
    }

    public void raw(String text)
    {
        out.println(text);
    }


    public abstract void log(LogRecord record);
}
