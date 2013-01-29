/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyModel;

/**
 *
 * @author tonychiu
 */
public class RandomizedCards {
    public int INDEX;
    public String DESCRIPTION;
    private boolean taken;

    public RandomizedCards(int INDEX, String DESCRIPTION) {
        this.INDEX = INDEX;
        this.DESCRIPTION = DESCRIPTION;
        taken = false;
    }
    
    public boolean isTaken() {
        return taken;
    }
    
    public void setTaken(boolean status) {
        taken = status;
    }
}
