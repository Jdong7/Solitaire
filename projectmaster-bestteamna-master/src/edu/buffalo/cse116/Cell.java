package edu.buffalo.cse116;

public abstract class Cell {

	/**
	 * A Cell contains a CardPile
	 */
	protected CardPile pile;

	/**
	 * Cell constructor
	 * 
	 * @param pile CardPile that the Cell contains
	 */
	public Cell(CardPile pile) {
		this.pile = pile;
	}

	/**
	 * CardPile getter
	 * 
	 * @return the CardPile the Cell contains
	 */
	public CardPile getCardPile() {
		return this.pile;
	}

	/**
	 * Checks if the CardPile is empty
	 * 
	 * @return true if the CardPile is empty
	 * 		   false if otherwise
	 */
	public boolean isEmpty() {
		if (this.pile.toArray().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Assists the move methods
	 * 
	 * @return CardPile that is extracted from a CardPile
	 */
	public CardPile Extract()
	{
		return this.pile.extract();
	}
	
	/**
	 * Assists the move methods
	 * 
	 * @param insert CardPile the CardPile is added to
	 */
	public void Add(CardPile insert)
	{
		this.pile.addPile(insert);
	}
	
	/**
	 * Adds a card to the Cell
	 * 
	 * @param c Cell that the card is being added to
	 */
	public void addLastCard(Cell c)
	{
		this.pile.addLastCard(c); 
	}
	
	/**
	 * Removes the last card of the Cell
	 */
	public void removeLastCard()
	{
		this.pile.removeLastCard();
	}

	/**
	 * Checks if the cell is a tableau cell, implemented by subclasses
	 */
	public abstract boolean isTC();
	/**
	 * Checks if the cell is a free cell, implemented by subclasses
	 */
	public abstract boolean isFC();
	/**
	 * Checks if the cell is a home cell, implemented by subclasses
	 */
	public abstract boolean isHC();
	/**
	 * Return last card in the cell, implemented by subclasses
	 */
	public abstract Card getLastCard();
}
