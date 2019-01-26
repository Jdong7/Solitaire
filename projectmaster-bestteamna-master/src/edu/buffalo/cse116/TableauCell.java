package edu.buffalo.cse116;


public class TableauCell extends Cell{

	
	/**
	 * Tableau Cell constructor
	 * Specific rules depend on game
	 * No maximum size
	 * If empty, can be filled in Free Cell, but cannot be filled in Baker's Dozen
	 * Must have method that moves King to bottom get # of King and move to get[0]
	 * 
	 * @param pile The CardPile that the Tableau Cell holds
	 */
	public TableauCell(CardPile pile) {
		super(pile);
	}
	/**
	 * Returns true, because a tableau cell is a tableau cell
	 */
	@Override
	public boolean isTC() {
		return true;
	}
	/**
	 * Returns false, because a tableau cell is not a free cell
	 */
	@Override
	public boolean isFC() {
		return false;
	}
	/**
	 * Returns false, because a free cell is a home cell
	 */
	@Override
	public boolean isHC() {
		return false;
	}
	/**
	 * Returns the last card of the tableau cell
	 */
	@Override
	public Card getLastCard() {
		return pile.getLastCard();
	}

}
