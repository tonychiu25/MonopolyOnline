/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyModel;

/**
 *
 * @author tonychiu
 */
public class BoardUtility extends BoardProperty{
    public BoardUtility(String name, int cost, String description) throws IllegalArgumentException {
        super(name, BOARD_FIELD_UTILITY, cost);
        desc = description;
    }
}
