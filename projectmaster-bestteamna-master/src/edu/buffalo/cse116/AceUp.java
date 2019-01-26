package edu.buffalo.cse116;

import java.util.ArrayList;

public class AceUp extends Game {
	/**
	 * Deck the game is played with
	 */
	private Deck deck = new Deck();

	/**
	 * ArrayList of Tableau Cells
	 */
	private ArrayList<Cell> TC = new ArrayList<Cell>();

	/**
	 * ArrayList of Home Cells
	 */
	private ArrayList<Cell> HC = new ArrayList<Cell>();

	/**
	 * ArrayList of all the top cards in each tableau cell
	 */
	private ArrayList<CardPile> topCards = new ArrayList<CardPile>();
	/*
	 * Boolean that determines if there is an empty tableau cell
	 */
	private boolean thereIsEmpty;

	/**
	 * Constructor for Ace's Up game
	 */
	public AceUp() {
		initialize();
		setTopCards();
	}

	/**
	 * Initializes the Ace's Up game.
	 */
	public void initialize() {
		deck.shuffle();
		for (int i = 0; i < 4; i++) {
			CardPile pile = deck.Draw(1);
			Cell c = new TableauCell(pile);
			topCards.add(pile);
			TC.add(c);
		}

		ArrayList<Card> huh = new ArrayList<Card>();
		CardPile pile = new CardPile(huh);
		Cell c = new HomeCell(pile);
		HC.add(c);

	}

	/**
	 * Checks the top card of each tableau pile and compares with input card to
	 * see if input card can be removed. If it can removed, the input card is
	 * moved to the home pile.
	 * 
	 * @param card
	 *            input card that is checked if it can be removed
	 * @return true if input card can be removed, false otherwise
	 */
	public boolean canRemove(Card card) {
		/*
		 * checks with topCards and see if it can remove, including the
		 * exception of ace rule same suit, and rank is bigger or is ACE. card
		 * moves to homepile setTopCards at the end and returns true else false
		 */
		boolean removable = false;
		if (card.isAce()) {
			return false;
		}
		for (int i = 0; i < 4; i++) {
			if (!topCards.get(i).isEmpty()) {
				if (card.isSameSuit(topCards.get(i).getLastCard())) {
					if (topCards.get(i).getLastCard().isAce()) {
						removable = true;
					} else if (card.getRank() < topCards.get(i).getLastCard().getRank()) {
						removable = true;
					}
				}
			}
		}
		if (removable) {
			return true;
		}
		return false;
	}

