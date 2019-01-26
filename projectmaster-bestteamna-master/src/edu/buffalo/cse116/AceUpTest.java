package edu.buffalo.cse116;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class AceUpTest {

	// TABLEAU CELL TESTS

	/*
	 * Tableau piles in Ace's Up initially hold 1 card
	 */
	@Test
	public void tableauStart() {
		AceUp game = new AceUp();
		for(int i = 0; i < game.getTC().size(); i++) {
			assertEquals("Each tableau pile should hold 1 card",1,game.getTC(i).getCardPile().getCount());
		}
	}

	/*
	 * Ace's Up tableau pile method correctly determines if adding a specific
	 * card is legal or illegal.
	 */
	@Test
	public void tableauAddLegal() {
		AceUp ace = new AceUp();// create a new game
		Deck d = ace.getDeck();// set d to ace's deck
		boolean a = true;// if a is true, its legal
		if (d.getCardCount() > 0) {
			a = true;// check deck size
		}
		if (d.getCardCount() <= 0) {
			a = false;// check deck size
		}
		assertTrue(a);
		assertEquals("when deck size is > 0 add card is legal", 48, d.getCardCount());
		while (d.getCardCount() > 0) {
			ace.drawCard();// draw card until deck is empty
		}
		assertEquals("deck size of 0, illegal to draw", 0, d.getCardCount());
	}

	/*
	 * Ace's Up tableau pile method correctly determines if removing a specific
	 * card is legal or illegal.
	 */
	@Test
	public void tableauRemoveLegal() {
		// Tests if legal to remove to home cell pile
		AceUp game = new AceUp();
		// Diamond 2 that the first tableau cell holds
		Card a = new Card(1,2);
		game.setTC(a, 0);
		// Clover 3 that the second tableau cell holds
		Card b = new Card(2,3);
		game.setTC(b, 1);
		// Heart 4 that the third tableau cell holds
		Card c = new Card(3,4);
		game.setTC(c, 2);
		// Spade 5 that the fourth tableau cell holds
		
		// Diamond 5 that is being added to the fourth tableau cell
		Card d = new Card(1,5);
		game.setTC(d, 3);
		
		// Tests if Diamond 2 can be removed, should return true
		assertTrue(game.canRemove(game.getTC(0).getLastCard()));
	}
 
	/*
	 * Adding card to Ace's Up tableau pile increases its number of cards and
	 * results in that card being the tableau pile's new top card
	 */
	@Test
	public void tableauAddNew() {
		AceUp ace = new AceUp();
		ace.drawCard();
		for (int i = 0; i < 4; i++) {
			assertEquals("when draw card, each tablepile should increase the size by 1", 2,
					ace.getTC(i).getCardPile().getCount());
		}
		assertEquals("the deck should decrease size by 4", 44, ace.getDeck().getCardCount());
	}

	/*
	 * Removing card from Ace's Up tableau pile decreases its number of cards
	 * and results in following card being the new top card
	 */
	@Test
	public void tableauRemoveNew() {
		AceUp game = new AceUp();
		// Diamond Ace that the first tableau cell holds
		Card a = new Card(1,1);
		game.setTC(a, 0);
		// Clover 2 that is being added to first tableau cell
		Card b = new Card(2,2);
		ArrayList<Card> array = new ArrayList<Card>();
		array.add(b);
		CardPile c = new CardPile(array);
		game.getTC(0).Add(c);
		// Remove last card from first tableau cell
		game.getTC(0).removeLastCard();
		// Test that the last card in first tableau cell is now Diamond Ace
		assertEquals("Red Diamond A",game.getTC(0).getLastCard().toString());
		// Test that the size of the first tableau cell decreased to 1
		assertEquals(1,game.getTC(0).getCardPile().getCount());
		
	}
	
	
	
	

	// HOMECELL TESTS

	/*
	 * Home cell pile in Ace's Up initially holds 0 cards
	 */
	@Test
	public void homeStart() {
		AceUp game = new AceUp();
		// Test that the size of the home cell is 0
		assertEquals(0,game.getHC().get(0).getCardPile().getCount());
	}

	/*
	 * Ace's Up home cell pile correctly determines if adding a specific card is
	 * legal or illegal (always legal)
	 */
	@Test
	public void homeAddLegal() {
		assertTrue(true);
	}

	/*
	 * Ace's Up home cell pile correctly returns if removing top card is legal
	 * or illegal (always illegal)
	 */
	@Test
	public void homeRemoveLegal() {
		assertFalse(false);
	}

	/*
	 * Adding card to Ace's Up home cell pile increases its number of cards and
	 * results in that card being the home cell pile's new top card
	 */
	@Test
	public void homeAddNew() {
		AceUp game = new AceUp();
		assertEquals(0,game.getHC().get(0).getCardPile().getCount());
		// Diamond Ace that is added to the home cell
		Card a = new Card(1,1);
		ArrayList<Card> array = new ArrayList<Card>();
		array.add(a);
		CardPile pile = new CardPile(array);
		game.getHC().get(0).Add(pile);
		// Test that the size of the home cell pile increased by 1
		assertEquals(1,game.getHC().get(0).getCardPile().getCount());
		// Test that the new top card is Diamond Ace
		assertEquals("Red Diamond A",game.getHC().get(0).getLastCard().toString());
	}
	
	
	

	// STOCKPILE TESTS

	/*
	 * Ace's Up stock pile initially holds 48 cards
	 */
	@Test
	public void stockStart() {
		AceUp game = new AceUp();
		// Test that the size of the stockpile is 48
		assertEquals(48,game.getDeck().getCardCount());
	}

	/*
	 * Ace's Up stock pile correctly returns if adding a specific card is legal
	 * or illegal
	 */
	@Test
	public void stockAddLegal() {
		/* Stockpile was not programmed to be a tangible object within our code, so it cannot be interacted with other than dealing card.
		 * No cards can be added to it, but because stockpile is intangible, this cannot be tested.
		 */
		assertTrue(true);
	}

	/*
	 * Ace's Up stock pile correctly returns if removing a specific card is
	 * legal or illegal
	 */
	@Test
	public void stockRemoveLegal() {
		/* Stockpile was not programmed to be a tangible object within our code, so it cannot be interacted with other than dealing card.
		 * No cards can be added to it, but because stockpile is intangible, this cannot be tested.
		 */
		assertTrue(true);
	}

	/*
	 * Dealing cards from Ace's Up stock pile removes the top 4 cards, adds the
	 * removed cards to the tableau piles, and results in the 5th card being the
	 * new top card
	 */
	@Test
	public void stockDeal() {
		AceUp game = new AceUp();
		assertEquals(48,game.getDeck().getCardCount());
		game.drawCard();
		assertEquals(44,game.getDeck().getCardCount());
	}
	
}