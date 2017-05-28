package com.marklogic.support; /**
 * Created by ableasdale on 26/05/2017.
 */
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

@Benchmark
@MarkLogic
class FirstJUnit5Tests {

    @Test
    @DisplayName("A special test case")
    void myFirstTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    @DisplayName("Add operation test")
    @RepeatedTest(2)
    void addNumber(TestInfo testInfo) {

        assertEquals(2, (1 + 1), "1 + 1 should equal 2");
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(1); // change to 100 to fail test
        });
    }


}