package edu.buffalo.cse116;

import java.util.ArrayList;

/**
 * @author junjiezheng
 *
 */
public abstract class Game {
	/* 
	 * initialize the game
	 */
	public abstract void initialize();

	/*
	 *return the deck
	 */
	public abstract Deck getDeck();

	/*
	 *  TC getter junit
	 */
	public abstract Cell getTC(int a);

	/*
	 *  hc getter junit
	 */
	public abstract Cell getHC(int a);

	/*
	 *  fc getterjunit
	 */
	public abstract Cell getFC(int a);

	/*
	 *  move method for game
	 */
	public abstract void move(int a, int b, boolean x);

	/*
	 * move metthod for game
	 */
	public abstract void move(int a, int b, int c);

	/*
	 *  HC getter
	 */
	public abstract ArrayList<Cell> getHC();

	/*
	 *  tc getter
	 */
	public abstract ArrayList<Cell> getTC();

	/*
	 *  fc getter
	 */
	public abstract ArrayList<Cell> getFC();

	/*
	 *  to test is tc
	 */
	public abstract boolean allTC();

	/*
	 * to test is hc
	 */
	public abstract boolean allHC();

	/*
	 *  to test is fc
	 */
	public abstract boolean allFC();

	/*
	 *  TC setter
	 */
	public abstract void setTC(ArrayList<Cell> C);

	/*
	 *  fc setter
	 */
	public abstract void setFC(ArrayList<Cell> C);

	/*
	 *  hc setter
	 */
	public abstract void setHC(ArrayList<Cell> C);

	/*
	 *  tc setter
	 */
	public abstract void setTC(Card c, int index);

	/*
	 *  get size
	 */
	public abstract int getSize(String type, int a);

	/*
	 *  comparator
	 */
	public abstract int methodComparison(Cell c, Cell d);

	/*
	 *  check is move is legal or not
	 */
	public abstract boolean canRemove(Card c);

	/*
	 *  move method for ace up
	 */
	public abstract void move(Card c);

	/*
	 *  to draw card in ace up
	 */
	public abstract void drawCard();

	/*
	 *  set the top card for ace up
	 */
	public abstract void setTopCards();

	/*
	 *  check is tc empty
	 */
	public abstract boolean isEmpty(ArrayList<Cell> c, int a);

	/*
	 *  check is the card ace
	 */
	public abstract boolean isAce(ArrayList<Cell> c, int a);

	/*
	 *  arraylist of all lastcards
	 */
	public abstract Card lastCard(ArrayList<Cell> c, int a);

	/*
	 *  check is all top empty
	 */
	public abstract boolean emptyInTop();
}
