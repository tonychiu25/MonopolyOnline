/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyModel;

/**
 *
 * @author tonychiu
 */
public class BoardProperty extends BoardField{
    
    protected int propertyCost, rentCost;
    protected boolean vacancy;
    protected Player propertyOwner;
    
    public BoardProperty(String name, int type, int cost) throws IllegalArgumentException {
        super(name, type);
        
        if (cost <= 0) {
            throw new IllegalArgumentException("Error : cost must be greater than 0");
        }
        
        vacancy = true;
        propertyCost = cost;
        rentCost = 0;
        propertyOwner = null;
    }
    
    public void setRentCost(int cost) {
        rentCost = cost;
    }
    
    public void setPropertyOwner(Player player) {
        propertyOwner = player;
        vacancy = false;
    }
    
    public Player getPropertyOwner() {
        return propertyOwner;
    }
    
    public boolean getVacancy() {
        return vacancy;
    }
    
    public int getPropertyCost() {
        return propertyCost;
    }
    
    public int getRentCost() {
        return rentCost;
    }
}
