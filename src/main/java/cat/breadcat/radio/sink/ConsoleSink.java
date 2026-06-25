package cat.breadcat.radio.sink;

import cat.breadcat.radio.core.LogRecord;
import cat.breadcat.radio.formatter.LogFormatter;

public final class ConsoleSink extends LogSink
{
    public ConsoleSink(LogFormatter formatter)
    {
        super(formatter, System.out);
    }


    @Override
    public void log(LogRecord record)
    {
        out.println(format(record));
    }
}
