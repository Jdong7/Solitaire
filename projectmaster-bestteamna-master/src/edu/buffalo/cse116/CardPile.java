package edu.buffalo.cse116;

import java.util.ArrayList;

public class CardPile {
	
	/**
	 * ArrayList of cards that the CardPile will hold
	 */
	private ArrayList<Card> pile = new ArrayList<Card>();
	/**
	 * Number of cards in the CardPile
	 */
	private int count;
	
	/**
	 * CardPile Constructor
	 * 
	 * @param drew ArrayList of type Card that the CardPile contains
	 */
	public CardPile (ArrayList<Card> drew) {
		pile = drew;
		count = drew.size();
	}

	/**
	 * Getter: Takes a CardPile and returns its corresponding ArrayList<Card>
	 * 
	 * @return ArrayList of Card
	 */
	public ArrayList<Card> toArray(){
		return pile;
	}
	
	/**
	 * Checks for last card in the ardPile removes the card. Then returns 
	 * the card in an ArrayList constructed into a CardPile object.
	 * 
	 * @return a CardPile of one card containing the last card of the CardPile
	 */
	public CardPile extract() {
		ArrayList<Card> temp = new ArrayList<Card>();
		
		if(count>0)
		{
			count--;
		temp.add(this.pile.get(this.pile.size()-1));
		this.pile.remove(this.pile.size()-1);
		}
		CardPile pile = new CardPile(temp);
		return  pile;
		
	}
	

	/**
	 * Takes a CardPile(preferably the return of the extract method) as a parameter and adds that 
	 * to the end of CardPile the method is called upon.
	 * 
	 * @param insert CardPile that is being added
	 */
	public void addPile(CardPile insert) {
		this.pile.add(insert.toArray().get(0));
		count++;
	}
	
	
	/**
	 * @return the number of cards in a CardPile.
	 */
	public int getCount() {
		return count;
	}
	

	/**
	 * @return top card of a CardPile
	 */
	public Card getLastCard() {
		if(this.pile.size()==0)
		{throw new IndexOutOfBoundsException();}
		else{
		return this.pile.get(this.pile.size()-1);
		}
	}
	
	/**
	 * @param c Cell that the card is being added to
	 */
	public void addLastCard(Cell c)
	{
		this.pile.add(c.getCardPile().getLastCard());
		count++;
	}
	
	/**
	 * Remove last card of the CardPile
	 */
	public void removeLastCard()
	{
		if(count>0)
		{
		this.pile.remove(this.pile.size()-1);
		count--;
		}
	}
	/**
	 * Moves the king to the back of the pile
	 */
	public void moveKing()
	{
		Card temp;
		for(int i = 0; i<pile.size();i++)
		{
			if(pile.get(i).getRank()==13)
			{
			temp = pile.get(i);
			 pile.remove(i)	;
			 pile.add(0,temp);
			}
		}
	}
	/**
	 * Checks if the cardpile is empty
	 */
	public boolean isEmpty()
	{
		if(this.pile.isEmpty())
		return true;
		else 
			return false;
		
	}

}
