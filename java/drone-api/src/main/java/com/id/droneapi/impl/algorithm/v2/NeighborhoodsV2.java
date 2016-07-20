package com.id.droneapi.impl.algorithm.v2;

import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;
import com.id.droneapi.impl.algorithm.v2.sides.ISide;
import com.id.droneapi.impl.algorithm.v2.sides.ISide.SideType;
import com.id.droneapi.impl.algorithm.v2.sides.SideFactory;
import com.id.droneapi.mock.api.IDealistaAPI;
import com.id.droneapi.mock.api.IUrbanizationID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class NeighborhoodsV2 {

    private static final Logger logger = Logger.getLogger(NeighborhoodsV2.class.getName());

    private final Map<SideType, ISide> sides = new HashMap<>();

    private final IUrbanizationID centralNode;

    public NeighborhoodsV2(IUrbanizationID centraltNode) {
        this.centralNode = centraltNode;

    }

    public List<Node> getNodes() {
        List<Node> nodes = new ArrayList<>();
        List<ISide> orderedSides = getOrderedSides();
        for (ISide side : orderedSides) {
            for (Node node : side.getNodes()) {
                // Skipping already addded nodes
                // The vertices are shared for each adjacent side
                if (!nodes.contains(node)) {
                    nodes.add(node);
                }
            }
        }

        return Collections.unmodifiableList(nodes);
    }

    public List<IUrbanizationID> getNodesIDs() {
        List<IUrbanizationID> nodes = new ArrayList<>();
        for (Node node : getNodes()) {
            nodes.add(node.getId());
        }
        return Collections.unmodifiableList(nodes);
    }

    public void calculateNeighborhoodsNodes(IDealistaAPI api, int range)
            throws NeighborhoodsAlgorithmEx {

        SideType firstSide = getSidesOrder().iterator().next();
        SideFactory factory = new SideFactory();
        for (SideType type : SideType.values()) {

            boolean isFirst = (firstSide.equals(type)) ? true : false;

            ISide side = factory.createDirection(type, isFirst);

            side.calculateNeighborhoodsNodes(api, centralNode, range);
            sides.put(type, side);
        }

    }

    private List<SideType> getSidesOrder() {
        List<SideType> sidesOrdered = new ArrayList<>();
        sidesOrdered.add(SideType.UP);
        sidesOrdered.add(SideType.RIGHT);
        sidesOrdered.add(SideType.BOTTOM);
        sidesOrdered.add(SideType.LEFT);

        return sidesOrdered;
    }

    private List<ISide> getOrderedSides() {
        List<SideType> sidesOrder = getSidesOrder();
        List<ISide> sidesOrdered = new ArrayList<>();

        for (SideType type : sidesOrder) {
            sidesOrdered.add(sides.get(type));
        }

        return sidesOrdered;
    }

     
}
