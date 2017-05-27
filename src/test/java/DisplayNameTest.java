import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("A very very very special test case")
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
        assertEquals(2, (1 + 2), "1 + 1 should equal 2");
	}

}
// end::user_guide[]
