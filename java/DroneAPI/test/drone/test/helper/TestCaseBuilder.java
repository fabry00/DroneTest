package drone.test.helper;

import drone.mock.API.IUrbanizationID;
import java.util.ArrayList;

public class TestCaseBuilder {

    private static int id = 0;
    private static final TestHelper helper = new TestHelper();

    /**
     * InputNode (2,2) range: 1
     * 01(0.0,0.0)  02(1.0,0.0)  03(2.0,0.0)  04(3.0,0.0)  05(4.0,0.0)            
     * 06(0.0,1.0)  07(1.0,1.0)  08(2.0,1.0)  09(3.0,1.0)  10(4.0,1.0)            
     * 11(0.0,2.0)  12(1.0,2.0)  13(2.0,2.0)  14(3.0,2.0)  15(4.0,2.0)            
     * 16(0.0,3.0)  17(1.0,3.0)  18(2.0,3.0)  19(3.0,3.0)  20(4.0,3.0)            
     * 21(0.0,4.0)  22(1.0,4.0)  23(2.0,4.0)  24(3.0,4.0)  25(4.0,4.0) 
     * 
     * @return 
     */
    public TestCase testCase1() {
        return new TestCase.Builder()
                .withStartingNode(2, 2)
                .withRange(1)
                .withUbMatrix(5, 5)
                .withExpected(new ArrayList<IUrbanizationID>() {
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
                })
                .build(id++);

    }
    
    /**
     * InputNode (2,2) range: 2
     * 01(0.0,0.0)  02(1.0,0.0)  03(2.0,0.0)  04(3.0,0.0)  05(4.0,0.0)            
     * 06(0.0,1.0)  07(1.0,1.0)  08(2.0,1.0)  09(3.0,1.0)  10(4.0,1.0)            
     * 11(0.0,2.0)  12(1.0,2.0)  13(2.0,2.0)  14(3.0,2.0)  15(4.0,2.0)            
     * 16(0.0,3.0)  17(1.0,3.0)  18(2.0,3.0)  19(3.0,3.0)  20(4.0,3.0)            
     * 21(0.0,4.0)  22(1.0,4.0)  23(2.0,4.0)  24(3.0,4.0)  25(4.0,4.0)
     * 
     * @return 
     */
    public TestCase testCase2() {
        return new TestCase.Builder()
                .withStartingNode(2, 2)
                .withRange(2)
                .withUbMatrix(5, 5)                
                .withExpected(new ArrayList<IUrbanizationID>() {
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
                })
                .build(id++);
    }

    /**
     * InputNode (2,2) range: 3 
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0) 04(3.0,0.0) 05(4.0,0.0) 
     * 06(0.0,1.0) 07(1.0,1.0) 08(2.0,1.0) 09(3.0,1.0) 10(4.0,1.0)
     * 11(0.0,2.0) 12(1.0,2.0) 13(2.0,2.0) 14(3.0,2.0) 15(4.0,2.0) 
     * 16(0.0,3.0) 17(1.0,3.0) 18(2.0,3.0) 19(3.0,3.0) 20(4.0,3.0)
     * 21(0.0,4.0) 22(1.0,4.0) 23(2.0,4.0) 24(3.0,4.0) 25(4.0,4.0)
     * 
     * @return 
    */
    public TestCase testCase3() {
        
        return new TestCase.Builder()
                .withStartingNode(2, 2)
                .withRange(3)
                .withUbMatrix(5, 5)
                .withExpected(new ArrayList<IUrbanizationID>())
                .build(id++);
    }
    
    /**
     * InputNode (0,0) range: 1 
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0)
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     * 
     * @return 
     */
    public TestCase testCase4() {
        
        return new TestCase.Builder()
                .withStartingNode(0, 0)
                .withRange(1)
                .withUbMatrix(3, 3)
                .withExpected(new ArrayList<IUrbanizationID>(){{
                    add(helper.getId(2));
                    add(helper.getId(5));
                    add(helper.getId(4));
                }})
                .build(id++);               
    }

    
    /**
     * InputNode (2,0) range: 1 
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0)
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     * @return 
     */
    public TestCase testCase5() {
        
        return new TestCase.Builder()
                .withStartingNode(2, 0)
                .withRange(1)
                .withUbMatrix(3, 3)
                .withExpected(new ArrayList<IUrbanizationID>(){{
                    add(helper.getId(6));
                    add(helper.getId(5));
                    add(helper.getId(2));
                }})
                .build(id++);    
        
    }
    
