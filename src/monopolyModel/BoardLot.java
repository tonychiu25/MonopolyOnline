/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyModel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tonychiu
 */
public class BoardLot extends BoardProperty{
    public static final int LOT_COLOR_BROWN = 0;
    public static final int LOT_COLOR_TEAL = 1;
    public static final int LOT_COLOR_PINK = 2;
    public static final int LOT_COLOR_ORANGE = 3;
    public static final int LOT_COLOR_RED = 4;
    public static final int LOT_COLOR_YELLOW = 5;
    public static final int LOT_COLOR_GREEN = 6;
    public static final int LOT_COLOR_BLUE =7;
    
    private final int TOTAL_BUILDING_COUNT = 6;
    private int buildingCount, buildingCost;
    private int[] rentCostList;
    private int lotColor;
    
    public BoardLot(String name, int cost, int buildingCost, int[] rentCosts, int lotColor) throws IllegalArgumentException{        
        super(name, BOARD_FIELD_LOT, cost);
        if (buildingCost <= 0) {
            throw new IllegalArgumentException("Error : buildingCost must be greater than 0");
        }
        if (rentCosts.length != TOTAL_BUILDING_COUNT) {
            throw new IllegalArgumentException("Error : rentCosts[] must have a size of 6");
        }
        for (int rent : rentCosts) {
            if (rent <= 0) {
                throw new IllegalArgumentException("Error : rentCost[] must contain all positive non zero integers");
            }
        }
        if (buildingCost <= 0) {
            throw new IllegalArgumentException("Error : buildingCost must be greater than 0");
        }
        if (lotColor < 0 || lotColor > 7) {
            throw new IllegalArgumentException("Error : Invalid lotColor, please select from LOT_COLOR_... in BoardLot Class");
        }
        
        buildingCount = 0;
        this.lotColor = lotColor;
        this.buildingCost = buildingCost;
        rentCostList = rentCosts;
        rentCost = rentCostList[buildingCount];
    }
    
    public void setBuildingCount(int buildingCount) {
        // TODO : throw an exception for bad input
        this.buildingCount = buildingCount;
        rentCost = rentCostList[buildingCount-1];
    }
    
    public int getBuildingCount() {
        return buildingCount;
    }
    
    public int[] getRentCosts() {
        return rentCostList;
    }
    
    public int getBuildingCost() {
        return buildingCost;
    }
    
    public int getLotColor() {
        return lotColor;
    }
    
    public static void main(String args[]) {
        try {
            int [] rents = {4,-3,423,4,4,4};
            BoardLot bb = new BoardLot("Park Avenue", 11, 11, rents, LOT_COLOR_BLUE);
            System.out.println(bb.getRentCosts()[2]);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}
