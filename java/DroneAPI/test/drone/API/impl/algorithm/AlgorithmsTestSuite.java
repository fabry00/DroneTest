package drone.API.impl.algorithm;
import drone.API.impl.algorithm.v1.NeighborhoodsAlgorithmTest;
import drone.API.impl.algorithm.v2.NeighborhoodsAlgorithmV2Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Algorithms Test suite
 * @author Fabrizio Faustinoni
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({NeighborhoodsAlgorithmTest.class, NeighborhoodsAlgorithmV2Test.class})
public class AlgorithmsTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
