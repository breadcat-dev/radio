package cat.breadcat.radio.sink;

import cat.breadcat.radio.core.LogRecord;
import cat.breadcat.radio.formatter.LogFormatter;

import java.io.PrintStream;

public final class FileSink extends LogSink implements AutoCloseable
{
    public FileSink(LogFormatter formatter, PrintStream out)
    {
        super(formatter, out);
    }


    @Override
    public void log(LogRecord record)
    {
        out.println(format(record));
    }

    @Override
    public void close()
    {
        out.close();
    }
}
