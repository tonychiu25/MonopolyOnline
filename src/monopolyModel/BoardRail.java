/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyModel;

/**
 *
 * @author tonychiu
 */
public class BoardRail extends BoardProperty{
    public BoardRail(String name, int type, int cost) throws IllegalArgumentException {
        super(name, BOARD_FIELD_RAIL, cost);
    }
}
