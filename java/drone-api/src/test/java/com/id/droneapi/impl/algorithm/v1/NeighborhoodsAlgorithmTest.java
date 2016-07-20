package com.id.droneapi.impl.algorithm.v1;

import com.id.droneapi.INeighborhoodsAlgorithm;
import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;
import com.id.droneapi.helper.TestCase;
import com.id.droneapi.helper.TestCaseBuilder;
import com.id.droneapi.helper.TestHelper;
import junit.framework.Assert;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class NeighborhoodsAlgorithmTest {

    private static INeighborhoodsAlgorithm algorithm;
    private static TestHelper helper;
    private static TestCaseBuilder testBuilder;

    public NeighborhoodsAlgorithmTest() {
        testBuilder = new TestCaseBuilder();
        helper = new TestHelper();
        algorithm = new NeighborhoodsAlgorithm();
    }

    private void executeTest(TestCase test) throws Exception {
        helper.executeTest(test, algorithm);
    }

    public void testNeighborhoodsV1_1() throws Exception {

        TestCase test = testBuilder.testCase1();
        executeTest(test);
    }

    public void testNeighborhoodsV1_2() throws Exception {

        TestCase test = testBuilder.testCase2();
        executeTest(test);
    }

    public void testNeighborhoodsV1_3() throws Exception {
        TestCase test = testBuilder.testCase3();
        executeTest(test);
    }

    public void testNeighborhoodsV1_4() throws Exception {
        TestCase test = testBuilder.testCase4();
        executeTest(test);
    }

    public void testNeighborhoodsV1_5() throws Exception {
        TestCase test = testBuilder.testCase5();
        executeTest(test);
    }

    public void testNeighborhoodsV1_6() throws Exception {
        TestCase test = testBuilder.testCase6();
        executeTest(test);
    }

    public void testNeighborhoodsV1_7() throws Exception {
        TestCase test = testBuilder.testCase7();
        executeTest(test);
    }

    public void testNeighborhoodsV1_8() throws Exception {
        TestCase test = testBuilder.testCase8();
        executeTest(test);
    }

    public void testNeighborhoodsV1_9() throws Exception {
        TestCase test = testBuilder.testCase9();
        executeTest(test);
    }

    public void testNeighborhoodsV1_10() throws Exception {
        TestCase test = testBuilder.testCase10();
        executeTest(test);
    }

    public void testNeighborhoodsV1_11() throws Exception {
        TestCase test = testBuilder.testCase11();
        executeTest(test);
    }

    public void testNeighborhoodsV1_12() throws Exception {
        TestCase test = testBuilder.testCase12();
        executeTest(test);
    }

    public void testNeighborhoodsV1_13() throws Exception {
        boolean exception = false;
        try {
            TestCase test = testBuilder.testCase13();
            executeTest(test);
        } catch (NeighborhoodsAlgorithmEx ex) {
            exception = true;
        }

        Assert.assertTrue(exception);
    }

    public void testNeighborhoodsV1_14() throws Exception {
        TestCase test = testBuilder.testCase14();
        executeTest(test);
    }

    public void testNeighborhoodsV1_15() throws Exception {
        TestCase test = testBuilder.testCase15();
        executeTest(test);
    }

    /**
     * @throws java.lang.Exception
     */
    /* NOT YET SUPPORTED
    public void testNeighborhoodsV1_16() throws Exception {
        TestCase test = testBuilder.testCase16();
        executeTest(test);
    }*/
}
