import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.junit.Assert.assertEquals;

/**
 * Created by ableasdale on 26/05/2017.
 */
public class TestNQuadLoading {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


   /* @Before
    public void setUp() throws Exception
    {
        LOG.info("SETUP");
    }*/

    @Test
    public void testHelloEmpty()
    {
        LOG.info("doing some testing");
        assertEquals(3, 1+4);
    }

    @Test
    public void testHelloWorld()
    {

        assertEquals(2, 1+1);

    }
}

/*
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FirstJUnit5Tests {

    @Test
    void myFirstTest() {
        assertEquals(2, 1 + 1);
    }

}
 */