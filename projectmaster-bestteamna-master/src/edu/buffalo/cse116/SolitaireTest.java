package edu.buffalo.cse116;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SolitaireTest {

	private Deck deck = new Deck();
	private BakersDozen bd = new BakersDozen();
	private FreeCellSolitaire game = new FreeCellSolitaire();

	@Test
	public void testInitializeBDG() {
		assertEquals("deck should return 52 cards", 52, deck.getCardCount());
		assertTrue("Cell should be TC", bd.allTC());
		int count = 0;
		for (int i = 0; i < bd.getTC().size(); i++) {
			count = bd.getTC().get(i).getCardPile().getCount();

			assertEquals("BDGame should initially holds 4 cards in a tc", 4, count);
		}

	}

	@Test
	public void testInitializeFCG() {
		assertTrue("Cell should be TC", game.allTC());
		int count = 0;
		for (int i = 0; i < 4; i++) {
			count = game.getTC().get(i).getCardPile().getCount();
			assertEquals("FCGame should has 6 or 7 card in TC", 6, count);
		}
		int count2 = 0;
		for (int i = 4; i < 8; i++) {
			count2 = game.getTC().get(i).getCardPile().getCount();
			assertEquals("FCGame should has 6 or 7 card in TC", 7, count2);
		}

	}

	@Test
	public void testBDGAdding() {

		Card ace = new Card(1, 1);
		Card twoo = new Card(3, 2);
		ArrayList<Card> temp = new ArrayList<Card>();
		temp.add(ace);
		ArrayList<Card> temp2 = new ArrayList<Card>();
		temp2.add(twoo);
		CardPile a = new CardPile(temp);
		CardPile b = new CardPile(temp2);
		Cell tbc = new TableauCell(a);
		Cell tbc2 = new TableauCell(b);
		ArrayList<Cell> fin = new ArrayList<Cell>();
		fin.add(tbc);
		fin.add(tbc2);
		BakersDozen game = new BakersDozen();
		game.setTC(fin);
		game.move(1, 2, false);
		assertEquals("Baker's Dozen tableau pile correctly determines if adding a specific card is legal or illegal ",
				2, game.getTC().get(1).getCardPile().toArray().size());
		assertEquals("Baker's Dozen tableau pile correctly returns if removing top card is legal or illegal", 0,
				game.getTC().get(0).getCardPile().toArray().size());
	}

	@Test
	public void testFCGAdding() {
		Card ace = new Card(1, 1);
		Card twoo = new Card(2, 2);
		ArrayList<Card> temp = new ArrayList<Card>();
		temp.add(ace);
		ArrayList<Card> temp2 = new ArrayList<Card>();
		temp2.add(twoo);
		CardPile a = new CardPile(temp);
		CardPile b = new CardPile(temp2);
		Cell tbc = new TableauCell(a);
		Cell tbc2 = new TableauCell(b);
		ArrayList<Cell> fin = new ArrayList<Cell>();
		fin.add(tbc);
		fin.add(tbc2);
		FreeCellSolitaire game = new FreeCellSolitaire();
		game.setTC(fin);
		game.move(1, 2, 1);
		assertEquals("Freecell tableau pile correctly determines if adding a specific card is legal or illegall", 2,
				game.getTC().get(1).getCardPile().toArray().size());
		assertEquals("Freecell tableau pile correctly returns if removing top card is legal or illegal", 0,
				game.getTC().get(0).getCardPile().toArray().size());
	}

	@Test
	public void testBDGRemovingLegal() {
		this.bd = new BakersDozen();
		for (int i = 0; i < bd.getTC().size(); i++) {
			bd.getTC().get(i).getCardPile().removeLastCard();
			assertEquals("BD tableau pile correctly returns if removing top card is legal or illegal", 3,
					bd.getTC().get(i).getCardPile().getCount());

		}

	}

	@Test
	public void FCGRemoving() {
		game = new FreeCellSolitaire();
		for (int i = 0; i < 4; i++) {
			Card a = game.getTC().get(i).getCardPile().toArray().get(3);
			game.getTC().get(i).removeLastCard();
			assertEquals("BD tableau pile correctly returns if removing top card is legal or illegal", 5,
					game.getTC().get(i).getCardPile().getCount());

		}
	}

	@Test
	public void testBDGTopCard() {
		Card ace = new Card(1, 1);
		Card twoo = new Card(3, 2);
		ArrayList<Card> temp = new ArrayList<Card>();
		temp.add(ace);
		ArrayList<Card> temp2 = new ArrayList<Card>();
		temp2.add(twoo);
		CardPile a = new CardPile(temp);
		CardPile b = new CardPile(temp2);
		Cell tbc = new TableauCell(a);
		Cell tbc2 = new TableauCell(b);
		Card top = tbc.getLastCard();
		ArrayList<Cell> fin = new ArrayList<Cell>();
		fin.add(tbc);
		fin.add(tbc2);
		BakersDozen game = new BakersDozen();
		game.setTC(fin);

		game.move(1, 2, false);

		assertEquals(
				"Adding card to Freecell tableau pile increases its number of cards and results in that card being the tableau pile's new top card",
				top, game.getTC().get(1).getLastCard());

	}

	@Test
	public void testFCGTopCard() {
		Card ace = new Card(1, 1);
		Card twoo = new Card(2, 2);
		ArrayList<Card> temp = new ArrayList<Card>();
		temp.add(ace);
		ArrayList<Card> temp2 = new ArrayList<Card>();
		temp2.add(twoo);
		CardPile a = new CardPile(temp);
		CardPile b = new CardPile(temp2);
		Cell tbc = new TableauCell(a);
		Cell tbc2 = new TableauCell(b);
		Card top = tbc.getLastCard();
		ArrayList<Cell> fin = new ArrayList<Cell>();
		fin.add(tbc);
		fin.add(tbc2);
		FreeCellSolitaire game = new FreeCellSolitaire();
		game.setTC(fin);
		game.move(1, 2, 1);
		assertEquals(
				"Adding card to Baker's Dozen tableau pile increases its number of cards and results in that card being the tableau pile's new top card",
				top, game.getTC().get(1).getLastCard());

	}

	@Test
	public void newFCGTopCard() {
		Card ace = new Card(1, 1);
		Card twoo = new Card(1, 2);
		Card three = new Card(1, 3);
		ArrayList<Card> temp = new ArrayList<Card>();
		temp.add(ace);
		temp.add(three);
		ArrayList<Card> temp2 = new ArrayList<Card>();
		temp2.add(twoo);
		CardPile a = new CardPile(temp);
		CardPile b = new CardPile(temp2);
		Cell tbc = new TableauCell(a);
		Cell tbc2 = new TableauCell(b);
		ArrayList<Cell> fin = new ArrayList<Cell>();
		fin.add(tbc);
		fin.add(tbc2);
		FreeCellSolitaire game = new FreeCellSolitaire();
		game.setTC(fin);
		game.move(1, 2, 1);
		Card top = tbc.getLastCard();
		assertEquals(
				"Removing card from Freecell tableau pile decreases its number of cards and results in following card being the new top card",
				top, game.getTC().get(0).getLastCard());

	}

	@Test
	public void newTopCardBDG() {
		Card ace = new Card(1, 1);
		Card twoo = new Card(3, 2);
		Card three = new Card(1, 2);
		ArrayList<Card> temp = new ArrayList<Card>();
		temp.add(ace);
		temp.add(three);
		ArrayList<Card> temp2 = new ArrayList<Card>();
		temp2.add(twoo);
		CardPile a = new CardPile(temp);
		CardPile b = new CardPile(temp2);
		Cell tbc = new TableauCell(a);
		Cell tbc2 = new TableauCell(b);

		ArrayList<Cell> fin = new ArrayList<Cell>();
		fin.add(tbc);
		fin.add(tbc2);
		BakersDozen game = new BakersDozen();
		game.setTC(fin);
		game.move(1, 2, false);
		Card top = tbc.getLastCard();
		assertEquals(
				"Removing card from Baker's Dozen tableau pile decreases its number of cards and results in following card being the new top card",
				top, game.getTC().get(0).getLastCard());
	}
	
	@Test
	public void testFC_init1() {
		// Homecell piles in Freecell initially hold 0 cards
		FreeCellSolitaire game = new FreeCellSolitaire();
		boolean allEmpty = true;
		ArrayList<Cell> hc = game.getHC();
		for (int i = 0; i < hc.size(); i++) {
			if (hc.get(i).getCardPile().getCount() > 0) {
				allEmpty = false;
			}
		}
		assertEquals("Homecell piles in Freecell initially hold 0 cards", true, allEmpty);
	}

	@Test
	public void testBD_init() {
		// Homecell piles in Baker's Dozen initially hold 0 cards
		BakersDozen game = new BakersDozen();
		boolean allEmpty = true;
		ArrayList<Cell> hc = game.getHC();
		for (int i = 0; i < hc.size(); i++) {
			if (hc.get(i).getCardPile().getCount() > 0) {
				allEmpty = false;
			}
		}
		assertEquals("Homecell piles in Bakers Dozen initially hold 0 cards", true, allEmpty);
	}

	@Test
	public void testFC_addLegal1() {
		// Freecell homecell pile correctly determines if adding a specific card
		// is legal or illegal
		FreeCellSolitaire game = new FreeCellSolitaire();
		// legal movement: isHome
		Card ace = new Card(1, 1);
		Card two = new Card(1, 2);
		// making variables
		ArrayList<Card> a = new ArrayList<Card>();
		ArrayList<Card> b = new ArrayList<Card>();
		a.add(two);
		a.add(ace);
		CardPile pile1 = new CardPile(a);
		CardPile pile2 = new CardPile(b);
		Cell home1 = new HomeCell(pile2);
		Cell table1 = new TableauCell(pile1);

		ArrayList<Cell> all1 = new ArrayList<Cell>();
		ArrayList<Cell> all2 = new ArrayList<Cell>();
		all1.add(table1);
		all2.add(home1);

		game.setHC(all2);
		game.setTC(all1);

		// legal
		game.move(1, 1, 3);
		assertEquals("Freecell homecell pile correctly determines if adding a specific card", 2,
				game.getHC().get(0).getCardPile().getCount());

		// illegal
		game.move(1, 1, 3);
		assertEquals("Freecell homecell pile correctly determines if adding a specific card", 2,
				game.getHC().get(0).getCardPile().getCount());
		// size should not be modified

	}

	@Test
	public void testBD_addLegal() {
		// Baker's Dozen homecell pile correctly determines if adding a specific
		// card is legal or illegal
		// is home
		BakersDozen game = new BakersDozen();
		// legal movement: isHome
		Card ace = new Card(1, 1);
		Card two = new Card(1, 2);
		// Card three = new Card(1,3);
		// Card four = new Card(1,4);
		// making varibles
		ArrayList<Card> a = new ArrayList<Card>();
		ArrayList<Card> b = new ArrayList<Card>();
		a.add(two);
		a.add(ace);
		CardPile pile1 = new CardPile(a);
		CardPile pile2 = new CardPile(b);
		Cell home1 = new HomeCell(pile2);
		Cell table1 = new TableauCell(pile1);

		ArrayList<Cell> all1 = new ArrayList<Cell>();
		ArrayList<Cell> all2 = new ArrayList<Cell>();
		all1.add(table1);
		all2.add(home1);

		game.setHC(all2);
		game.setTC(all1);

		// legal
		game.move(1, 1, true);
		assertEquals("Freecell homecell pile correctly determines if adding a specific card", 2,
				game.getHC().get(0).getCardPile().getCount());

		// illegal
		game.move(1, 1, true);
		assertEquals("Freecell homecell pile correctly determines if adding a specific card", 2,
				game.getHC().get(0).getCardPile().getCount());
	}
	@Test
	public void testFC_extractLegal1() {
		// Freecell homecell pile correctly determines if removing a specific card
		// is legal or illegal
		FreeCellSolitaire game = new FreeCellSolitaire();
		// legal movement: isHome
		Card ace = new Card(1, 1);
		Card two = new Card(1, 2);
		// Card three = new Card(1,3);
		// Card four = new Card(1,4);
		// making varibles
		ArrayList<Card> a = new ArrayList<Card>();
		ArrayList<Card> b = new ArrayList<Card>();
		a.add(two);
		a.add(ace);
		CardPile pile1 = new CardPile(a);
		CardPile pile2 = new CardPile(b);
		Cell home1 = new HomeCell(pile2);
		Cell table1 = new TableauCell(pile1);

		ArrayList<Cell> all1 = new ArrayList<Cell>();
		ArrayList<Cell> all2 = new ArrayList<Cell>();
		all1.add(table1);
		all2.add(home1);

		game.setHC(all2);
		game.setTC(all1);

		// legal
		game.move(1, 1, 3);
		assertEquals("Freecell homecell pile correctly determines if adding a specific card", 0,
				game.getTC().get(0).getCardPile().getCount());

		// illegal
		game.move(1, 1, 3);
		assertEquals("Freecell homecell pile correctly determines if adding a specific card", 0,
				game.getTC().get(0).getCardPile().getCount());
		// size should not be modified

	}
	@Test
	public void testBD_extractLegal() {
		// Baker's Dozen homecell pile correctly returns if removing top card is
		// legal or illegal (e.g., always false)
		// Baker's Dozen homecell pile correctly determines if adding a specific
		// card is legal or illegal
		// is home
		BakersDozen game = new BakersDozen();
		// legal movement: isHome
		Card ace = new Card(1, 1);
		Card two = new Card(1, 2);
		// Card three = new Card(1,3);
		// Card four = new Card(1,4);
		// making varibles
		ArrayList<Card> a = new ArrayList<Card>();
		ArrayList<Card> b = new ArrayList<Card>();
		a.add(two);
		a.add(ace);
		CardPile pile1 = new CardPile(a);
		CardPile pile2 = new CardPile(b);
		Cell home1 = new HomeCell(pile2);
		Cell table1 = new TableauCell(pile1);

		ArrayList<Cell> all1 = new ArrayList<Cell>();
		ArrayList<Cell> all2 = new ArrayList<Cell>();
		all1.add(table1);
		all2.add(home1);

		game.setHC(all2);
		game.setTC(all1);

		// legal
		game.move(1, 1, true);
		assertEquals("Baker's Dozen homecell pile correctly determines if adding a specific card", 0,
				game.getTC().get(0).getCardPile().getCount());

		// illegal
		game.move(1, 1, true);
		assertEquals("Baker's Dozen homecell pile correctly determines if adding a specific card", 0,
				game.getTC().get(0).getCardPile().getCount());
	}
	
	@Test
	public void testFC_add1() {
		// Adding card to Freecell homecell pile increases its number of cards
		// and results in that card being the homecell pile's new top card
		
				// is legal or illegal
				FreeCellSolitaire game = new FreeCellSolitaire();
				// legal movement: isHome
				Card ace = new Card(1, 1);
				Card two = new Card(1, 2);
				// making variables
				ArrayList<Card> a = new ArrayList<Card>();
				ArrayList<Card> b = new ArrayList<Card>();
				a.add(two);
				a.add(ace);
				CardPile pile1 = new CardPile(a);
				CardPile pile2 = new CardPile(b);
				Cell home1 = new HomeCell(pile2);
				Cell table1 = new TableauCell(pile1);

				ArrayList<Cell> all1 = new ArrayList<Cell>();
				ArrayList<Cell> all2 = new ArrayList<Cell>();
				all1.add(table1);
				all2.add(home1);

				game.setHC(all2);
				game.setTC(all1);


				game.move(1, 1, 3);
				assertEquals("Adding to Freecell homecell pile increases its number of cards", 2,
						game.getHC().get(0).getCardPile().getCount());
				assertEquals("Freecell homecell pile correctly returns new top card", "Red Diamond 2",
						game.getHC().get(0).getCardPile().getLastCard().toString());

				
	}
	@Test
	public void testBD_add() {
		// Adding card to Baker's Dozen homecell pile increases its number of
		// cards and results in that card being the homecell pile's new top card

		BakersDozen game = new BakersDozen();
		// legal movement: isHome
		Card ace = new Card(1, 1);
		Card two = new Card(1, 2);
		// Card three = new Card(1,3);
		// Card four = new Card(1,4);
		// making varibles
		ArrayList<Card> a = new ArrayList<Card>();
		ArrayList<Card> b = new ArrayList<Card>();
		a.add(two);
		a.add(ace);
		CardPile pile1 = new CardPile(a);
		CardPile pile2 = new CardPile(b);
		Cell home1 = new HomeCell(pile2);
		Cell table1 = new TableauCell(pile1);

		ArrayList<Cell> all1 = new ArrayList<Cell>();
		ArrayList<Cell> all2 = new ArrayList<Cell>();
		all1.add(table1);
		all2.add(home1);

		game.setHC(all2);
		game.setTC(all1);


		game.move(1, 1, true);
		assertEquals("Adding to Freecell homecell pile increases its number of cards", 2,
				game.getHC().get(0).getCardPile().getCount());
		assertEquals("Freecell homecell pile correctly returns new top card", "Red Diamond 2",
				game.getHC().get(0).getCardPile().getLastCard().toString());

	}
	
	@Test
	public void testFC_init() {
		// Freecell piles in Freecell begin holding 0 cards
		FreeCellSolitaire f = new FreeCellSolitaire();
		assertEquals("Freecell piles should hold 0 cards", 0, f.getFC().get(0).getCardPile().toArray().size());
	}

	@Test
	public void testFC_addLegal() {
		// Freecell freecell pile correctly returns if adding a specific card is
		// legal or illegal (e.g., if the freecell pile is empty)
		FreeCellSolitaire a = new FreeCellSolitaire();
		a.move(1, 1, 2);
		// Legal move
		assertEquals("Freecell pile should have 1 card", 1, a.getFC().get(0).getCardPile().getCount());

		FreeCellSolitaire e = new FreeCellSolitaire();
		Card two = new Card(2, 2);
		ArrayList<Card> g = new ArrayList<Card>();
		g.add(two);
		CardPile pile1 = new CardPile(g);
		Cell free1 = new FreeCell(pile1);
		ArrayList<Cell> all5 = new ArrayList<Cell>();
		all5.add(free1);
		e.setFC(all5);
		e.move(1, 1, 2);
		// Illegal move
		assertEquals("Freecell pile should have 1 card", 1, e.getFC().get(0).getCardPile().getCount());
	}

	@Test
	public void testFC_extractLegal() {
		// Freecell freecell pile correctly returns if removing top card is
		// legal or illegal (e.g., if the freecell pile is NOT empty)
		FreeCellSolitaire x = new FreeCellSolitaire();
		FreeCellSolitaire y = new FreeCellSolitaire();
		Card two = new Card(2, 2);
		Card three = new Card(1, 3);
		Card four = new Card(1, 4);
		ArrayList<Card> a = new ArrayList<Card>();
		ArrayList<Card> b = new ArrayList<Card>();
		ArrayList<Card> c = new ArrayList<Card>();
		a.add(two);
		b.add(three);
		c.add(four);
		CardPile pile1 = new CardPile(a);
		CardPile pile2 = new CardPile(b);
		CardPile pile3 = new CardPile(c);
		Cell free1 = new FreeCell(pile1);
		Cell table1 = new TableauCell(pile2);
		Cell free2 = new FreeCell(pile3);
		ArrayList<Cell> all1 = new ArrayList<Cell>();
		ArrayList<Cell> all2 = new ArrayList<Cell>();
		ArrayList<Cell> all3 = new ArrayList<Cell>();
		all1.add(free1);
		all2.add(table1);
		all3.add(free2);
		x.setFC(all1);
		x.setTC(all2);
		y.setFC(all3);
		y.setTC(all2);
		x.move(1, 1, 4);
		// Legal move
		assertEquals("Freecell pile should have 0 cards", 0, x.getFC().get(0).getCardPile().getCount());
		y.move(1, 1, 4);
		// Illegal move
		assertEquals("Freecell pile should have 1 card", 1, y.getFC().get(0).getCardPile().getCount());
	}

	@Test
	public void testFC_add() {
		// Adding card to Freecell freecell pile increases its number of cards
		// and results in that card being the freecell pile's new top card
		FreeCellSolitaire c = new FreeCellSolitaire();
		Card first = c.getTC().get(0).getLastCard();
		c.move(1, 1, 2);
		assertEquals("Freecell pile size should be 1", 1, c.getFC().get(0).getCardPile().getCount());
		assertEquals("The card in tableau pile should be equal to new card in freecell pile", first,
				c.getFC().get(0).getLastCard());
	}

}
