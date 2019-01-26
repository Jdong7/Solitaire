package edu.buffalo.cse116;

import java.util.ArrayList;

public class BakersDozen extends Game{

	/**
	 * Deck the game is played with
	 */
	private Deck deck = new Deck();
	
	/**
	 * ArrayList of Home Cells
	 */
	private ArrayList<Cell> HC = new ArrayList<Cell>();
	
	/**
	 * ArrayList of Tableau Cells
	 */
	private ArrayList<Cell> TC = new ArrayList<Cell>();

	/**
	 * Constructor of Baker's Dozen Game
	 */
	public BakersDozen() {
		super();
		initialize();
	}

	/**
	 * Adds all the cells with their respective number of cards on to the Baker's Dozen Game
	 */
	public void initialize() {
		deck.shuffle();
		for (int i = 0; i < 13; i++) {
			CardPile pile = deck.Draw(4);
			pile.moveKing();
			Cell c = new TableauCell(pile);
			TC.add(c);
		}
		for (int i = 0; i < 4; i++) {
			ArrayList<Card> cards = new ArrayList<Card>();
			CardPile pile = new CardPile(cards);
			Cell c = new HomeCell(pile);
			HC.add(c);
		}
	}

	/**
	 * Moves the last card from one cell to another while obeying the rules of the Baker's Dozen Game
	 * 
	 * @param A index of the cell extracting from
	 * @param B index of the cell adding to
	 * @param home if false, movement is from Tableau Cell to Tableau Cell
	 * 		  if true, movement is from Tableau Cell to Home Cell
	 */
	public void move(int A, int B, boolean home) {
		int index1 = A - 1;
		int index2 = B - 1;
		if (home) {
			if (!(isEmpty(TC, index1))) {
				if ((isEmpty(HC, index2)) && (isAce(TC, index1))) {
					// HC.get(index2).getCardPile().addPile(TC.get(index1).getCardPile().extract());
					// //move
					// HC.get(index2).Add(TC.get(index1).Extract());
					HC.get(index2).addLastCard(TC.get(index1));
					TC.get(index1).removeLastCard();
				}
				if (!isEmpty(HC, index2))
					if (lastCard(TC, index1).isHome(lastCard(HC, index2))) {
						// HC.get(index2).getCardPile().addPile(TC.get(index1).getCardPile().extract());
						// //move
						// HC.get(index2).Add(TC.get(index1).Extract());
						HC.get(index2).addLastCard(TC.get(index1));
						TC.get(index1).removeLastCard();
					}

			}
		}
		if (home == false) {// doesnt work but why
			if (!isEmpty(TC, index1))
				if (!isEmpty(TC, index2))
					if (lastCard(TC, index1).isConsecutive(lastCard(TC, index2))) {
						// TC.get(index2).getCardPile().addPile(TC.get(index1).getCardPile().extract());
						// //move
						// TC.get(index2).Add(TC.get(index1).Extract());
						TC.get(index2).addLastCard(TC.get(index1));
						TC.get(index1).removeLastCard();
					}
		}
	}
	/**
	 * Helps simplify the code, checks if the cell is empty
	 * 
	 * @param c The ArrayList of the cell
	 * @param a The index that the cell lies in
	 * @return true if the cell is empty
	 * 		   false if not
	 */
	public boolean isEmpty(ArrayList<Cell> c,int a)
	{
		return c.get(a).isEmpty();
	}
	
	/**
	 * Helps simplify the code, checks if the card is an Ace
	 *  
	 * @param c The ArrayList of the cell
	 * @param a The index that the cell lies in
	 * @return true if the card is an Ace
	 * 		   false if not
	 */
	public boolean isAce(ArrayList<Cell> c,int a)
	{
		return c.get(a).getLastCard().isAce();
	}
	
	/**
	 *  Helps simplify the code, returns the last card of the cell
	 * 
	 * @param c The ArrayList of the cell
	 * @param a The index that the cell lies in
	 * @return the last card of the cell
	 */
	
	public Card lastCard(ArrayList<Cell> c, int a)
	{
		return c.get(a).getLastCard();
	}
	
	/**
	 * Helps simplify the code, returns the Home Cell
	 * 
	 * @return the Home Cell
	 */
	public ArrayList<Cell> getHC()
	{
		return HC;
	}
	
	/**
	 * Helps simplify the code, returns the Tableau Cell
	 * 
	 * @return the Tableau Cell
	 */
	public ArrayList<Cell> getTC()
	{
		return TC;
	}
	public Cell getTC(int index)
	{
		return TC.get(index);
	}
	public Cell getHC(int index)
	{
		return HC.get(index);
	}
	
	/**
	 * for junit test
	 * 
	 * @return true if all the items in the ArrayList are Tableau Cells
	 * 		   false if otherwise
	 */
	public boolean allTC()
	{
		int count = 0;
		for(int i = 0;i<TC.size();i++)
		{
			if(TC.get(i).isTC())
					{
				count++;
					}
		}
		if(count==TC.size())
			return true;
		else
			return false;
	}
	/**
	 * for junit test
	 * 
	 * @return true if all the items in the ArrayList are Home Cells
	 * 		   false if otherwise
	 */
	public boolean allHC()
	{
		int count = 0;
		for(int i = 0;i<HC.size();i++)
		{
			if(HC.get(i).isHC())
					{
				count++;
					}
		}
		if(count==HC.size())
			return true;
		else
			return false;
	}
	
	
	/**
	 * for junit test
	 * 
	 * @return deck
	 */
	public Deck getDeck()
	{
		return this.deck;
	}
	
	/**
	 * TC setter
	 * 
	 * @param c ArrayList of type Cell
	 */
	public void setTC(ArrayList<Cell> c) {
		this.TC = c;
	}
	
	/**
	 * HC setter
	 * 
	 * @param c ArrayList of type Cell
	 */
	public void setHC(ArrayList<Cell> c) {
		this.HC = c;
	}
	
	/**
	 * Size getter
	 * 
	 * @param type Type of Cell
	 * @param a Index of Cell in ArrayList of type cell
	 * @return integer of size
	 */
	public int getSize(String type, int a) {
		if (type.equals("TC")) 
			return TC.get(a-1).getCardPile().getCount();
		else
			return HC.get(a-1).getCardPile().getCount();
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
	public void move(int a, int b, int c) {
		// TODO Auto-generated method stub
		
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
	public boolean allFC() {
		// TODO Auto-generated method stub
		return false;
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
	public int methodComparison(Cell c, Cell d) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Method implemented by extending the game class
	 */
	@Override
	public boolean canRemove(Card c) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Method implemented by extending the game class
	 */
	@Override
	public void drawCard() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method implemented by extending the game class
	 */
	@Override
	public void setTopCards() {
		// TODO Auto-generated method stub
		
	}
public int test()
{
	return 10;
}

	/**
	 * Method implemented by extending the game class
	 */
@Override
public void move(Card c) {
	// TODO Auto-generated method stub
	
}
	/**
	 * Method implemented by extending the game class
	 */
@Override
public void setTC(Card c, int index) {
	// TODO Auto-generated method stub
	
}
	/**
	 * Method implemented by extending the game class
	 */
@Override
public boolean emptyInTop() {
	// TODO Auto-generated method stub
	return false;
}


	
}
