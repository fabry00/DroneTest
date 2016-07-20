package com.id.droneapi.impl.algorithm.v1;

import com.id.droneapi.IScanDirection;
import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;
import com.id.droneapi.helper.TestCase;
import com.id.droneapi.helper.TestCaseBuilder;
import com.id.droneapi.helper.TestHelper;
import com.id.droneapi.impl.algorithm.v1.scandirection.CounterClockWise;
import com.id.droneapi.mock.api.IUrbanizationID;
import java.util.Collections;
import java.util.List;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * WARNING!!!!!!!!!
 * The CounterClockWise has been planned but not completed yet!!
 * This is just an example to how to use the dependency injection 
 * 
 * @author Fabrizio Faustinoni
 */
public class NeighborhoodsAlgorithmReverseTest {

    private final TestHelper helper;
    private final TestCaseBuilder testBuilder;

    public NeighborhoodsAlgorithmReverseTest() {
        testBuilder = new TestCaseBuilder();
        helper = new TestHelper();
    }

    private void executeTest(TestCase test, IScanDirection direction) throws Exception {

        helper.executeTest(test, new NeighborhoodsAlgorithm(direction));
    }

    private List<IUrbanizationID> reverse(List<IUrbanizationID> expected) {
        if (!expected.isEmpty()) {
            List<IUrbanizationID> shallowCopy = expected.subList(1, expected.size());
            Collections.reverse(shallowCopy);

            shallowCopy.add(0, expected.get(0));
            return shallowCopy;
        }
        return expected;
    }

    @Test
    public void testNeighborhoodsV1_1_reverse() throws Exception {

        TestCase test = testBuilder.testCase1();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());
    }

    @Test
    public void testNeighborhoodsV1_2_reverse() throws Exception {

        TestCase test = testBuilder.testCase2();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());
    }

    @Test
    public void testNeighborhoodsV1_3_reverse() throws Exception {

        TestCase test = testBuilder.testCase3();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());
    }

    @Ignore("Not Yet Supported")
    @Test
    public void testNeighborhoodsV1_4_reverse() throws Exception {

        TestCase test = testBuilder.testCase4();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());
    }

    @Ignore("Not Yet Supported")
    @Test
    public void testNeighborhoodsV1_5_reverse() throws Exception {

        TestCase test = testBuilder.testCase5();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());

    }

    @Ignore("Not Yet Supported")
    @Test
    public void testNeighborhoodsV1_6_reverse() throws Exception {

        TestCase test = testBuilder.testCase6();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());

    }

    @Test
    public void testNeighborhoodsV1_7_reverse() throws Exception {

        TestCase test = testBuilder.testCase7();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());
    }

    @Test
    public void testNeighborhoodsV1_8_reverse() throws Exception {

        TestCase test = testBuilder.testCase8();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());
    }

    @Ignore("Not Yet Supported")
    @Test
    public void testNeighborhoodsV1_9_reverse() throws Exception {

        TestCase test = testBuilder.testCase9();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());
    }

    @Test
    public void testNeighborhoodsV1_10_reverse() throws Exception {

        TestCase test = testBuilder.testCase10();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());
    }

    @Test
    public void testNeighborhoodsV1_11_reverse() throws Exception {

        TestCase test = testBuilder.testCase11();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());
    }

    @Ignore("Not Yet Supported")
    @Test
    public void testNeighborhoodsV1_12_reverse() throws Exception {

        TestCase test = testBuilder.testCase12();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());

    }

    @Test
    public void testNeighborhoodsV1_13_reverse() throws Exception {

        boolean exception = false;
        try {
            TestCase test = testBuilder.testCase13();
            executeTest(test, new CounterClockWise());
        } catch (NeighborhoodsAlgorithmEx ex) {
            exception = true;
        }
        Assert.assertTrue(exception);

    }

    @Test
    public void testNeighborhoodsV1_14_reverse() throws Exception {

        TestCase test = testBuilder.testCase14();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());
    }

    @Ignore("Not Yet Supported")
    @Test
    public void testNeighborhoodsV1_15_reverse() throws Exception {

        TestCase test = testBuilder.testCase15();
        test.expected = reverse(test.expected);
        executeTest(test, new CounterClockWise());

    }
}
