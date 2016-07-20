package com.id.droneapi.helper;

import com.id.droneapi.mock.api.IUrbanizationID;
import java.util.List;

public class TestCase {

    public int id;
    public int starting_node_x;
    public int starting_node_y;
    public int range;

    // urbanizationMatrix dimensions
    public int ub_matrix_width;
    public int ub_matrix_height;

    public List<IUrbanizationID> expected;

    private TestCase(int id, int starting_node_x, int starting_node_y,
            int range, int ub_matrix_width, int ub_matrix_height,
            List<IUrbanizationID> expected) {

        this.id = id;
        this.starting_node_x = starting_node_x;
        this.starting_node_y = starting_node_y;
        this.range = range;
        this.ub_matrix_width = ub_matrix_width;
        this.ub_matrix_height = ub_matrix_height;
        this.expected = expected;
    }

    public static class Builder {

        int x, y, w, h, range;
        List<IUrbanizationID> expected;

        public Builder withStartingNode(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder withRange(int range) {
            this.range = range;
            return this;
        }

        public Builder withUbMatrix(int w, int h) {
            this.w = w;
            this.h = h;
            return this;
        }

        public Builder withExpected(List<IUrbanizationID> expected) {
            this.expected = expected;
            return this;
        }

        public TestCase build(int id) {
            return new TestCase(id, x, y, range, w, h, expected);
        }

    }
}