    /**
     * InputNode (2,2) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0)
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     * @return 
     */
    public TestCase testCase6() {
        
        return new TestCase.Builder()
                .withStartingNode(2, 2)
                .withRange(1)
                .withUbMatrix(3, 3)
                .withExpected(new ArrayList<IUrbanizationID>(){{
                    add(helper.getId(5));
                    add(helper.getId(6));
                    add(helper.getId(8));
                }})
                .build(id++);    
        
    }
    
    /**
     * InputNode (0,2) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0)
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     * @return 
     */
    public TestCase testCase7() {
        
        return new TestCase.Builder()
                .withStartingNode(0, 2)
                .withRange(1)
                .withUbMatrix(3, 3)
                .withExpected(new ArrayList<IUrbanizationID>(){{
                    add(helper.getId(4));
                    add(helper.getId(5));
                    add(helper.getId(8));
                }})
                .build(id++);    
    }
    
    /**
     * InputNode (0,1) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0)
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     * @return 
     */
    public TestCase testCase8() {
        
        return new TestCase.Builder()
                .withStartingNode(0, 1)
                .withRange(1)
                .withUbMatrix(3, 3)
                .withExpected(new ArrayList<IUrbanizationID>(){{
                    add(helper.getId(1));
                    add(helper.getId(2));
                    add(helper.getId(5));
                    add(helper.getId(8));
                    add(helper.getId(7));
                }})
                .build(id++);            
    }
    
    
    /**
     * InputNode (1,0) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0)
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     * @return 
     */
    public TestCase testCase9() {
        
        return new TestCase.Builder()
                .withStartingNode(1, 0)
                .withRange(1)
                .withUbMatrix(3, 3)
                .withExpected(new ArrayList<IUrbanizationID>(){{
                    add(helper.getId(3));
                    add(helper.getId(6));
                    add(helper.getId(5));
                    add(helper.getId(4));
                    add(helper.getId(1));
                }})
                .build(id++);         
    }
    
    /**
     * InputNode (2,1) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0)
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     * @return 
     */
    public TestCase testCase10() {
        
        return new TestCase.Builder()
                .withStartingNode(2, 1)
                .withRange(1)
                .withUbMatrix(3, 3)
                .withExpected(new ArrayList<IUrbanizationID>(){{
                    add(helper.getId(2));
                    add(helper.getId(3));
                    add(helper.getId(9));
                    add(helper.getId(8));
                    add(helper.getId(5));
                }})
                .build(id++); 
    }
    
