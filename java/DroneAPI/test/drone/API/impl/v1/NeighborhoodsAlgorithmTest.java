package drone.API.impl.v1;

import drone.API.impl.v1.NeighborhoodsAlgorithm;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import drone.test.helper.TestHelper;
import drone.API.IDealistaAPI;
import drone.API.INeighborhoodsAlgorithm;
import drone.API.ITestDroneAPI;
import drone.API.IUrbanizationID;
import drone.API.exception.DuplicatedAdjacentNode;
import drone.API.exception.NodeAlreadyAdded;
import drone.API.exception.NodeAlreadyAddedAsAdjacent;
import drone.API.exception.NodeNotFound;
import drone.API.impl.TestDroneAPI;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class NeighborhoodsAlgorithmTest {

    
    private static INeighborhoodsAlgorithm algorithm;
    private static TestHelper helper;

    public NeighborhoodsAlgorithmTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NodeAlreadyAddedAsAdjacent,
            DuplicatedAdjacentNode, NodeAlreadyAdded, NodeNotFound {

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

    /**
     * Test of getNeighborhoods method, of class NeighborhoodsAlgorithm.
     * InputNode (2,2) range: 1
     * 01(0.0,0.0)  02(1.0,0.0)  03(2.0,0.0)  04(3.0,0.0)  05(4.0,0.0)            
     * 06(0.0,1.0)  07(1.0,1.0)  08(2.0,1.0)  09(3.0,1.0)  10(4.0,1.0)            
     * 11(0.0,2.0)  12(1.0,2.0)  13(2.0,2.0)  14(3.0,2.0)  15(4.0,2.0)            
     * 16(0.0,3.0)  17(1.0,3.0)  18(2.0,3.0)  19(3.0,3.0)  20(4.0,3.0)            
     * 21(0.0,4.0)  22(1.0,4.0)  23(2.0,4.0)  24(3.0,4.0)  25(4.0,4.0) 
     * @throws java.lang.Exception
     */
    @Test
    public void testGetNeighborhoods0() throws Exception {
        // Input node coords
        int x = 2;
        int y = 2;
        int range = 1;
        // urbanizationMatrix dimensions
        int width = 5;
        int height = 5;

        List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>() {
            {
                add(helper.getId(7));
                add(helper.getId(8));
                add(helper.getId(9));
                add(helper.getId(14));
                add(helper.getId(19));
                add(helper.getId(18));
                add(helper.getId(17));
                add(helper.getId(12));

            }
        };
        
        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        ITestDroneAPI testDrone = new TestDroneAPI(api, algorithm);

        System.out.println("Test 0");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        System.out.println(api);
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);
    }

    @Test
    /**
     * InputNode (2,2) range: 2
     * 01(0.0,0.0)  02(1.0,0.0)  03(2.0,0.0)  04(3.0,0.0)  05(4.0,0.0)            
     * 06(0.0,1.0)  07(1.0,1.0)  08(2.0,1.0)  09(3.0,1.0)  10(4.0,1.0)            
     * 11(0.0,2.0)  12(1.0,2.0)  13(2.0,2.0)  14(3.0,2.0)  15(4.0,2.0)            
     * 16(0.0,3.0)  17(1.0,3.0)  18(2.0,3.0)  19(3.0,3.0)  20(4.0,3.0)            
     * 21(0.0,4.0)  22(1.0,4.0)  23(2.0,4.0)  24(3.0,4.0)  25(4.0,4.0)
     */
    public void testGetNeighborhoods2() throws Exception {
        // Input node coords
        int x = 2;
        int y = 2;
        int range = 2;
        // urbanizationMatrix dimensions
        int width = 5;
        int height = 5;

        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        ITestDroneAPI testDrone = new TestDroneAPI(api, algorithm);
        List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>() {
            {
                add(helper.getId(1));
                add(helper.getId(2));
                add(helper.getId(3));
                add(helper.getId(4));
                add(helper.getId(5));
                add(helper.getId(10));
                add(helper.getId(15));
                add(helper.getId(20));
                add(helper.getId(25));
                add(helper.getId(24));
                add(helper.getId(23));
                add(helper.getId(22));
                add(helper.getId(21));
                add(helper.getId(16));
                add(helper.getId(11));
                add(helper.getId(6));
            }
        };
        
        System.out.println("Test 1");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        System.out.println(api);
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);
    }
    
    
    @Test
    /**
     * InputNode (2,2) range: 3
     * 01(0.0,0.0)  02(1.0,0.0)  03(2.0,0.0)  04(3.0,0.0)  05(4.0,0.0)            
     * 06(0.0,1.0)  07(1.0,1.0)  08(2.0,1.0)  09(3.0,1.0)  10(4.0,1.0)            
     * 11(0.0,2.0)  12(1.0,2.0)  13(2.0,2.0)  14(3.0,2.0)  15(4.0,2.0)            
     * 16(0.0,3.0)  17(1.0,3.0)  18(2.0,3.0)  19(3.0,3.0)  20(4.0,3.0)            
     * 21(0.0,4.0)  22(1.0,4.0)  23(2.0,4.0)  24(3.0,4.0)  25(4.0,4.0)
     */
    public void testGetNeighborhoods3() throws Exception {
        // Input node coords
        int x = 2;
        int y = 2;
        int range = 3;
        // urbanizationMatrix dimensions
        int width = 5;
        int height = 5;

        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        ITestDroneAPI testDrone = new TestDroneAPI(api, algorithm);
        List<IUrbanizationID> expected = new ArrayList<>();
        
        System.out.println("Test 1");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);
    }

    @Test
    /**
     * InputNode (0,0) range: 1
     * 01(0.0,0.0)  02(1.0,0.0)  03(2.0,0.0)            
     * 04(0.0,1.0)  05(1.0,1.0)  06(2.0,1.0)            
     * 07(0.0,2.0)  08(1.0,2.0)  09(2.0,2.0)
     */
    public void testGetNeighborhoods4() throws Exception {
        // Input node coords
        int x = 0;
        int y = 0;
        int range = 1;
        // urbanizationMatrix dimensions
        int width = 3;
        int height = 3;
        
        List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>() {
            {
                add(helper.getId(2));
                add(helper.getId(5));
                add(helper.getId(4));
            }
        };

        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        ITestDroneAPI testDrone = new TestDroneAPI(api, algorithm);
        
        System.out.println("Test 4");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);
        
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);

    }

    @Test
    /**
     * InputNode (2,0) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0) 
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     */
    public void testGetNeighborhoods5() throws Exception {
        // Input node coords
        int x = 2;
        int y = 0;
        int range = 1;
        // urbanizationMatrix dimensions
        int width = 3;
        int height = 3;
        
        
        List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>() {
            {
                add(helper.getId(6));
                add(helper.getId(5));
                add(helper.getId(2));
            }
        };

        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        ITestDroneAPI testDrone = new TestDroneAPI(api, algorithm);

        System.out.println("Test 5");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);
        
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);
    }
    
    @Test
    /**
     * InputNode (2,2) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0) 
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     */
    public void testGetNeighborhoods6() throws Exception {
        // Input node coords
        int x = 2;
        int y = 2;
        int range = 1;
        // urbanizationMatrix dimensions
        int width = 3;
        int height = 3;
        
        
        List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>() {
            {
                add(helper.getId(5));
                add(helper.getId(6));
                add(helper.getId(8));
            }
        };

        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        ITestDroneAPI testDrone = new TestDroneAPI(api, algorithm);

        System.out.println("Test 6");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);
        
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);
    }
    
    @Test
    /**
     * InputNode (0,2) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0) 
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     */
    public void testGetNeighborhoods7() throws Exception {
        // Input node coords
        int x = 0;
        int y = 2;
        int range = 1;
        // urbanizationMatrix dimensions
        int width = 3;
        int height = 3;
        
        
        List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>() {
            {
                add(helper.getId(4));
                add(helper.getId(5));
                add(helper.getId(8));
            }
        };

        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        ITestDroneAPI testDrone = new TestDroneAPI(api, algorithm);

        System.out.println("Test 7");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);
        
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);
    }
    

    /*@Test
    public void testGetNeighborhoods6() throws Exception {
        IDealistaAPI api = helper.getIdealistaAPI(7, 7);
        ITestDroneAPI testDrone = new TestDroneAPI(api, algorithm);

        testDrone.getNeighborhoods(4, 4, 3);

        fail();
    }

    @Test
    public void testGetNeighborhoods7() throws Exception {
        IDealistaAPI api = helper.getIdealistaAPI(6, 6);
        ITestDroneAPI testDrone = new TestDroneAPI(api, algorithm);

        testDrone.getNeighborhoods(2, 2, 3);

        fail();
    }*/
}
