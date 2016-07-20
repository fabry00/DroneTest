package com.id.droneapi;

import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;
import com.id.droneapi.mock.API.IUrbanizationID;
import com.id.droneapi.mock.API.IDealistaAPI;
import java.util.List;

/**
 * Interface that extends the IDealistaAPI adding the required method.
 *
 * @author Fabrizio Faustinoni
 */
public interface IDroneAPI extends IDealistaAPI {

    /**
     * Required function.
     *
     * obtenerUrbanizaciónes(38.56889, 40.511107, 1) = [id urbanización7, id
     * urbanización8, id urbanización9, id urbanización12, id urbanización13, id
     * urbanización17, id urbanización18. id urbanización19]
     *
     * @param x
     * @param y
     * @param range
     * @return
     * @throws com.id.droneapi.exception.NeighborhoodsAlgorithmEx
     */
    List<IUrbanizationID> getNeighborhoods(double x, double y, int range)
            throws NeighborhoodsAlgorithmEx;

    List<IUrbanizationID> getNeighborhoods(double x, double y, int range, IScanDirection direction)
            throws NeighborhoodsAlgorithmEx;
}
