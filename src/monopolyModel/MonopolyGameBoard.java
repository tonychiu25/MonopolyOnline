/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyModel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author tonychiu
 */
public class MonopolyGameBoard {
    public static final int NUM_BOARD_PIECES = 40;
    public static final int NUM_CHANCE_CARDS = 15;
    public static final int NUM_COMMUNITY_CHEST_CARDS = 15;
    
    public static final String GO_DESC = "Collect $200";
    public static final String GO_PIECE = "Go";
    public static final String COMMUNITY_CHEST = "Community Chest";
    public static final String COMMUNITY_CHEST_DESC = "Collect a community chest card";
    public static final String BROWN_1_NAME = "Old Kent Road";
    public static final String BROWN_2_NAME = "Whitechapel Road";
    public static final String INCOME_TAX_NAME = "Income Tax";
    public static final String INCOME_TAX_DESC = "Pay $(200)";
    public static final String RAIL_1_NAME = "King's Cross Station";
    public static final String RAIL_2_NAME = "Marylebone";
    public static final String RAIL_3_NAME = "Fenchurch St Station";
    public static final String RAIL_4_NAME = "Liverpool Street Station";
    public static final String TEAL_1_NAME = "The Angle Islington";
    public static final String TEAL_2_NAME = "Euston Road";
    public static final String TEAL_3_NAME = "Pentonville Road";
    public static final String CHANCE_CARD = "Chance";
    public static final String CHANCE_CARD_DESC = "Collect a Chance card";
    public static final String JAIL = "Jail";
    public static final String JAIL_DESC = "Jail Desc";
    public static final String PINK_1_NAME = "Pall Mall";
    public static final String ELECTRIC_NAME = "Electric Company";
    public static final String ELECTRIC_DESC = "Desc for electric company";
    public static final String PINK_2_NAME = "Whitehall";
    public static final String PINK_3_NAME = "Northumberland Avenue";
    public static final String ORANGE_1_NAME = "Bow Street";
    public static final String ORANGE_2_NAME = "Marlborough Street";
    public static final String ORANGE_3_NAME = "Vine Street";
    public static final String FREE_PARKING = "Free Parking";
    public static final String FREE_PARKING_DESC = "Description for free parking";
    public static final String RED_1_NAME = "The Strand";
    public static final String RED_2_NAME = "Fleet Street";
    public static final String RED_3_NAME = "Trafalgar Square";
    public static final String YELLOW_1_NAME = "Leicester Square";
    public static final String YELLOW_2_NAME = "Coventry Street";
    public static final String YELLOW_3_NAME = "Piccadilly";
    public static final String WATER_NAME = "Water Works";
    public static final String WATER_DESC = "Description for Water Works";
    public static final String GO_JAIL = "Go to Jail";
    public static final String GO_JAIL_DESC = "Description for going to jail";
    public static final String GREEN_1_NAME = "Regent Stret";
    public static final String GREEN_2_NAME = "Oxford Street";
    public static final String GREEN_3_NAME = "Bond Street";
    public static final String BLUE_1_NAME = "Park Lane";
    public static final String BLUE_2_NAME = "Mayfair";
    public static final String SUPER_TAX_NAME = "Luxary Tax";
    public static final String SUPER_TAX_DESC = "Description for super tax";
    
    private final int RAIL_COST = 200;
    private Vector<Player> players;
    private BoardField[] gameBoard;
    private int currentPlayerIndex;
            
    private Map<Integer, HashSet<BoardProperty>> streets;

    public MonopolyGameBoard() {
        gameBoard = new BoardField[NUM_BOARD_PIECES];
        players = new Vector();
        streets = new HashMap();
        currentPlayerIndex = 0;
    }
    
    public Player getCurrentPlayer() {
        Player currPlayer;
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
        }
        
         currPlayer = players.get(currentPlayerIndex);
         currentPlayerIndex++;
         
         return currPlayer;
    }
    
    /**
     * Pre : lot is vacant, player has enough money; check in controller
     * @param player
     * @param lot 
     */
    public void purchaseLot(Player player, BoardLot lot) {
        player.addOwnedLot(lot);
        player.setCashBalance(player.getCashBalance() - lot.getPropertyCost());
        lot.setPropertyOwner(player);
    }
    
    public void erectBuilding(Player player, BoardLot lot) {
        player.setCashBalance(player.getCashBalance() - lot.getBuildingCost());
        lot.setBuildingCount(lot.getBuildingCount() + 1);
    }
    
    /** Determines if a player owns all the Lot of a same color **/
    public boolean isStreetKing(Player player, int streetColor) {
        Map<Integer, HashSet<BoardLot>> lotsOwned = player.getOwnedLots();
        if (lotsOwned.containsKey(streetColor) && 
                lotsOwned.get(streetColor).size() == streets.get(streetColor).size()) {
            return true;
        } else {
            return false;
        }
    }
    
