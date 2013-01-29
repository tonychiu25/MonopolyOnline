/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyModel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author tonychiu
 */
public class Player {
    private String playerName;
    int cashBalance, boardPosition;
    boolean incarcerated;
    private Map<Integer, HashSet<BoardLot>> ownedLotMap;
    private Set<BoardRail> ownedRailSet;
    private Set<BoardUtility> ownedUtilitySet;

    public Player(String name) {
        playerName = name;
        cashBalance = 0;
        boardPosition = 0;
        incarcerated = false;
        
        ownedLotMap = new HashMap();
        ownedRailSet = new HashSet();
        ownedUtilitySet = new HashSet();
    }
    
    public Map<Integer, HashSet<BoardLot>> getOwnedLots() {
        return ownedLotMap;
    } 
    
    public void addOwnedUtility(BoardUtility utility) {
        ownedUtilitySet.add(utility);
    }
    
    public void addOwnedRail(BoardRail rail) {
        ownedRailSet.add(rail);
    }
    
    public void addOwnedLot(BoardLot lot) {
        Integer lotColor = lot.getLotColor();
        HashSet<BoardLot> boardLots;
        if (ownedLotMap.containsKey(lotColor)) {
            boardLots = ownedLotMap.get(lotColor);
            boardLots.add(lot);
        } else {
            boardLots = new HashSet();
            boardLots.add(lot);
            ownedLotMap.put(lotColor, boardLots);
        }
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setCashBalance(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Error : amount must be positive integer");
        } else {
            cashBalance = amount;
        }
    }
    
    public void setBoardPosition(int boardPositionIndex) {
        boardPosition = boardPositionIndex;
    }
    
    public void setIncacerated(boolean incarceratedStatus) {
        incarcerated = incarceratedStatus;
    }
    
    public int getCashBalance() {
        return cashBalance;
    }
    
    public int getBoardPosition() {
        return boardPosition;
    }
    
    public boolean isIncarcerated() {
        return incarcerated;
    }
    
    
    
    /** TESTING METHODS **/
    public void printLot() {
        for (Integer color : ownedLotMap.keySet()) {
            for (BoardLot lot : ownedLotMap.get(color)) {
                System.out.println(lot.getFieldName());
            }
        }
    }
}