	/*
	 * Checks if a tableau cell is empty
	 */
	public boolean emptyInTop() {
		for (int i = 0; i < 4; i++) {
			if (topCards.get(i).isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Moves the input card to another location. First checks if input card is
	 * removable, if it is, move to home cell. Otherwise, check for an empty
	 * pile. If there is, move to empty tableau cell.
	 * 
	 * @param card
	 *            input card that is being moved
	 */
	public void move(Card card) {

		// check for empty piles
		thereIsEmpty = false;

		ArrayList<Boolean> emptyAt = new ArrayList<Boolean>();
		for (int i = 0; i < 4; i++) {
			if (topCards.get(i).isEmpty()) {
				emptyAt.add(i, true);
				thereIsEmpty = true;
			} else {
				emptyAt.add(i, false);
			}
		}

		// find where the input card is at
		int indexOf = 0;
		for (int i = 0; i < 4; i++) {
			if (!topCards.get(i).isEmpty()) {
				if (topCards.get(i).getLastCard().compareTo(card)) {
					indexOf = i;
				}
			}
		}

		// if removable, remove from TC, add to HC
		if (canRemove(card)) {
			HC.get(0).addLastCard(TC.get(indexOf));
			TC.get(indexOf).removeLastCard();
			setTopCards();
		}

		// if there is empty, move to first empty(left to right)
		else {
			if (thereIsEmpty) {
				int emptyIdx = emptyAt.indexOf(true);
				TC.get(emptyIdx).addLastCard(TC.get(indexOf));
				TC.get(indexOf).removeLastCard();
				setTopCards();
			}
		}
		setTopCards();

	}

	/**
	 * Deals cards to each tableau pile and sets the top card of each pile to
	 * the newly distributed card.
	 */
	public void drawCard() {
		// deals cards and then setTop Cards
		for (int i = 0; i < 4; i++) {
			CardPile pile = deck.Draw(1);
			TC.get(i).getCardPile().addPile(pile);
		}

		setTopCards();

	}

	/**
	 * Checks if the ArrayList at index a is empty
	 * 
	 * @param c
	 *            ArrayList being checked
	 * 
	 * @param index
	 *            of cell being checked
	 */
	@Override
	public boolean isEmpty(ArrayList<Cell> c, int a) {
		return c.get(a).isEmpty();
	}

	/**
	 * Checks if the last card of the ArrayList is an Ace
	 * 
	 * @param c
	 *            ArrayList being checked
	 * 
	 * @param index
	 *            of cell being checked
	 */
	@Override
	public boolean isAce(ArrayList<Cell> c, int a) {
		return c.get(a).getLastCard().isAce();
	}

	/**
	 * Returns the last card of the ArrayList
	 * 
	 * @param c
	 *            ArrayList being checked
	 * 
	 * @param a
	 *            index of cell being checked
	 */
	@Override
	public Card lastCard(ArrayList<Cell> c, int a) {
		return c.get(a).getLastCard();
	}

	/**
	 * Returns an ArrayList of all the tableau cells
	 */
	public ArrayList<Cell> getTC() {
		return TC;
	}

	/**
	 * Returns the tableau cell at the input index
	 * 
	 * @param index
	 *            index at which tableau cell is located
	 */
	public Cell getTC(int index) {
		return TC.get(index);
	}

	/*
	 * Returns the deck
	 */
	@Override
	public Deck getDeck() {
		return deck;
	}

	/**
	 * Adds the top card in each tableau cell into an ArrayList, assuming the
	 * tableau cell is not empty.
	 */
	@Override
	public void setTopCards() {
		for (int i = 0; i < TC.size(); i++) {
			if (!(TC.get(i).isEmpty())) {

				ArrayList<Card> c = new ArrayList<Card>();
				c.add(TC.get(i).getCardPile().getLastCard());
				CardPile d = new CardPile(c);
				topCards.set(i, d);
			} else {
				ArrayList<Card> c = new ArrayList<Card>();
				CardPile p = new CardPile(c);
				topCards.set(i, p);
			}
		}

	}

	/**
	 * Returns the home cell at index a
	 * 
	 * @param a
	 *            index at which the home cell is located
	 */
	@Override
	public Cell getHC(int a) {
		// TODO Auto-generated method stub
		return HC.get(a);
	}

	/**
	 * Method implemented by extending the game class
	 */
	@Override
	public Cell getFC(int a) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method implemented by extending the game class
	 */
	@Override
	public void move(int a, int b, boolean x) {
		// TODO Auto-generated method stub

	}

	/**
	 * Method implemented by extending the game class
	 */
	@Override
	public void move(int a, int b, int c) {
		// TODO Auto-generated method stub

	}

	/**
	 * Returns an ArrayList of all the home cells
	 */
	@Override
	public ArrayList<Cell> getHC() {
		// TODO Auto-generated method stub
		return HC;
	}

	/**
	 * Method implemented by extending the game class
	 */
	@Override
	public ArrayList<Cell> getFC() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method implemented by extending the game class
	 */
	@Override
	public boolean allTC() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Method implemented by extending the game class
	 */
	@Override
	public boolean allHC() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Method implemented by extending the game class
	 */
	@Override
	public boolean allFC() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Overrides the current tableau cells with another ArrayList of tableau
	 * cells
	 * 
	 * @param c
	 *            index at which the home cell is located
	 */
	@Override
	public void setTC(ArrayList<Cell> C) {
		TC = C;

	}

	/**
	 * Method implemented by extending the game class
	 */
	@Override
	public void setFC(ArrayList<Cell> C) {
		// TODO Auto-generated method stub

	}

	/**
	 * Method implemented by extending the game class
	 */
	@Override
	public void setHC(ArrayList<Cell> C) {
		// TODO Auto-generated method stub

	}

	/**
	 * Returns the size of a tableau cell or home cell
	 */
	@Override
	public int getSize(String type, int a) {
		if (type.equals("TC"))
			return TC.get(a - 1).getCardPile().getCount();
		else
			return HC.get(a - 1).getCardPile().getCount();
	}

	/**
	 * Method implemented by extending the game class
	 */
	@Override
	public int methodComparison(Cell c, Cell d) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Sets input card as the top card of the tableau cell at input index.
	 * 
	 * @param card
	 *            input card that is being set
	 * @param index
	 *            index of the tableau cell
	 */
	@Override
	public void setTC(Card c, int index) {
		TC.get(index).removeLastCard();
		ArrayList<Card> temp = new ArrayList<Card>();
		temp.add(c);
		CardPile p = new CardPile(temp);
		TC.get(index).Add(p);
		setTopCards();

	}
}