/** Methods involving the player **/
    public boolean addPlayer(String playerName) {
        Player newPlayer;
        for (Player play : players) {
            if (play.getPlayerName() == playerName) {
                return false;
            }
        }
        
        newPlayer = new Player(playerName);
        players.add(newPlayer);
        
        return true;
    }
    
    public BoardField getFieldBoardPosition(int boardPosition) throws ArrayIndexOutOfBoundsException{
        if (boardPosition >= gameBoard.length) {
            throw new ArrayIndexOutOfBoundsException("Error : boardPosition exceeds number of board pieces");
        } else {
            return gameBoard[boardPosition];
        }
    }
    
    /** Add a non-property field to the board **/
    private void addRegularField(int boardPos, String name, String desc, int fieldType) {
        BoardField bf = null;
        try {
            bf = new BoardField(name, fieldType);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        
        bf.setDescription(desc);
        gameBoard[boardPos] = bf;
    }
    
    private void addLotField(int boardPos, String name, int cost, int buildingCost, int[] rentCosts, int lotColor) {
        BoardLot bl = null;
        HashSet<BoardProperty> streetLots= null;
        try {
            bl = new BoardLot(name, cost, buildingCost, rentCosts, lotColor);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        
        if (streets.containsKey(lotColor)) {
            streetLots = streets.get(lotColor);
        } else {
            streetLots = new HashSet();
        }
        
        streetLots.add(bl);
        streets.put(lotColor, streetLots);
        
        gameBoard[boardPos] = bl;
    }
    
    private void addRailField(int boardPos, String name, int cost) {
        BoardField br = null;
        try {
            br = new BoardRail(name, BoardField.BOARD_FIELD_RAIL, cost);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        
        gameBoard[boardPos] = br;
    }
    
    private void addUtilityField(int boardPos, String name, int cost, String description) {
        BoardField bu = null;
        try {
            bu = new BoardUtility(name, cost, description);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        
        gameBoard[boardPos] = bu;
    }
    
    public void initiateBoard() {
        addRegularField(0, GO_PIECE, GO_DESC, BoardField.BOARD_FIELD_GO);
        addLotField(1, BROWN_1_NAME, 60, 50, new int[]{2, 10, 30, 90, 160, 250}, BoardLot.LOT_COLOR_BROWN);
        addRegularField(2, COMMUNITY_CHEST, COMMUNITY_CHEST_DESC, BoardField.BOARD_COMMUNITY_CHEST);
        addLotField(3, BROWN_2_NAME, 60, 50, new int[]{4, 20, 60, 180, 320, 450}, BoardLot.LOT_COLOR_BROWN);
        addRegularField(4, INCOME_TAX_NAME, INCOME_TAX_DESC, BoardField.BOARD_INCOME_TAX);
        addRailField(5, RAIL_1_NAME, RAIL_COST);
        addLotField(6, TEAL_1_NAME, 100, 50, new int[]{6, 30, 90, 270, 400, 550}, BoardLot.LOT_COLOR_TEAL);
        addRegularField(7, CHANCE_CARD, CHANCE_CARD_DESC, BoardField.BOARD_CHANCE);
        addLotField(8, TEAL_2_NAME, 100, 50, new int[]{6, 30, 90, 270, 400, 550}, BoardLot.LOT_COLOR_TEAL);
        addLotField(9, TEAL_3_NAME, 120, 50, new int[]{8, 40, 100, 300, 450, 600}, BoardLot.LOT_COLOR_TEAL);
        addRegularField(10, JAIL, JAIL_DESC, BoardField.BOARD_FIELD_JAIL);
        addLotField(11,PINK_1_NAME, 140, 100, new int[]{10, 50, 150, 450, 625, 750}, BoardLot.LOT_COLOR_PINK);
        addUtilityField(12, ELECTRIC_NAME, 150, ELECTRIC_DESC);
        addLotField(13, PINK_2_NAME, 140, 100, new int[]{10, 50, 150, 450, 625, 750}, BoardLot.LOT_COLOR_PINK);
        addLotField(14, PINK_3_NAME, 160, 100, new int[]{12, 60, 180, 500, 700, 900}, BoardLot.LOT_COLOR_PINK);
        addRailField(15, RAIL_2_NAME, RAIL_COST);
        addLotField(16, ORANGE_1_NAME, 180, 100, new int[]{14, 70, 200, 550, 750, 950}, BoardLot.LOT_COLOR_ORANGE);
        addRegularField(17, CHANCE_CARD, CHANCE_CARD_DESC, BoardLot.BOARD_CHANCE);
        addLotField(18, ORANGE_2_NAME, 180, 100, new int[]{14, 70, 200, 550, 750, 950}, BoardLot.LOT_COLOR_ORANGE);
        addLotField(19, ORANGE_3_NAME, 200, 100, new int[]{16, 80, 220, 600, 800, 1000}, BoardLot.LOT_COLOR_ORANGE);
        addRegularField(20, FREE_PARKING, FREE_PARKING_DESC, BoardLot.BOARD_FIELD_FREE_PARKING);
        addLotField(21, RED_1_NAME, 220, 150, new int[]{18, 90, 250, 700, 875, 1050}, BoardLot.LOT_COLOR_RED);
        addRegularField(22, CHANCE_CARD, CHANCE_CARD_DESC, BoardLot.BOARD_CHANCE);
        addLotField(23, RED_2_NAME, 220, 150, new int[]{18, 90, 250, 700, 875, 1050}, BoardLot.LOT_COLOR_RED);
        addLotField(24, RED_3_NAME, 240, 150, new int[]{20, 100, 300, 750, 925, 1100}, BoardLot.LOT_COLOR_RED);
        addRailField(25, RAIL_3_NAME, RAIL_COST);
        addLotField(26, YELLOW_1_NAME, 260, 150, new int[]{22, 110, 330, 800, 975, 1150}, BoardLot.LOT_COLOR_YELLOW);
        addLotField(27, YELLOW_2_NAME, 260, 150, new int[]{22, 110, 330, 800, 975, 1150}, BoardLot.LOT_COLOR_YELLOW);
        addUtilityField(28, WATER_NAME, 150, WATER_DESC);
        addLotField(29, YELLOW_3_NAME, 280, 150, new int[]{22, 120, 360, 850, 1025, 1200}, BoardLot.LOT_COLOR_YELLOW);
        addRegularField(30, GO_JAIL, GO_JAIL_DESC, BoardField.BOARD_FIELD_GO);
        addLotField(31, GREEN_1_NAME, 300, 200, new int[]{26, 130, 390, 900, 1100, 1275}, BoardLot.LOT_COLOR_GREEN);
        addLotField(32, GREEN_2_NAME, 300, 200, new int[]{26, 130, 390, 900, 1100, 1275}, BoardLot.LOT_COLOR_GREEN);
        addRegularField(33, COMMUNITY_CHEST, COMMUNITY_CHEST_DESC, BoardField.BOARD_COMMUNITY_CHEST);
        addLotField(34, GREEN_3_NAME, 320, 200, new int[]{28, 150, 450, 1000, 1200, 1400}, BoardLot.LOT_COLOR_GREEN);
        addRailField(35, RAIL_4_NAME, RAIL_COST);
        addRegularField(36, CHANCE_CARD, CHANCE_CARD_DESC, BoardField.BOARD_CHANCE);
        addLotField(37, BLUE_1_NAME, 350, 200, new int[]{35, 175, 500, 1100, 1300, 1500}, BoardLot.LOT_COLOR_BLUE);
        addRegularField(38, SUPER_TAX_NAME, SUPER_TAX_DESC, BoardField.BOARD_FIELD_SUPER_TAX);
        addLotField(39, BLUE_2_NAME, 400, 200, new int[]{50, 200, 600, 1400, 1700, 2000}, BoardLot.LOT_COLOR_BLUE);
    }
    
    public void printStreets() {
        for (Integer color : streets.keySet()) {
            for (BoardProperty bp : streets.get(color)) {
                System.out.println(color + ":" + bp.getFieldName() + ":" + bp.getVacancy());
            }
        }
    }
    
    public static void main(String args[]) {
        MonopolyGameBoard gb = new MonopolyGameBoard();
        gb.initiateBoard();
        gb.addPlayer("Player1");
        gb.addPlayer("Player2");
        gb.addPlayer("Player3");
        
        Player tmpPlayer;
        tmpPlayer = gb.getCurrentPlayer();
        tmpPlayer.setCashBalance(10000);
        
        gb.purchaseLot(tmpPlayer, (BoardLot)gb.getFieldBoardPosition(1));
        gb.purchaseLot(tmpPlayer, (BoardLot)gb.getFieldBoardPosition(3));
//        System.out.println(tmpPlayer.getPlayerName() + " Position is : "+tmpPlayer.getBoardPosition());
        tmpPlayer.printLot();
        BoardLot bl = (BoardLot)gb.getFieldBoardPosition(1);
//        System.out.println(bl.getRentCost());
        tmpPlayer.setBoardPosition(3);
        tmpPlayer = gb.getCurrentPlayer();
        tmpPlayer = gb.getCurrentPlayer();
        tmpPlayer = gb.getCurrentPlayer();
        
//        System.out.println(tmpPlayer.getPlayerName() + " Position is : "+tmpPlayer.getBoardPosition());
//        gb.printStreets();
        System.out.println(gb.isStreetKing(tmpPlayer, BoardLot.LOT_COLOR_BROWN));
    }
}