    /**
     * InputNode (1,2) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0)
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0)
     * @return 
     */
    public TestCase testCase11() {
        
        return new TestCase.Builder()
                .withStartingNode(1, 2)
                .withRange(1)
                .withUbMatrix(3, 3)
                .withExpected(new ArrayList<IUrbanizationID>(){{
                    add(helper.getId(4));
                    add(helper.getId(5));
                    add(helper.getId(6));
                    add(helper.getId(9));
                    add(helper.getId(7));
                }})
                .build(id++);        
    }
    
    
    /**
     * InputNode (4,4) range: 3 
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0) 04(3.0,0.0) 05(4.0,0.0) 06(5.0,0.0) 07(6.0,0.0) 
     * 08(0.0,1.0) 09(1.0,1.0) 10(2.0,1.0) 11(3.0,1.0) 12(4.0,1.0) 13(5.0,1.0) 14(6.0,1.0) 
     * 15(0.0,2.0) 16(1.0,2.0) 17(2.0,2.0) 18(3.0,2.0) 19(4.0,2.0) 20(5.0,2.0) 21(6.0,2.0)
     * 22(0.0,3.0) 23(1.0,3.0) 24(2.0,3.0) 25(3.0,3.0) 26(4.0,3.0) 27(5.0,3.0) 28(6.0,3.0)
     * 29(0.0,4.0) 30(1.0,4.0) 31(2.0,4.0) 32(3.0,4.0) 33(4.0,4.0) 34(5.0,4.0) 35(6.0,4.0) 
     * 36(0.0,5.0) 37(1.0,5.0) 38(2.0,5.0) 39(3.0,5.0) 40(4.0,5.0) 41(5.0,5.0) 42(6.0,5.0) 
     * 43(0.0,6.0) 44(1.0,6.0) 45(2.0,6.0) 46(3.0,6.0) 47(4.0,6.0) 48(5.0,6.0) 49(6.0,6.0)
     * 
     * @return 
     */
    public TestCase testCase12() {
        return new TestCase.Builder()
                .withStartingNode(4, 4)
                .withRange(3)
                .withUbMatrix(7, 7)
                .withExpected(new ArrayList<IUrbanizationID>(){{
                    add(helper.getId(9));
                    add(helper.getId(10));
                    add(helper.getId(11));
                    add(helper.getId(12));
                    add(helper.getId(13));
                    add(helper.getId(14));

                    add(helper.getId(44));

                    add(helper.getId(37));
                    add(helper.getId(30));
                    add(helper.getId(23));
                    add(helper.getId(16));
                }})
                .build(id++);         
    }
    
    
    /**
     * InputNode (3,3) range: 1 
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0)
     * 04(0.0,1.0) 05(1.0,1.0) 06(2.0,1.0) 
     * 07(0.0,2.0) 08(1.0,2.0) 09(2.0,2.0) 
     * 
     * Expected exception "Oops, something went wrong!! Starting Node not found" 
     * 
     * @return 
     */
    public TestCase testCase13() {
        
        return new TestCase.Builder()
                .withStartingNode(3, 3)
                .withRange(1)
                .withUbMatrix(3, 3)
                .withExpected(null)
                .build(id++); 
    }  
    
    
    /**
     * Matrix with width != height InputNode (2,1) range: 1
     *
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0) 04(3.0,0.0) 05(4.0,0.0) 06(5.0,0.0)
     * 07(0.0,1.0) 08(1.0,1.0) 09(2.0,1.0) 10(3.0,1.0) 11(4.0,1.0) 12(5.0,1.0)
     * 13(0.0,2.0) 14(1.0,2.0) 15(2.0,2.0) 16(3.0,2.0) 17(4.0,2.0) 18(5.0,2.0)
     *
     * @return 
     */
    public TestCase testCase14() {
        
        return new TestCase.Builder()
                .withStartingNode(2, 1)
                .withRange(1)
                .withUbMatrix(6, 3)
                .withExpected(new ArrayList<IUrbanizationID>() {
                    {
                        add(helper.getId(2));
                        add(helper.getId(3));
                        add(helper.getId(4));

                        add(helper.getId(10));
                        add(helper.getId(16));

                        add(helper.getId(15));
                        add(helper.getId(14));

                        add(helper.getId(8));

                    }
                })
                .build(id++); 
    } 
    
    
     /**
     * Matrix with width != height InputNode (2,1) range: 2
     *
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0) 04(3.0,0.0) 05(4.0,0.0) 06(5.0,0.0)
     * 07(0.0,1.0) 08(1.0,1.0) 09(2.0,1.0) 10(3.0,1.0) 11(4.0,1.0) 12(5.0,1.0)
     * 13(0.0,2.0) 14(1.0,2.0) 15(2.0,2.0) 16(3.0,2.0) 17(4.0,2.0) 18(5.0,2.0)
     *
     * @return 
     */
    public TestCase testCase15() {
        
        return new TestCase.Builder()
                .withStartingNode(2, 1)
                .withRange(2)
                .withUbMatrix(6, 3)
                .withExpected(new ArrayList<IUrbanizationID>(){{
                    add(helper.getId(5));
                    add(helper.getId(11));
                    add(helper.getId(17));

                    add(helper.getId(13));
                    add(helper.getId(7));
                    add(helper.getId(1));
                }})
                .build(id++);    
    }
    
    
    /**
     * InputNode (0,0) range: 1
     * 01(0.0,0.0) 02(1.0,0.0) 03(2.0,0.0)
     * 
     * @return 
     */
    public TestCase testCase16() {
        
        return new TestCase.Builder()
                .withStartingNode(0, 0)
                .withRange(1)
                .withUbMatrix(3, 1)
                .withExpected(new ArrayList<IUrbanizationID>(){{
                    add(helper.getId(2));
                }})
                .build(id++); 
    }  
   
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
