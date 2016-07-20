package drone.API.impl.v1;

import drone.API.impl.algorithm.v1.NeighborhoodsAlgorithm;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import drone.test.helper.TestHelper;
import drone.mock.API.IDealistaAPI;
import drone.API.INeighborhoodsAlgorithm;
import drone.API.IDroneAPI;
import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.mock.API.IUrbanizationID;
import drone.mock.exception.DuplicatedAdjacentNode;
import drone.mock.exception.NodeAlreadyAdded;
import drone.mock.exception.NodeAlreadyAddedAsAdjacent;
import drone.mock.exception.NodeNotFound;
import drone.API.impl.DroneAPI;

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
        IDroneAPI testDrone = new DroneAPI(api, algorithm);

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
        IDroneAPI testDrone = new DroneAPI(api, algorithm);
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
        IDroneAPI testDrone = new DroneAPI(api, algorithm);
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
        IDroneAPI testDrone = new DroneAPI(api, algorithm);
        
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
        IDroneAPI testDrone = new DroneAPI(api, algorithm);

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
        IDroneAPI testDrone = new DroneAPI(api, algorithm);

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
        IDroneAPI testDrone = new DroneAPI(api, algorithm);

        System.out.println("Test 7");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);
        
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);
    }
    
    
    @Test
    /**
     * InputNode (0,1) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0) 
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     */
    public void testGetNeighborhoods8() throws Exception {
        // Input node coords
        int x = 0;
        int y = 1;
        int range = 1;
        // urbanizationMatrix dimensions
        int width = 3;
        int height = 3;
        
        
        List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>() {
            {
                add(helper.getId(1));
                add(helper.getId(2));
                add(helper.getId(5));
                add(helper.getId(8));
                add(helper.getId(7));
            }
        };

        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        IDroneAPI testDrone = new DroneAPI(api, algorithm);

        System.out.println("Test 8");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);
        
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);
    }
    
    @Test
    /**
     * InputNode (1,0) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0) 
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     */
    public void testGetNeighborhoods9() throws Exception {
        // Input node coords
        int x = 1;
        int y = 0;
        int range = 1;
        // urbanizationMatrix dimensions
        int width = 3;
        int height = 3;
        
        
        List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>() {
            {
                add(helper.getId(3));
                add(helper.getId(6));
                add(helper.getId(5));
                add(helper.getId(4));
                add(helper.getId(1));
            }
        };

        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        IDroneAPI testDrone = new DroneAPI(api, algorithm);

        System.out.println("Test 9");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);
        
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);
    }
    
    
    @Test
    /**
     * InputNode (2,1) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0) 
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     */
    public void testGetNeighborhoods10() throws Exception {
        // Input node coords
        int x = 2;
        int y = 1;
        int range = 1;
        // urbanizationMatrix dimensions
        int width = 3;
        int height = 3;
        
        
        List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>() {
            {
                add(helper.getId(2));
                add(helper.getId(3));
                add(helper.getId(9));
                add(helper.getId(8));
                add(helper.getId(5));
            }
        };

        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        IDroneAPI testDrone = new DroneAPI(api, algorithm);

        System.out.println("Test 10");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);
        
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);
    }
    
    @Test
    /**
     * InputNode (1,2) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0) 
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     */
    public void testGetNeighborhoods11() throws Exception {
        // Input node coords
        int x = 1;
        int y = 2;
        int range = 1;
        // urbanizationMatrix dimensions
        int width = 3;
        int height = 3;
        
        
        List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>() {
            {
                add(helper.getId(4));
                add(helper.getId(5));
                add(helper.getId(6));
                add(helper.getId(9));
                add(helper.getId(7));
            }
        };

        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        IDroneAPI testDrone = new DroneAPI(api, algorithm);

        System.out.println("Test 11");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);
        
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);
    }
    

    @Test
    /**
     * InputNode (4,4) range: 3
     * 01(0.0,0.0)  02(1.0,0.0)  03(2.0,0.0)  04(3.0,0.0)  05(4.0,0.0)  06(5.0,0.0)  07(6.0,0.0)            
     * 08(0.0,1.0)  09(1.0,1.0)  10(2.0,1.0)  11(3.0,1.0)  12(4.0,1.0)  13(5.0,1.0)  14(6.0,1.0)            
     * 15(0.0,2.0)  16(1.0,2.0)  17(2.0,2.0)  18(3.0,2.0)  19(4.0,2.0)  20(5.0,2.0)  21(6.0,2.0)            
     * 22(0.0,3.0)  23(1.0,3.0)  24(2.0,3.0)  25(3.0,3.0)  26(4.0,3.0)  27(5.0,3.0)  28(6.0,3.0)            
     * 29(0.0,4.0)  30(1.0,4.0)  31(2.0,4.0)  32(3.0,4.0)  33(4.0,4.0)  34(5.0,4.0)  35(6.0,4.0)            
     * 36(0.0,5.0)  37(1.0,5.0)  38(2.0,5.0)  39(3.0,5.0)  40(4.0,5.0)  41(5.0,5.0)  42(6.0,5.0)            
     * 43(0.0,6.0)  44(1.0,6.0)  45(2.0,6.0)  46(3.0,6.0)  47(4.0,6.0)  48(5.0,6.0)  49(6.0,6.0)            
     */
    public void testGetNeighborhoods12() throws Exception {
        // Input node coords
        int x = 4;
        int y = 4;
        int range = 3;
        // urbanizationMatrix dimensions
        int width = 7;
        int height = 7;
        
        List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>() {
            {
                add(helper.getId(9));add(helper.getId(10));add(helper.getId(11));
                add(helper.getId(12));add(helper.getId(13));add(helper.getId(14));
                
                add(helper.getId(44));
                
                add(helper.getId(37));add(helper.getId(30));add(helper.getId(23));
                add(helper.getId(16));
                
            }
        };
        
        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        IDroneAPI testDrone = new DroneAPI(api, algorithm);
        
        System.out.println("Test 12");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);

         List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
         
        assertEquals(expected, actual);
    }

    @Test(expected=NeighborhoodsAlgorithmEx.class)
    /**
     * InputNode (3,3) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0) 
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     * Expeted exception Oops, something went wrong!! Starting Node not found
     * And an empty lists
     */
    public void testGetNeighborhoods13() throws Exception {
        // Input node coords
        int x = 3;
        int y = 3;
        int range = 1;
        // urbanizationMatrix dimensions
        int width = 3;
        int height = 3;
        
        
        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        IDroneAPI testDrone = new DroneAPI(api, algorithm);

        System.out.println("Test 13");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);
        
        testDrone.getNeighborhoods(x, y, range);
    }
    
    
    @Test
    /**
     * Matrix with  width != height
     * InputNode (2,1) range: 1
     * 
     * 01(0.0,0.0)  02(1.0,0.0)  03(2.0,0.0)  04(3.0,0.0)  05(4.0,0.0)  06(5.0,0.0)            
     * 07(0.0,1.0)  08(1.0,1.0)  09(2.0,1.0)  10(3.0,1.0)  11(4.0,1.0)  12(5.0,1.0)            
     * 13(0.0,2.0)  14(1.0,2.0)  15(2.0,2.0)  16(3.0,2.0)  17(4.0,2.0)  18(5.0,2.0)
     * 
     */
    public void testGetNeighborhoods14() throws Exception {
        // Input node coords
        int x = 2;
        int y = 1;
        int range = 1;
        // urbanizationMatrix dimensions
        int width = 6;
        int height = 3;
        
        
        List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>() {
            {
                add(helper.getId(2));add(helper.getId(3));add(helper.getId(4));
                
                add(helper.getId(10));add(helper.getId(16));
                
                add(helper.getId(15));add(helper.getId(14));
                
                add(helper.getId(8));
                
            }
        };

        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        IDroneAPI testDrone = new DroneAPI(api, algorithm);

        System.out.println("Test 14");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);
        
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);
    }
    
    
    @Test
    /**
     * Matrix with  width != height
     * InputNode (2,1) range: 2
     * 
     * 01(0.0,0.0)  02(1.0,0.0)  03(2.0,0.0)  04(3.0,0.0)  05(4.0,0.0)  06(5.0,0.0)            
     * 07(0.0,1.0)  08(1.0,1.0)  09(2.0,1.0)  10(3.0,1.0)  11(4.0,1.0)  12(5.0,1.0)            
     * 13(0.0,2.0)  14(1.0,2.0)  15(2.0,2.0)  16(3.0,2.0)  17(4.0,2.0)  18(5.0,2.0)
     * 
     */
    public void testGetNeighborhoods15() throws Exception {
        // Input node coords
        int x = 2;
        int y = 1;
        int range = 2;
        // urbanizationMatrix dimensions
        int width = 6;
        int height = 3;
        
        
        List<IUrbanizationID> expected = new ArrayList<IUrbanizationID>() {
            {
                add(helper.getId(5));add(helper.getId(11));add(helper.getId(17));
                
                add(helper.getId(13));add(helper.getId(7));add(helper.getId(1));
                
            }
        };

        IDealistaAPI api = helper.getIdealistaAPI(width, height);
        IDroneAPI testDrone = new DroneAPI(api, algorithm);

        System.out.println("Test 15");
        System.out.println("InputNode ("+x+","+y+") range: "+range);
        // Print the matrix
        System.out.println(api);
        
        List<IUrbanizationID> actual = testDrone.getNeighborhoods(x, y, range);
        assertEquals(expected, actual);
    }
    
    
    //@Test
    /**
     * InputNode (0,0) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0) 
     */
    /*public void testGetNeighborhoods14() throws Exception {
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
     * InputNode (0,0) range: 1
     * 01(0.0,0.0) 
     * 02(0.0,1.0) 
     * 03(0.0,2.0) 
     */
    /*public void testGetNeighborhoods15() throws Exception {
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
