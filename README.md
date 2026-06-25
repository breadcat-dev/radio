# Radio - Lightweight & Reliable logging library for Java
### Part of my TANK Series

## Status

EXPERIMENTAL - API might change in the future

## What it does
- Fast logging API
- Structured log levels
- Highly customizable
- Sink and formatter support

## Examples

Console only
```java
private static final Logger LOGGER = new LoggerBuilder(TestClass.class)
        .console(ColoredFormatter.INSTANCE)
        .build();

public void init()
{
  LOGGER.info("Initializing Test Class..");

  try
  {
    // ...
  }
  catch(IOException e)
  {
    LOGGER.error("Failed to open File \"" + filename + "\"", e);
    return;
  }

  LOGGER.ok("Initialization complete");
}
```

Console + File
```java
private static final Logger LOGGER = new LoggerBuilder(ChatPacketHandler.class)
        .console(ColoredFormatter.INSTANCE)
        .file(PlainFormatter.INSTANCE, Path.of("./chat.log"))
        .build();

// ...

public void handle(ChatPacket packet)
{
  if(packet == null)
    return;

  LOGGER.info(packet.text());

  // ...
}
```

Custom Log Levels
```java
public final class NetworkLogLevel
{
    public static final LogLevel PACKET_IN = new LogLevel("PACKET_IN", 0, AnsiUtil.YELLOW);
    public static final LogLevel PACKET_OUT = new LogLevel("PACKET_OUT", 0, AnsiUtil.YELLOW);
    public static final LogLevel CONNECTION = new LogLevel("CONNECTION", 2, AnsiUtil.YELLOW);
    public static final LogLevel SESSION = new LogLevel("SESSION", 2, AnsiUtil.YELLOW);
}
```

Custom Wrapper
```java
public final class NetworkLogger
{
    private final Logger logger;

    public NetworkLogger(Logger logger)
    {
        this.logger = logger;
    }



    public void connection(Connection connection, Object... args)
    {
        logger.log(
                NetworkLogLevel.CONNECTION,
                "[" + connection.getId() + "]", StringUtil.join(args)
        );
    }
}
```

Custom Formatter
```java
public final class SimplePlainFormatter extends LogFormatter
{
    public static final SimplePlainFormatter INSTANCE = new SimplePlainFormatter();

    /*private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern(
            "HH:mm:ss"
    );*/

    private SimplePlainFormatter() {}


    @Override
    public String format(LogRecord record)
    {
        // String time = record.timestamp().format(TIMESTAMP_FORMATTER);
        // String clazz = record.clazz();
        String level = record.level().name();
        String text = record.text();

        return "[" + level + "] "
                + text;
    }
}
```

Custom Sink
```java
public final class ErrorSink extends LogSink
{
    public ErrorSink(LogFormatter formatter)
    {
        super(formatter, System.err);
    }


    @Override
    public void log(LogRecord record)
    {
        out.println(format(record));
    }
}
```
(LogSink for reference)
```java
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
```

Usage
```java
final Logger LOGGER = new LoggerBuilder(Main.class)
        .sink(new ErrorSink(SimplePlainFormatter.INSTANCE))
        .build();
```

## Dependencies:
- Toolbox: `cat.breadcat:toolbox:[VERSION]` [Github](https://github.com/breadcat-dev/toolbox)
