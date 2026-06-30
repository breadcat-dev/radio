package cat.breadcat.radio.core;

import cat.breadcat.radio.formatter.ColoredFormatter;
import cat.breadcat.radio.formatter.PlainFormatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class TestLogger
{
    private final static Logger LOGGER = new LoggerBuilder(TestLogger.class)
            .console(PlainFormatter.INSTANCE)
            .build();

    @Test
    void shouldThrowWhenNoSinkProvided()
    {
        assertThrows(IllegalStateException.class, () ->  {
            new LoggerBuilder(TestLogger.class)
                    .build();
        });
    }

    @Test
    void shouldNotReturnNull()
    {
        assertNotNull(new LoggerBuilder(TestLogger.class)
                .console(ColoredFormatter.INSTANCE)
                .build());
    }

    @Test
    void shouldNotThrow()
    {
        assertDoesNotThrow(() -> LOGGER.info("hi"));
    }
}
