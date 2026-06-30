package cat.breadcat.radio;

import cat.breadcat.radio.core.Logger;
import cat.breadcat.radio.core.LoggerBuilder;
import cat.breadcat.radio.formatter.ColoredFormatter;
import cat.breadcat.radio.formatter.PlainFormatter;
import cat.breadcat.radio.level.DefaultLogLevel;
import cat.breadcat.radio.sink.BenchmarkSink;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations = 2)
@State(Scope.Thread)
@Measurement(iterations = 3)
public class BenchmarkLogger
{
    private static final Logger _LOGGER = new LoggerBuilder(BenchmarkLogger.class)
            .sink(new BenchmarkSink(PlainFormatter.INSTANCE, System.out))
            .build();

    @Benchmark
    public void initializationConsole()
    {
        final Logger LOGGER = new LoggerBuilder(BenchmarkLogger.class)
                .console(ColoredFormatter.INSTANCE)
                .build();
    }

    @Benchmark
    public void initializationConsoleAndFile()
    {
        final Logger LOGGER = new LoggerBuilder(BenchmarkLogger.class)
                .console(ColoredFormatter.INSTANCE)
                .file(PlainFormatter.INSTANCE, Path.of("./debug.log"))
                .build();
    }

    @Benchmark
    public void logInfoManually(Blackhole bh)
    {
        _LOGGER.log(DefaultLogLevel.INFO, "hellooo");
        bh.consume(_LOGGER);
    }

    @Benchmark
    public void logInfo(Blackhole bh)
    {
        _LOGGER.info("hellooo");
        bh.consume(_LOGGER);
    }

    @Benchmark
    public void systemOutPrintln(Blackhole bh)
    {
        System.out.println("hellooo");
        bh.consume(_LOGGER);
    }
}
