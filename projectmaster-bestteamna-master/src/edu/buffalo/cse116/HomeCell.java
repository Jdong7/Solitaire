package edu.buffalo.cse116;


public class HomeCell extends Cell{

	/**
	 * Home Cell constructor
	 * Starts as an empty CardPile
	 * Can only accepts Aces initially
	 * Can only be of a certain suit
	 * Has to be in order (consecutive cards have to be 1 rank higher than previous card).
	 * Max size = 13
	 * 
	 * @param pile The CardPile that the Home Cell contains
	 */
	public HomeCell(CardPile pile) {
		super(pile);
	}

	/**
	 * Returns false, because a home cell is not a tableau cell
	 */
	@Override
	public boolean isTC() {
		return false;
	}
	/**
	 * Returns false, because a home cell is not a free cell
	 */
	@Override
	public boolean isFC() {
		return false;
	}
	/**
	 * Returns true, because a home cell is a home cell
	 */
	@Override
	public boolean isHC() {
		return true;
	}
	/**
	 * Returns the last card of the home cell
	 */
	@Override
	public Card getLastCard() {
		return pile.getLastCard();
	}

}
