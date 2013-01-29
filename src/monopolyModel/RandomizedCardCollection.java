/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author tonychiu
 */
public class RandomizedCardCollection {

    private ArrayList<RandomizedCards> cardCollection;
    private Set<Integer> addedIndex;            // Set to make sure no duplicated cards are added
    private int cardPosition;
    
    public RandomizedCardCollection() {
        cardCollection = new ArrayList<>();
        addedIndex = new HashSet();
        cardPosition = 0;
    }
    
    public void addCard(RandomizedCards card) throws IllegalArgumentException{
        if (addedIndex.contains(card.INDEX)) {
            throw new IllegalArgumentException("Error : card with index "+card.INDEX+" has been added; "
                    + "duplicate indecies not allowed");
        }
        cardCollection.add(card);
        addedIndex.add(card.INDEX);
    }
    
    public void shuffleDeck() {
        Collections.shuffle(cardCollection);
    }
    
    public RandomizedCards drawCard() {
        RandomizedCards card;
        // Reset cardPosition index if larger than deck size
        if (cardPosition >= cardCollection.size()) {
            cardPosition = 0;
        }
        
        card = cardCollection.get(cardPosition);
        if (card.isTaken()) {
            cardPosition++;
            return drawCard();
        } else {
            card.setTaken(true);
            return card;
        }
    }
    
    // TODO : write this method
    public void returnCard() {
        
    }
    
    public void checkState() {
        for (RandomizedCards rc : cardCollection) {
            System.out.println(rc.INDEX + ":"+rc.isTaken());
        }
    }
    
    public static void main(String args[]) {
        RandomizedCardCollection rcc = new RandomizedCardCollection();
        rcc.addCard(new RandomizedCards(0, "DSADAS"));
        rcc.addCard(new RandomizedCards(1, "DSADAS"));
        rcc.addCard(new RandomizedCards(2, "DSADAS"));
        rcc.addCard(new RandomizedCards(3, "DSADAS"));
        
        rcc.shuffleDeck();
        
        rcc.drawCard();
        
        rcc.checkState();        
    }
}
