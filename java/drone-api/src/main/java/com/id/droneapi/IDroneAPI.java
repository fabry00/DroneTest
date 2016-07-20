package com.id.droneapi;

import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;
import com.id.droneapi.mock.api.IUrbanizationID;
import com.id.droneapi.mock.api.IDealistaAPI;
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
     * obtenerUrbanizaciones(38.56889, 40.511107, 1) = [id urbanizacion7, id
     * urbanizacion8, id urbanizacion9, id urbanizacion12, id urbanizacion13, id
     * urbanizacion17, id urbanizacion18. id urbanizacion19]
     *
     * @param x coordinate X for the inputNode 
     * @param y coordinate Y for the inputNode 
     * @param range Neighborhoods distance 
     * @return The list ids
     * @throws com.id.droneapi.exception.NeighborhoodsAlgorithmEx The exception
     */
    List<IUrbanizationID> getNeighborhoods(double x, double y, int range)
            throws NeighborhoodsAlgorithmEx;

    
    /**
     *
     * This specify also the direction to use to search the Neighborhoods
     * @param x coordinate X for the inputNode 
     * @param y coordinate Y for the inputNode 
     * @param range Neighborhoods distance 
     * @param direction Scan direction
     * @return The list ids
     * @throws com.id.droneapi.exception.NeighborhoodsAlgorithmEx The exception
     */
    List<IUrbanizationID> getNeighborhoods(double x, double y, int range, IScanDirection direction)
            throws NeighborhoodsAlgorithmEx;
}
