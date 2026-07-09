package cat.breadcat.radio.sink;

import cat.breadcat.radio.core.LogRecord;
import cat.breadcat.radio.formatter.LogFormatter;

import java.io.PrintStream;

public interface LogSink
{
    void raw(String text);
    default void log(LogRecord record) {}
}
