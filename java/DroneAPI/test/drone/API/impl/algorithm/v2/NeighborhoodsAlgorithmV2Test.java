package drone.API.impl.algorithm.v2;

import drone.API.INeighborhoodsAlgorithm;
import drone.mock.API.IDealistaAPI;
import drone.mock.API.IUrbanizationID;
import drone.test.helper.TestCase;
import drone.test.helper.TestCaseBuilder;
import drone.test.helper.TestHelper;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class NeighborhoodsAlgorithmV2Test {
    
    private static INeighborhoodsAlgorithm algorithm;
    private static TestHelper helper;
    private static TestCaseBuilder testBuilder;
    
    public NeighborhoodsAlgorithmV2Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        testBuilder = new TestCaseBuilder();
        helper = new TestHelper();
        algorithm = new NeighborhoodsAlgorithmV2();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    private void executeTest(TestCase test) throws Exception {
        helper.executeTest(test, algorithm);
    }

    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testGetNeighborhoods() throws Exception {
        TestCase test = testBuilder.testGetNeighborhoods1();
        executeTest(test);
    }
    
}
