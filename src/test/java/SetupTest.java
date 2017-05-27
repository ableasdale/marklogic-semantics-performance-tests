import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 26/05/2017.
 */
public class SetupTest {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @BeforeAll
    static void initAll() {
        System.out.println("do we see this?");
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
