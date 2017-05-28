package com.marklogic.support;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("A very very very special test case (DisplayNameTest)")
@Benchmark
@MarkLogic
class DisplayNameTest {

    @Test
    @DisplayName("Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces() {
        assertEquals(2, (1 + 1), "1 + 1 should equal 2");
    }

    @Test
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
        assertEquals(2, (1 + 1), "1 + 1 should equal 2");
    }

    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
        assertEquals(3, (1 + 2), "1 + 1 should equal 2");
    }

}

