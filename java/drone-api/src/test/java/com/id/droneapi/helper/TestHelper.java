package com.id.droneapi.helper;

import com.id.droneapi.IDroneAPI;
import com.id.droneapi.INeighborhoodsAlgorithm;
import com.id.droneapi.impl.DroneAPI;
import com.id.droneapi.mock.api.IDealistaAPI;
import com.id.droneapi.mock.api.IUrbanizationID;
import com.id.droneapi.mock.Node;
import com.id.droneapi.mock.UrbanizationID;
import com.id.droneapi.mock.UrbanizationMatrix;
import com.id.droneapi.mock.UrbanizationMatrixFactory;
import com.id.droneapi.mock.exception.DuplicatedAdjacentNode;
import com.id.droneapi.mock.exception.NodeAlreadyAdded;
import com.id.droneapi.mock.exception.NodeAlreadyAddedAsAdjacent;
import com.id.droneapi.mock.exception.NodeNotFound;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertEquals;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class TestHelper {

    private static final String TESTS_FOLDER = "tests";

    public IDealistaAPI getIdealistaAPI(int col, int row) throws NodeAlreadyAddedAsAdjacent, DuplicatedAdjacentNode, NodeAlreadyAdded, NodeNotFound {
        IDealistaAPI idApi = createUBMatrix(row, col);
        return idApi;
    }

    /**
     * @param row
     * @param col
     * @return
     * @throws com.id.droneapi.mock.exception.NodeAlreadyAddedAsAdjacent
     * @throws com.id.droneapi.mock.exception.DuplicatedAdjacentNode
     * @throws com.id.droneapi.mock.exception.NodeAlreadyAdded
     */
    public IDealistaAPI createUBMatrix(int row, int col) throws NodeAlreadyAddedAsAdjacent, DuplicatedAdjacentNode, NodeAlreadyAdded {
        return new UrbanizationMatrixFactory().createUBMatrix(row, col);
    }

    public UrbanizationID getId(int id) {
        return new UrbanizationMatrixFactory().getId(id);
    }

    public String testToString(IDealistaAPI api, TestCase test, List<IUrbanizationID> actual) {

        String string = "Test " + test.id + System.getProperty("line.separator");
        string += "InputNode (" + test.starting_node_x + ","
                + test.starting_node_y + ") range: " + test.range
                + System.getProperty("line.separator");
        string += api + System.getProperty("line.separator");
        string += "Expected: " + test.expected + System.getProperty("line.separator");
        string += "Found: " + actual + System.getProperty("line.separator");
        string += actual.equals(test.expected)
                ? " *** PASSED ****" : "############# FAILED ########";
        string += System.getProperty("line.separator")
                + "--------------------------------------------"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator");
        System.out.println(string);
        return string;

    }

    public void executeTest(TestCase test, INeighborhoodsAlgorithm algorithm,
            String className) throws Exception {
        IDealistaAPI api = getIdealistaAPI(test.ub_matrix_width, test.ub_matrix_height);
        IDroneAPI testDrone = new DroneAPI(api, algorithm);

        List<IUrbanizationID> actual
                = testDrone.getNeighborhoods(test.starting_node_x, test.starting_node_y, test.range);

        appendTestOutput(testToString(api, test, actual), className);

        assertEquals(test.expected, actual);
    }

    public void initTesting(String className) {
        File testFolder = new File(TESTS_FOLDER);
        if (!testFolder.exists()) {
            testFolder.mkdir();
        }
    }

    public void appendTestOutput(String testString, String className) throws IOException {
        Format formatter = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss");
        String filename = TESTS_FOLDER + "/" + className + "_" + formatter.format(new Date()) + ".txt";

        try (FileWriter fw = new FileWriter(filename, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(testString);

        } catch (IOException ex) {
            Logger.getLogger(TestHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
