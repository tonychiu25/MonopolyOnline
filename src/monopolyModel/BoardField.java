/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyModel;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author tonychiu
 */
public class BoardField {
    
    public static final int BOARD_FIELD_LOT = 0;
    public static final int BOARD_FIELD_RAIL = 1;
    public static final int BOARD_FIELD_UTILITY = 2;
    public static final int BOARD_FIELD_CHANCE = 3;
    public static final int BOARD_FIELD_GO = 4;
    public static final int BOARD_COMMUNITY_CHEST = 5;
    public static final int BOARD_INCOME_TAX = 6;
    public static final int BOARD_CHANCE = 7;
    public static final int BOARD_FIELD_JAIL = 8;
    public static final int BOARD_FIELD_FREE_PARKING = 9;
    public static final int BOARD_FIELD_SUPER_TAX = 10;
    
    /** Stores all BOARD_FIELD types for easy type checking **/
    
    
    protected String fieldName, desc;
    protected int fieldType;
    
    public BoardField(String name, int type) throws IllegalArgumentException {
        
        if (type < 0 || type > 10) {
            throw new IllegalArgumentException("Error : Invalid type argument");
        }
        fieldName = name;
        fieldType = type;
        desc = "";
    }
    
    public void setDescription(String description) {
        desc = description;
    }
    
    public String getDescription() {
        return desc;
    }
    
    public String getFieldName() {
        return fieldName;
    }
    
    public int getFieldType() {
        return fieldType;
    }
}
