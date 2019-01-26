package edu.buffalo.cse116;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	/**
	 * Number of cards in the deck
	 */
	private int numOfCards;
	/**
	 * ArrayList of type card that contains the cards in the deck
	 */
	private ArrayList<Card> deckPile = new ArrayList<Card>();

	/**
	 * Deck constructor
	 */
	public Deck() {
		numOfCards = 52;
		createFullDeck();
	}

	/**
	 * Creates a playable 52 card deck with unique ranking and suit combination 
	 */
	public void createFullDeck() {
		for (int i = 1; i < 5; i++)
			for (int j = 1; j < 14; j++) {
				deckPile.add(new Card(i, j));
			}
	}

	
	/**
	 * Shuffles deck
	 */
	public void shuffle() {
		Collections.shuffle(deckPile);

	}

	
	/**
	 * Draws from the deck
	 * 
	 * @param x number of cards to draw
	 * @return the card that was drawn
	 */
	public CardPile Draw(int x) throws NullPointerException{
		if(numOfCards-x>=0)
		{
		CardPile p = new CardPile(deckPile);
		 ArrayList<Card> b = new ArrayList<Card>();
		 ArrayList<Card> c = new ArrayList<Card>();
		 for(int i = deckPile.size()-1; i>=x;i--)
		 c.add(deckPile.get(i));
		  
			for(int j = 0; j<x;j++)
		 		b.add(deckPile.get(j));
		 	
		 	this.deckPile = c;
		 	
		 	p = new CardPile(b);
		 	numOfCards= numOfCards-x;
		 	 return p;
		}
		else
			throw new NullPointerException();
		 		
		 	
		 }
	
	

	/**
	 * Resets the deck
	 */
	public void Reset() {
		numOfCards = 52;
		deckPile.clear();
		createFullDeck();
	}


	/**
	 * Count getter
	 * 
	 * @return the amount of cards in the deck.
	 */
	public int getCardCount() {
		return numOfCards;
	}
	
	/**
	 * deckPile getter
	 * 
	 * @return the deckPile
	 */
	public ArrayList<Card> getDeck()
	{
		return this.deckPile;
	}
	/**
	 * Checks if the deck is empty
	 */
	public boolean isEmpty()
	{
		if(numOfCards==0)
		{
			return true;
		}
		else return false;
	}
}
