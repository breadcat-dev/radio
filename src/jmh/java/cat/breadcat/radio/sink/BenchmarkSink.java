package cat.breadcat.radio.sink;

import cat.breadcat.radio.core.LogRecord;
import cat.breadcat.radio.formatter.LogFormatter;

import java.io.PrintStream;

public final class BenchmarkSink extends LogSink
{
    public BenchmarkSink(LogFormatter formatter, PrintStream out)
    {
        super(formatter, out);
    }

    @Override
    public void log(LogRecord record)
    {

    }
}
