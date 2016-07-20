package drone.API.impl.algorithm.v1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import drone.test.helper.TestHelper;
import drone.API.INeighborhoodsAlgorithm;
import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.mock.exception.DuplicatedAdjacentNode;
import drone.mock.exception.NodeAlreadyAdded;
import drone.mock.exception.NodeAlreadyAddedAsAdjacent;
import drone.mock.exception.NodeNotFound;
import drone.test.helper.TestCase;
import drone.test.helper.TestCaseBuilder;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class NeighborhoodsAlgorithmTest {

    private static INeighborhoodsAlgorithm algorithm;
    private static TestHelper helper;
    private static TestCaseBuilder testBuilder;

    public NeighborhoodsAlgorithmTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NodeAlreadyAddedAsAdjacent,
            DuplicatedAdjacentNode, NodeAlreadyAdded, NodeNotFound {

        testBuilder = new TestCaseBuilder();
        helper = new TestHelper();
        algorithm = new NeighborhoodsAlgorithm();
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

    @Test
    public void testNeighborhoodsV1_1() throws Exception {

        TestCase test = testBuilder.testCase1();
        executeTest(test);
    }

    @Test
    public void testNeighborhoodsV1_2() throws Exception {

        TestCase test = testBuilder.testCase2();
        executeTest(test);
    }

    @Test
    public void testNeighborhoodsV1_3() throws Exception {
        TestCase test = testBuilder.testCase3();
        executeTest(test);
    }

    @Test
    public void testNeighborhoodsV1_4() throws Exception {
        TestCase test = testBuilder.testCase4();
        executeTest(test);
    }

    @Test
    public void testNeighborhoodsV1_5() throws Exception {
        TestCase test = testBuilder.testCase5();
        executeTest(test);
    }

    @Test
    public void testNeighborhoodsV1_6() throws Exception {
        TestCase test = testBuilder.testCase6();
        executeTest(test);
    }

    @Test
    public void testNeighborhoodsV1_7() throws Exception {
        TestCase test = testBuilder.testCase7();
        executeTest(test);
    }

    @Test
    public void testNeighborhoodsV1_8() throws Exception {
        TestCase test = testBuilder.testCase8();
        executeTest(test);
    }

    @Test
    public void testNeighborhoodsV1_9() throws Exception {
        TestCase test = testBuilder.testCase9();
        executeTest(test);
    }

    @Test

    public void testNeighborhoodsV1_10() throws Exception {
        TestCase test = testBuilder.testCase10();
        executeTest(test);
    }

    @Test
    public void testNeighborhoodsV1_11() throws Exception {
        TestCase test = testBuilder.testCase11();
        executeTest(test);
    }

    @Test

    public void testNeighborhoodsV1_12() throws Exception {
        TestCase test = testBuilder.testCase12();
        executeTest(test);
    }

    @Test(expected = NeighborhoodsAlgorithmEx.class)
    public void testNeighborhoodsV1_13() throws Exception {
        TestCase test = testBuilder.testCase13();
        executeTest(test);
    }

    @Test
    public void testNeighborhoodsV1_14() throws Exception {
        TestCase test = testBuilder.testCase14();
        executeTest(test);
    }

    @Test
    public void testNeighborhoodsV1_15() throws Exception {
        TestCase test = testBuilder.testCase15();
        executeTest(test);
    }

    //@Test
    /**
     * InputNode (0,0) range: 1 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0)
     */
    /*public void testNeighborhoodsV1_14() throws Exception {
     // Input node coords
     int x = 0;
     int y = 0;
     int range = 1;
     // urbanizationMatrix dimensions
     int width = 3;
     int height = 1;
        
        
     List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>(){
     {
     add(helper.getId(2));
     }
     };

     IDealistaAPI api = helper.getIdealistaAPI(width, height);
     ITestDroneAPI testDrone = new TestDroneAPI(api, algorithm);

     System.out.println("Test 14");
     System.out.println("InputNode ("+x+","+y+") range: "+range);
     // Print the matrix
     System.out.println(api);
        
     List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
     assertEquals(expected, actual);
     }*/
    //@Test
    /**
     * InputNode (0,0) range: 1 01(0.0,0.0) 02(0.0,1.0) 03(0.0,2.0)
     */
    /*public void testNeighborhoodsV1_15() throws Exception {
     // Input node coords
     int x = 0;
     int y = 0;
     int range = 1;
     // urbanizationMatrix dimensions
     int width = 1;
     int height = 3;
        
        
     List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>(){
     {
     add(helper.getId(2));
     }
     };

     IDealistaAPI api = helper.getIdealistaAPI(width, height);
     ITestDroneAPI testDrone = new TestDroneAPI(api, algorithm);

     System.out.println("Test 14");
     System.out.println("InputNode ("+x+","+y+") range: "+range);
     // Print the matrix
     System.out.println(api);
        
     List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
     assertEquals(expected, actual);
     }*/
}
