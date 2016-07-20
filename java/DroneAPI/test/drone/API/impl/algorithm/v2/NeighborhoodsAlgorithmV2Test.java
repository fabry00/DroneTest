package drone.API.impl.algorithm.v2;

import drone.API.INeighborhoodsAlgorithm;
import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.test.helper.TestCase;
import drone.test.helper.TestCaseBuilder;
import drone.test.helper.TestHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void testNeighborhoodsV2_1() throws Exception {
        TestCase test = testBuilder.testCase1();
        executeTest(test);
    }

    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_2() throws Exception {
        TestCase test = testBuilder.testCase2();
        executeTest(test);
    }

    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_3() throws Exception {
        TestCase test = testBuilder.testCase3();
        executeTest(test);
    }

    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_4() throws Exception {
        TestCase test = testBuilder.testCase4();
        executeTest(test);
    }

    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_5() throws Exception {
        TestCase test = testBuilder.testCase5();
        executeTest(test);
    }

    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_6() throws Exception {
        TestCase test = testBuilder.testCase6();
        executeTest(test);
    }

    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_7() throws Exception {
        TestCase test = testBuilder.testCase7();
        executeTest(test);
    }

    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_8() throws Exception {
        TestCase test = testBuilder.testCase8();
        executeTest(test);
    }

    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_9() throws Exception {
        TestCase test = testBuilder.testCase9();
        executeTest(test);
    }
    
    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_10() throws Exception {
        TestCase test = testBuilder.testCase10();
        executeTest(test);
    }
    
    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_11() throws Exception {
        TestCase test = testBuilder.testCase11();
        executeTest(test);
    }
    
    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_12() throws Exception {
        TestCase test = testBuilder.testCase12();
        executeTest(test);
    }
    
    /**
     * @throws java.lang.Exception
     */
    @Test(expected = NeighborhoodsAlgorithmEx.class)
    public void testNeighborhoodsV2_13() throws Exception {
        TestCase test = testBuilder.testCase13();
        executeTest(test);
    }
    
    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_14() throws Exception {
        TestCase test = testBuilder.testCase14();
        executeTest(test);
    }
    
    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_15() throws Exception {
        TestCase test = testBuilder.testCase15();
        executeTest(test);
    }
    
    
    /**
     * @throws java.lang.Exception
     */
    @Test
    public void testNeighborhoodsV2_16() throws Exception {
        TestCase test = testBuilder.testCase16();
        executeTest(test);
    }

}
