import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Created by ableasdale on 26/05/2017.
 */
public class SetupTest {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    @DisplayName("Add operation test")
    @RepeatedTest(5)
    void addNumber(TestInfo testInfo) {

        assertEquals(2, (1 + 1), "1 + 1 should equal 2");
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }

    @BeforeAll
    static void initAll(){
        LOG.info("before all");
    }

    @BeforeEach
    void init() {
        LOG.info("before each");
    }

    @AfterEach
    void tearDown() {
        LOG.info("tear down each");
    }

    @AfterAll
    static void tearDownAll() {
        LOG.info("tear down all");
    }

}
