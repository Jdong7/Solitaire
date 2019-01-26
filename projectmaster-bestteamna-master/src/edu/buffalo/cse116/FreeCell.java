package edu.buffalo.cse116;

public class FreeCell extends Cell {

	

	/**
	 * Starts empty
	 * Can only hold 1 card
	 * Cards can be added/removed freely
	 * 
	 * @param pile CardPile the Cell holds
	 */
	public FreeCell(CardPile pile) {
		super(pile);
	}

	/**
	 * Returns false, because a free cell is not a tableau cell
	 */
	@Override
	public boolean isTC() {
		return false;
	}

	/**
	 * Returns true, because a free cell is a free cell
	 */
	@Override
	public boolean isFC() {
		return true;
	}

	/**
	 * Returns false, because a free cell is not a home cell
	 */
	@Override
	public boolean isHC() {
		return false;
	}

	/**
	 * Returns the last card in the free cell
	 */
	@Override
	public Card getLastCard() {
		return pile.getLastCard();
	}

}
