# Radio

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
![Java](https://img.shields.io/badge/Java-21-blue)
![Status](https://img.shields.io/badge/status-experimental-orange)
![Release](https://img.shields.io/github/v/release/breadcat-dev/radio)

> Flexible logging library for Java

Part of the TANK Series.

---

## Features

- Configurable log levels
- Multiple output sinks
- Custom formatters
- Builder-based configuration
- Console and file logging by default

## Design Goals

- Extensible
- Clean API
- Fast

---

## Installation

Currently, Radio is not on Maven Central.
To use it, clone the repository and publish it to your local Maven Repository.


```sh
git clone https://github.com/breadcat-dev/radio.git
cd radio
```

### Linux / MacOS
```sh
./gradlew publishToMavenLocal
```
### Windows
```sh
./gradlew.bat publishToMavenLocal
```

Once installed, add the dependency:

### Groovy
```gradle
implementation "cat.breadcat:radio:<version>"
```

### Kotlin DSL
```gradle
implementation("cat.breadcat:radio:<version>")
```

---

## Quick Example

```java
Logger logger = new LoggerBuilder(Main.class)
        .console(ColoredFormatter.INSTANCE)
        .build();

logger.info("hello");
```

> [2026-06-30 16:20:31] [Main] [INFO] hellooo

---

## Advanced Examples

### Console only
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

### Console + File
```java
private static final Logger LOGGER = new LoggerBuilder(ChatPacketHandler.class)
        .console(ColoredFormatter.INSTANCE)
        .file(PlainFormatter.INSTANCE, Path.of("./chat.log"))
        .build();

public void handle(ChatPacket packet)
{
    if(packet == null)
        return;

    LOGGER.info(packet.text());

    // ...
}
```

### Custom Log Levels
```java
public final class NetworkLogLevel
{
    private NetworkLogLevel() {}
    
    public static final LogLevel PACKET_IN = new LogLevel("PACKET_IN", 0, AnsiUtil.YELLOW);
    public static final LogLevel PACKET_OUT = new LogLevel("PACKET_OUT", 0, AnsiUtil.YELLOW);
    public static final LogLevel CONNECTION = new LogLevel("CONNECTION", 2, AnsiUtil.YELLOW);
    public static final LogLevel SESSION = new LogLevel("SESSION", 2, AnsiUtil.YELLOW);
}
```

### Custom Wrapper
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

### Custom Formatter
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
        /* String time = record.timestamp().format(TIMESTAMP_FORMATTER);
        String clazz = record.clazz(); */
        String level = record.level().name();
        String text = record.text();

        return "[" + level + "] "
                + text;
    }
}
```

### Custom Sink

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

### Usage

```java
final Logger LOGGER = new LoggerBuilder(Main.class)
        .sink(new ErrorSink(SimplePlainFormatter.INSTANCE))
        .build();
```

---

## Performance

| Benchmark                                    | Mode | Cnt | Score      | Error   | Units |
|----------------------------------------------|------|-----|------------|---------|-------|
| BenchmarkLogger.initializationConsole        | avgt | 3   | 1.080 ±    | 0.058   | ns/op |
| BenchmarkLogger.initializationConsoleAndFile | avgt | 3   | 3177.254 ± | 317.104 | ns/op |
| BenchmarkLogger.logInfo                      | avgt | 3   | 61.724 ±   | 1.763   | ns/op |
| BenchmarkLogger.logInfoManually              | avgt | 3   | 63.721 ±   | 1.116   | ns/op |

> Results measured using JMH (AverageTime, 3 iterations, 2 warmup iterations, OpenJDK 21)

[ ! ] Benchmark note: `.logInfo()` and `.logInfoManually` use a no-op sink to isolate logging dispatch cost (no IO involved).
```java
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
```

---

## Dependencies:
- Toolbox - [Github](https://github.com/breadcat-dev/toolbox)